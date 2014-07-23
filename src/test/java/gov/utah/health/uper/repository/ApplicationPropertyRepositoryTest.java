package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.ApplicationProperty;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-Test.xml")
@Transactional
public class ApplicationPropertyRepositoryTest {
	
	@Autowired private  ApplicationPropertyRepository  applicationPropertyRepository;
	 
	
	@Test
	public void testFindAll(){
		List<ApplicationProperty > properties = applicationPropertyRepository.findAll();
	}
	
	
}
