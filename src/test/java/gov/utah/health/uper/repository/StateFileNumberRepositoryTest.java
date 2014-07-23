package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.StateFileNumber;

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
public class StateFileNumberRepositoryTest {
	
	@Autowired private StateFileNumberRepository stateFileNumberRepository;
	
	@Test
	public void testFindAll(){
		List<StateFileNumber> sfns =  stateFileNumberRepository.findAll();
		
		for (StateFileNumber sf: sfns){
			System.out.println("\n\n\tTest:  " + sf.getPatientApplication() );
		}
		
		
 	}

	 
	
	
}
