package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationDocument;
import gov.utah.health.uper.model.PatientApplication;
import gov.utah.health.uper.model.enums.DocumentType;
import gov.utah.health.uper.repository.ApplicationDocumentRepository;
import gov.utah.health.uper.repository.PatientApplicationRepository;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceImplTest {

	
	@InjectMocks
	private DocumentServiceImpl documentService  = new DocumentServiceImpl();
	
	@Mock private ApplicationDocumentRepository applicationDocumentRepository;
	@Mock private PatientApplicationRepository patientApplicationRepository;
	@Mock private SecurityService securityService;
	
	private ApplicationDocument pd;
	private PatientApplication application;
 
	
	@Before
	public void setUp(){
		pd = new ApplicationDocument();
		pd.setDocumentType(DocumentType.H003);
		application = new  PatientApplication();
		application.setId(14);
		pd.setPatientApplication(application);
	}

	@Test
	public void testSave(){
		Mockito.when(patientApplicationRepository.findOne(Mockito.anyInt())).thenReturn(application);
		Mockito.when(applicationDocumentRepository.save(( ApplicationDocument)Mockito.anyObject())).thenReturn(pd);
		ApplicationDocument doc = documentService.saveDocument(1, DocumentType.H003.toString());
		Assert.assertEquals(DocumentType.H003, doc.getDocumentType());
		Assert.assertEquals(application, doc.getPatientApplication());
	}
	
	@Test
	public void testDelete(){
		documentService.deleteDocument(1, DocumentType.H003.toString());
	}
	
	@Test
	public void testGetDocuments(){
		documentService.getDocumentsForApplication("stateFileNumber");
	}
	
}
