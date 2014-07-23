package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.Patient;

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
public class PatientRepositoryTest {
	
	
	@Autowired private PatientRepository patientRepository;
	 
	
	@Test
	public void testFindAll(){
		List<Patient> pts =  patientRepository.findAll();
 	}
	
	@Test
	public void testSavePatient(){
		List<Patient> pts =  patientRepository.findAll();
		for (Patient p: pts){
			patientRepository.save(p);
		}
	}
	
	@Test
	public void testGetPatientsByName(){
		List<Patient> pts =  patientRepository.findAll();
		for (Patient p: pts){
			patientRepository.getPatientsLikeName(p.getPatientLastName(), p.getPatientFirstName() );
		}
		
	}
	
	 
}
