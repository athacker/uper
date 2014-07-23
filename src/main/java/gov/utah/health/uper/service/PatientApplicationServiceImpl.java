package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientApplication;
import gov.utah.health.uper.model.StateFileNumber;
import gov.utah.health.uper.model.UperPatient;
import gov.utah.health.uper.model.enums.ApplicationStatus;
import gov.utah.health.uper.model.enums.ApplicationType;
import gov.utah.health.uper.model.enums.PaymentType;
import gov.utah.health.uper.repository.PatientApplicationRepository;
import gov.utah.health.uper.repository.PatientRepository;
import gov.utah.health.uper.repository.StateFileNumberRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("patientApplicationService")
public class PatientApplicationServiceImpl implements PatientApplicationService {
	
	
	@Autowired private PatientRepository patientRepository;
	@Autowired private PatientApplicationRepository patientApplicationRepository;
	@Autowired private SecurityService securityService;
	@Autowired private StateFileNumberRepository stateFileNumberRepository;
	@Autowired private UtilityService utilityService;
	
	
	/**
	 * Used for UCJIS web services
	 */
	@Override
	@Transactional(readOnly  = true)
	public UperPatient getApplByStateFileNumber( String stateFileNumber) {
		
		UperPatient bean = new UperPatient();
		StateFileNumber sfn = stateFileNumberRepository.getByStateFileNumber(stateFileNumber);
	 	if (null != sfn){
			PatientApplication application = sfn.getPatientApplication();
			bean.setStateFileNumber(stateFileNumber);
			bean.setApplicationStatus(application.getApplicationStatus().toString());
			bean.setExpiratonDate(utilityService.formatDateToString(application.getExpirationDate()));
			bean.setIssueDate(utilityService.formatDateToString(application.getIssuedDate()));
			bean.setLastName(application.getPatient().getPatientLastName());
			bean.setFirstName(application.getPatient().getPatientFirstName());
		 
		}
		
		return bean;
	}
	
	
	
	
	

	/**
	 * SUPPORTS restful calls delivered to ucijis - department of law enforcement
	 * Return a list to Dept Of Law Enforcement -- do not send PII data!
	 */
	@Transactional(readOnly  = true)
	 public List<UperPatient> getNonPIIPatientsLikeName(  String lastName, String firstName  ){
		 List<UperPatient> noPiiPatients = new ArrayList<UperPatient>();
		 
		 StringBuilder last= new StringBuilder(lastName.toUpperCase());
		 last.append("%");
			
		 StringBuilder first= new StringBuilder(firstName.toUpperCase());
		 first.append("%");
		 
		 List<PatientApplication> pts = patientApplicationRepository.getPatientsLikeName(last.toString(), first.toString());
		 
		 for (PatientApplication pa: pts){
			 UperPatient bean = new UperPatient();
			 bean.setFirstName(pa.getPatient().getPatientFirstName());
			 bean.setLastName(pa.getPatient().getPatientLastName());
			 bean.setStateFileNumber(pa.getStateFileNumber().getStateFileNumber());
			 bean.setIssueDate(utilityService.formatDateToString(pa.getIssuedDate()));
			 bean.setExpiratonDate(utilityService.formatDateToString(pa.getExpirationDate()));
			 bean.setApplicationStatus(pa.getApplicationStatus().toString());
			 noPiiPatients.add(bean);
		 }
 
		 return noPiiPatients;
	 }


	@Override
	@Transactional
	public ApplicationBean createNewPatientApplication( Integer patientId) {
 
		Patient pt = patientRepository.findOne(patientId);
		PatientApplication pa = new PatientApplication();
		
		List<PatientApplication> previousApplications = patientApplicationRepository.getApplicationsForPatient(patientId);
		if (previousApplications.isEmpty()){
			pa.setApplicationType(ApplicationType.NEW);
		}else{
			pa.setApplicationType(ApplicationType.RENEWAL);
		}
		
		pa.setAddedUserId(securityService.getUserName());
		pa.setApplicationDate(new Date());
		
		pa.setPatient(pt);
		pa.setApplicationStatus(ApplicationStatus.IN_PROCESS);
		pa.setCreatedDate(new Date());
	 	pa.setUpdatedDate(new Date());
		pa.setUpdatedUserId(securityService.getUserName());
		StateFileNumber sf = generateStateFileNumber();
		sf.setPatientApplication(pa);
		pa.setStateFileNumber( sf);
		 
		pa=patientApplicationRepository.save(pa);
		return  getApplication(pa.getStateFileNumber().getStateFileNumber());
	}




	@Override
	@Transactional
	public StateFileNumber generateStateFileNumber() {
		StateFileNumber sf = new StateFileNumber();
		StateFileNumber lastFileNumber =  stateFileNumberRepository.getLastNumberIssued();
		int counter = 0;
		if (null != lastFileNumber){
			counter = lastFileNumber.getCounter() + 1;
		}
		StringBuilder sbNumber = new StringBuilder( String.valueOf( Calendar.getInstance().get(Calendar.YEAR) ) );
	 	sbNumber.append( StringUtils.leftPad(String.valueOf(counter ), 4, "0") );
	 	sbNumber.append("HEM");
	 	sf.setAddedUserId(securityService.getUserName());
	 	sf.setUpdatedUserId(securityService.getUserName());
		sf.setCounter(counter);  
		sf.setCreatedDate(new Date());
		sf.setUpdatedDate(new Date());
		sf.setStateFileNumber(sbNumber.toString() );
	  	return sf;
 	}
 
	
	
	
	@Override
	@Transactional
	public ApplicationBean savePatientApplication( ApplicationBean bean) {
		PatientApplication pa = patientApplicationRepository.findOne(bean.getPatientApplicationId());
		pa.setApplicationStatus(ApplicationStatus.valueOf(bean.getApplicationStatus()));
		 

		if(null != bean.getIssuedDate()){
			pa.setIssuedDate(utilityService.formatDate(bean.getIssuedDate()));
		}	
		if(null != bean.getExpirationDate()){
			pa.setExpirationDate(  utilityService.formatDate(bean.getExpirationDate()));
		}	
		
	    pa.setPaymentReceived(bean.isPaymentReceived());
		pa.setCheckMoneyOrderNumber(bean.getCheckMoneyOrderNumber());
		if (null != bean.getPaymentType()){
			pa.setPaymentType(PaymentType.valueOf(bean.getPaymentType()));
		}
	    
		pa.setSecurityPaperNumber(bean.getSecurityPaperNumber());
		pa.setUpdatedDate(new Date());
		pa.setUpdatedUserId(securityService.getUserName());
		
		patientApplicationRepository.save(pa);
		return bean;
	}
	 
 
	@Override
	@Transactional(readOnly  = true)
	public ApplicationBean getApplication(String stateFileNumber){
		ApplicationBean bean = new ApplicationBean();
		StateFileNumber sfn = stateFileNumberRepository.getByStateFileNumber(stateFileNumber);
		bean.setApplicationDate(utilityService.formatDateToString(sfn.getPatientApplication().getApplicationDate() ));
		bean.setApplicationStatus( sfn.getPatientApplication().getApplicationStatus().toString());
		bean.setApplicationType(sfn.getPatientApplication().getApplicationType().toString());
		bean.setPatientApplicationId(sfn.getPatientApplication().getId());
		bean.setPatientFirstName(sfn.getPatientApplication().getPatient().getPatientFirstName());
		bean.setPatientLastName(sfn.getPatientApplication().getPatient().getPatientLastName());
		bean.setPatientId( sfn.getPatientApplication().getPatient().getId());
		if (null != sfn.getPatientApplication().getIssuedDate()){
			bean.setIssuedDate(utilityService.formatDateToString(sfn.getPatientApplication().getIssuedDate() ));
		}
		if (null != sfn.getPatientApplication().getExpirationDate()){
			bean.setExpirationDate(utilityService.formatDateToString(sfn.getPatientApplication().getExpirationDate() ));
		}
		bean.setSecurityPaperNumber(sfn.getPatientApplication().getSecurityPaperNumber());
		bean.setStateFileNumber(sfn.getPatientApplication().getStateFileNumber().getStateFileNumber());
		if (null != sfn.getPatientApplication().getPaymentReceived() ){
			bean.setPaymentReceived(sfn.getPatientApplication().getPaymentReceived());
		}	
		if (null != sfn.getPatientApplication().getPaymentType()){
			bean.setPaymentType(sfn.getPatientApplication().getPaymentType().toString());
		}	
		bean.setCheckMoneyOrderNumber(sfn.getPatientApplication().getCheckMoneyOrderNumber() );
		bean.setMinor( utilityService.getAge(sfn.getPatientApplication().getPatient().getPatientDob()) < 18   );
		return bean;
	}
	
	
	
	public List<ApplicationBean>getApplications(){
		List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
		List<PatientApplication> aps=patientApplicationRepository.findAll();
		for(PatientApplication ap:aps){
			
			ApplicationBean bean = new ApplicationBean();
			bean.setApplicationDate(utilityService.formatDateToString(ap.getApplicationDate() ));
			bean.setApplicationStatus( ap.getApplicationStatus().toString());
			bean.setApplicationType(ap.getApplicationType().toString());
			bean.setPatientApplicationId(ap.getId());
			bean.setPatientFirstName(ap.getPatient().getPatientFirstName());
			bean.setPatientLastName(ap.getPatient().getPatientLastName());
			bean.setPatientId( ap.getPatient().getId());
			if (null != ap.getIssuedDate()){
				bean.setIssuedDate(utilityService.formatDateToString(ap.getIssuedDate() ));
			}
			if (null != ap.getExpirationDate()){
				bean.setExpirationDate(utilityService.formatDateToString(ap.getExpirationDate() ));
			}
			bean.setSecurityPaperNumber(ap.getSecurityPaperNumber());
			bean.setStateFileNumber(ap.getStateFileNumber().getStateFileNumber());
			if (null != ap.getPaymentReceived() ){
				bean.setPaymentReceived(ap.getPaymentReceived());
			}	
			if (null != ap.getPaymentType()){
				bean.setPaymentType(ap.getPaymentType().toString());
			}	
			bean.setCheckMoneyOrderNumber(ap.getCheckMoneyOrderNumber() );
			bean.setMinor( utilityService.getAge(ap.getPatient().getPatientDob()) < 18   );
			
			applications.add(bean);
		}
		
		
		
		return applications;
	}


	@Override
	public List<PatientApplication> getPtApplications() {
	 	return patientApplicationRepository.findAll();
	}

 
	@Override
	@Transactional
	public void savePaymentType(Integer applicationId, PaymentType paymentType) {
		PatientApplication pa = patientApplicationRepository.findOne(applicationId);
		pa.setUpdatedUserId(securityService.getUserName());
		pa.setUpdatedDate(new Date());
		pa.setPaymentType(paymentType);
		patientApplicationRepository.save(pa);
	 }


	@Override
	@Transactional
	public void receivePayment(Integer applicationId, boolean paymentReceived) {
		PatientApplication pa = patientApplicationRepository.findOne(applicationId);
		pa.setPaymentReceived(paymentReceived);
		pa.setUpdatedUserId(securityService.getUserName());
		pa.setUpdatedDate(new Date());
		patientApplicationRepository.save(pa);
	}
	 
	
	
	
	
	

}
