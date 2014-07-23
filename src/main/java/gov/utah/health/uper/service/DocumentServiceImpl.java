package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationDocument;
import gov.utah.health.uper.model.DocumentBean;
import gov.utah.health.uper.model.enums.DocumentType;
import gov.utah.health.uper.repository.ApplicationDocumentRepository;
import gov.utah.health.uper.repository.PatientApplicationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("documentService")
public class DocumentServiceImpl implements DocumentService{
	
	
	@Autowired ApplicationDocumentRepository  applicationDocumentRepository;
	@Autowired PatientApplicationRepository patientApplicationRepository;
	@Autowired SecurityService securityService;

	@Override
	public ApplicationDocument saveDocument(Integer applicationId, String documentCode) {
		ApplicationDocument doc = new ApplicationDocument();
		doc.setPatientApplication( patientApplicationRepository.findOne( applicationId) );
		doc.setAddedUserId(securityService.getUserName());
		doc.setUpdatedUserId(securityService.getUserName());
		doc.setDocumentType(DocumentType.valueOf(documentCode));
		return  applicationDocumentRepository.save(doc);
	 }

	@Override
	public void deleteDocument(Integer applicationId, String documentCode) {
		applicationDocumentRepository.deleteDocument( applicationId, DocumentType.valueOf( documentCode ));
 	}
 
	@Override
	public DocumentBean getDocumentsForApplication(String stateFileNumber){
		List< DocumentType> documents =  applicationDocumentRepository.getApplicationDocuments(stateFileNumber); 
		DocumentBean bean = new DocumentBean();  
		 if (documents.contains(DocumentType.ID)){
			 bean.setID(true);
		  }
		 if (documents.contains(DocumentType.H001)){
			 bean.setH001(true);
		  }
		 if (documents.contains(DocumentType.DOC_RES)){
			 bean.setDOC_RES(true);
		  }
		 if (documents.contains(DocumentType.H003)){
			 bean.setH003(true);
		  }
		 if (documents.contains(DocumentType.GUARD)){
			 bean.setGUARD(true);
		  }
		 if (documents.contains(DocumentType.H005)){
			 bean.setH005(true);
		  }
		 
		 
		 return bean ;
	}
	 
	
	
	
	
}
