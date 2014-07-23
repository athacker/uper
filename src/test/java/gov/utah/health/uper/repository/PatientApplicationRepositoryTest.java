package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.PatientApplication;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-Test.xml")
@Transactional
public class PatientApplicationRepositoryTest {
	
	@Autowired private PatientApplicationRepository patientApplicationRepository;
	
	@Test
	public void testFindAll(){
		List<PatientApplication> applications =  patientApplicationRepository.findAll();
 	}

	
	@Test
	public void testGetPatientsLikeName(){
		List<PatientApplication> applications =  patientApplicationRepository.findAll();
		 for (PatientApplication pa: applications){
			List<PatientApplication> result = patientApplicationRepository.getPatientsLikeName(pa.getPatient().getPatientLastName(), pa.getPatient().getPatientFirstName() );
		 }
	}
	 
	
	@Test
	public void testGetApplicationsForPatient(){
		List<PatientApplication> applications =  patientApplicationRepository.findAll();
	 	for (PatientApplication pa: applications){
			List<PatientApplication>  result = patientApplicationRepository.getApplicationsForPatient(pa.getPatientId()) ;
			Assert.assertEquals(pa.getPatientId(), result.get(0).getPatientId() );
		}
	}
	
	
	@Test
	public void getStateFileNumbersForPatient(){
		List<PatientApplication> applications =  patientApplicationRepository.findAll();
		for (PatientApplication pa: applications){
			List<String> result = patientApplicationRepository.getStateFileNumbersForPatient(pa.getPatientId());
		}
	}
	
	
	
}
