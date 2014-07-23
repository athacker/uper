package gov.utah.health.uper.repository;

import gov.utah.health.uper.controller.ApplicationController;
import gov.utah.health.uper.model.ApplicationDocument;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-Test.xml")
@Transactional
public class ApplicationDocumentRepositoryTest {
	
	
	@Autowired private ApplicationDocumentRepository applicationDocumentRepository;
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationDocumentRepositoryTest.class);
	
	@Test
	public void testFindAll(){
		List<ApplicationDocument> pts =  applicationDocumentRepository.findAll();
		LOG.warn("WARN TEST");
		LOG.debug("DEBUG TEST");
		LOG.info("INFO TEST");
 	}
	
	
	@Test
	public void testDelete(){
		for(ApplicationDocument pd: applicationDocumentRepository.findAll() ){
			applicationDocumentRepository.deleteDocument(pd.getApplicationId(), pd.getDocumentType());
		}
	}
	
	
	
	@Test
	public void testGetApplicationDocuments(){
		for(ApplicationDocument pd: applicationDocumentRepository.findAll() ){
			String sf = pd.getPatientApplication().getStateFileNumber().getStateFileNumber();
			applicationDocumentRepository.getApplicationDocuments(sf);
		}
	}
	 
	 
	
	 
}
