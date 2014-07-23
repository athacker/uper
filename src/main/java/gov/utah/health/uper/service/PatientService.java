package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientBean;

import java.util.List;

/**
 * 
 * Patient Business Logic
 *
 */
public interface PatientService {
	
	/**
	 * Saves Patient
	 * @param patientBean
	 * @return
	 */
	PatientBean savePatient(PatientBean patientBean);
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	List<Patient> getPatientsLikeName(  String lastName, String firstName  );
	
	
	/**
	 * 
	 * @param patientId
	 * @return
	 */
	PatientBean getPatient(Integer patientId);

	
}
