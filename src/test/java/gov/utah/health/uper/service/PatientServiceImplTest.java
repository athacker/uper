package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientBean;
import gov.utah.health.uper.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {
	
	@InjectMocks 
	private PatientService  patientService  = new PatientServiceImpl();
	
	@Mock private PatientRepository patientRepository;
	@Mock private SecurityService securityService;
	@Mock private UtilityService utilityService;
	
	private PatientBean bean; 
	
	
	@Before
	public void setUp(){
		bean = new PatientBean();
		bean.setAddressCurrent("addressCurrent");
		bean.setCityCurrent("cityCurrent");
		bean.setStateCurrent("UT");
		bean.setDob("08-08-2011");
		bean.setDoplNumber("4444-44444");
		bean.setFirstName("firstName");
		bean.setLastName("lastName");
		bean.setLicenseExpireDate("04-14-2018");
		bean.setParentAddressCurrent("parentAddressCurrent");
		bean.setParentCityCurrent("parentCityCurrent");
		bean.setParentDob("11-21-1977");
		bean.setParentFirstName("parentFirstName");
		bean.setParentLastName("parentLastName");
		bean.setParentStateCurrent("UT");
		bean.setParentZipCurrent("84668");
		bean.setStateCurrent("UT");
		bean.setZipCurrent("86775");
	}
	
	
	@Test
	public void testSave(){
		Patient pt = new Patient();
		pt.setId(1);
		Mockito.when(patientRepository.findOne(Mockito.anyInt())).thenReturn(null);
		Mockito.when(patientRepository.save((Patient)Mockito.anyObject())).thenReturn(pt);
		Mockito.when(securityService.getUserName()).thenReturn("TEST");
		patientService.savePatient(bean);
		Mockito.verify(patientRepository, Mockito.atLeastOnce() ).save( (Patient)Mockito.anyObject());
	}
	
	@Test
	public void testGetByName(){
		Patient pt = new Patient();
		pt.setPatientLastName("Smith");
		pt.setParentFirstName("Fred");
		List<Patient>pts = new ArrayList<Patient>();
		pts.add(pt);
		Mockito.when(patientRepository.getPatientsLikeName(Mockito.anyString(), Mockito.anyString())).thenReturn(pts);
		List<Patient> test = patientService.getPatientsLikeName("smi", "fre");
		Assert.assertEquals(pts.size(),test.size());
	}
	
	
	
}
