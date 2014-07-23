package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationDocument;
import gov.utah.health.uper.model.DocumentBean;

public interface DocumentService {

	/**
	 * 
	 * @param patientId
	 * @param documentCode
	 * @return
	 */
	ApplicationDocument saveDocument(Integer patientId, String documentCode);

	/**
	 * 
	 * @param patientId
	 * @param documentCode
	 */
	void deleteDocument(Integer patientId, String documentCode);

	/**
	 * 
	 * @param patientId
	 * @return
	 */
	DocumentBean getDocumentsForApplication(String stateFileNumber);

}
