package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.PatientApplication;
import gov.utah.health.uper.model.StateFileNumber;
import gov.utah.health.uper.model.UperPatient;
import gov.utah.health.uper.model.enums.PaymentType;

import java.util.List;

public interface PatientApplicationService {
 
	/**
	 * 
	 * @param stateFileNumber
	 * @return
	 */
	UperPatient getApplByStateFileNumber(String stateFileNumber);
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	List<UperPatient> getNonPIIPatientsLikeName(  String lastName, String firstName  );
	
	
	/**
	 * 
	 * @param patientId
	 * @return
	 */
	ApplicationBean createNewPatientApplication(Integer patientId);
	
	/**
	 * 
	 * @param applicationBean
	 * @return
	 */
	ApplicationBean savePatientApplication(ApplicationBean applicationBean);
	
	/**
	 * 
	 * @param applicationPatientId
	 * @return
	 */
	ApplicationBean getApplication(String stateFileNumber);
	
	/**
	 * 
	 * @return
	 */
	StateFileNumber generateStateFileNumber(); 
	
	
	/**
	 * 
	 * @return
	 */
	List<ApplicationBean>getApplications();
	
	
	/**
	 * 
	 * @return
	 */
	List<PatientApplication>getPtApplications();
	
	/**
	 * 
	 * @param applicationId
	 * @param paymentType
	 */
	void savePaymentType(Integer applicationId, PaymentType paymentType);
	
	/**
	 * 
	 * @param applicationId
	 * @param isReceived
	 */
	void receivePayment(Integer applicationId, boolean isReceived);
	
	
	
}
