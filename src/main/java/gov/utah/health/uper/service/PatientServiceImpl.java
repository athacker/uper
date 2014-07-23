package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientBean;
import gov.utah.health.uper.repository.PatientApplicationRepository;
import gov.utah.health.uper.repository.PatientRepository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("patientService")
public class PatientServiceImpl implements PatientService{

	@Autowired PatientRepository patientRepository;
	@Autowired PatientApplicationRepository patientApplicationRepository;
	@Autowired SecurityService securityService;
	@Autowired UtilityService utilityService;
	
	@Override
	public PatientBean savePatient(PatientBean bean) {
		
		Patient pt = patientRepository.findOne(bean.getPatientId());
		
		if (null == pt){
			pt = new Patient();
			pt.setAddedUserId(securityService.getUserName());
			pt.setCreatedDate(new Date());
		}
		pt.setUpdatedUserId(securityService.getUserName());
		pt.setLicenseExpireDate(utilityService.formatDate(bean.getLicenseExpireDate()));
		pt.setParentAddress(bean.getParentAddressCurrent());
		pt.setParentCity(bean.getParentCityCurrent());
		if (StringUtils.isNotBlank(bean.getParentDob())){
			pt.setParentDob(utilityService.formatDate(bean.getParentDob() ));
		}
		pt.setParentFirstName(bean.getParentFirstName());
		pt.setParentLastName(bean.getParentLastName());
		pt.setParentMiddleName(bean.getParentMiddleName());
		pt.setParentState(bean.getParentStateCurrent());
		pt.setParentZip(bean.getParentZipCurrent());
		
		pt.setPatientAddress(bean.getAddressCurrent());
		pt.setPatientCity(bean.getCityCurrent());
		pt.setPatientDob(utilityService.formatDate(bean.getDob()));
		pt.setPatientFirstName(bean.getFirstName());
		pt.setPatientLastName(bean.getLastName());
		pt.setPatientMiddleName(bean.getMiddleName());
		pt.setPatientState(bean.getStateCurrent());
		pt.setPatientZip(bean.getZipCurrent());
		
		pt.setPhysicianName(bean.getPhysicianName());
		pt.setPhysicianBoardCertified(bean.isPhysicianBoardCertified() );
		if (null != bean.getDoplNumber()){
			pt.setDoplNumber( bean.getDoplNumber() );
		}
		
		
		
		pt = patientRepository.save(pt);
		bean.setPatientId(pt.getId());
		return bean;
		 
	}
	
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<Patient> getPatientsLikeName( String lastName, String firstName ){
		StringBuilder last= new StringBuilder(lastName.toUpperCase());
		last.append("%");
		
		StringBuilder first= new StringBuilder(firstName.toUpperCase());
		first.append("%");
		
		return patientRepository.getPatientsLikeName(last.toString(), first.toString());
	}

	
	
	public PatientBean getPatient(Integer patientId){
		Patient pt = patientRepository.findOne(patientId);
		return convertToBean(pt);
	 }
	
	
	private PatientBean convertToBean(Patient p){
		PatientBean  bean = new PatientBean();
		
		bean.setPatientId(p.getId());
		bean.setFirstName(p.getPatientFirstName());
		bean.setLastName(p.getPatientLastName());
		bean.setMiddleName(p.getPatientMiddleName());
		bean.setDob(utilityService.formatDateToString(p.getPatientDob() ));
		bean.setAddressCurrent(p.getPatientAddress());
		bean.setCityCurrent(p.getPatientCity());
		bean.setStateCurrent(p.getPatientState());
		bean.setZipCurrent(p.getPatientZip());
		
		
		bean.setParentFirstName(p.getParentFirstName());
		bean.setParentLastName(p.getParentLastName());
		bean.setParentMiddleName(p.getParentMiddleName() );
		bean.setParentDob(utilityService.formatDateToString( p.getParentDob()  ) );
		bean.setParentAddressCurrent(p.getParentAddress());
		bean.setParentCityCurrent(p.getParentCity());
		bean.setParentStateCurrent(p.getParentState());
		bean.setParentZipCurrent(p.getParentZip());
		 
		bean.setPhysicianName(p.getPhysicianName());
		bean.setDoplNumber(p.getDoplNumber());
		bean.setPhysicianBoardCertified(p.isPhysicianBoardCertified());
		bean.setLicenseExpireDate(utilityService.formatDateToString(p.getLicenseExpireDate() ));
		
	    bean.setMinor( utilityService.getAge(p.getPatientDob())< 18);
		 
	
 		bean.setStateFileNumbers(  patientApplicationRepository.getStateFileNumbersForPatient(bean.getPatientId() ));
		
		return bean;
	}
	
	
	
}
