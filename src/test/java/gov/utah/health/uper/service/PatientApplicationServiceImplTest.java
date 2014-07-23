package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientApplication;
import gov.utah.health.uper.model.StateFileNumber;
import gov.utah.health.uper.model.UperPatient;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.ApplicationStatus;
import gov.utah.health.uper.model.enums.ApplicationType;
import gov.utah.health.uper.model.enums.PaymentType;
import gov.utah.health.uper.repository.PatientApplicationRepository;
import gov.utah.health.uper.repository.StateFileNumberRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@RunWith(MockitoJUnitRunner.class)
public class PatientApplicationServiceImplTest {
	
	@InjectMocks 
	private PatientApplicationService  patientApplicationService  = new PatientApplicationServiceImpl();
	
	@Mock PatientApplicationRepository patientApplicationRepository;
	@Mock UtilityService utilityService;
 	@Mock StateFileNumberRepository stateFileNumberRepository;
	@Mock SecurityService securityService;
	
	private UperUserDetails currentUser;
	private PatientApplication pa; 
	private ApplicationBean bean;
	private List<PatientApplication> pts;
	private Patient pt;
	private StateFileNumber sfn;
	private static final String STATE_NUMBER = "20140000HEM";
	private List<String> stateFileNumbers = new ArrayList<String>();

	@Before
	public void setUp(){
		pa = new PatientApplication();
		pa.setId(1);
		pa.setApplicationStatus(ApplicationStatus.APPROVED);
		pa.setApplicationType(ApplicationType.NEW);
		pa.setApplicationDate(new Date() );
		sfn= new StateFileNumber();
		sfn.setCounter(0);
		sfn.setStateFileNumber("20140001HEM");
	 	stateFileNumbers.add("20140001HEM");
		pa.setStateFileNumber(sfn);
		pt = new Patient();
		pt.setId(1);
		pt.setPatientFirstName("patientFirstName");
		pt.setPatientLastName("patientLastName");
		pa.setPatient(pt);
		
		
		sfn.setPatientApplication(pa);
		bean = new ApplicationBean();
		bean.setPatientId(1);
		bean.setApplicationDate("04-04-2014");
		bean.setApplicationType("NEW");
		bean.setApplicationStatus("APPROVED");
			
		pts = new ArrayList<PatientApplication>();
		
		Collection<GrantedAuthority>gas = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_ADMIN");
		gas.add(ga);
		currentUser = new UperUserDetails("umdlogin","password",true, false, false, false,gas);
		currentUser.setName("umdlogin");
		sfn.setAddedUserId("umdlogin");
		sfn.setUpdatedUserId("umdlogin");
	 }
	
	
	@Test
	public void testGetApplByStateFileNumber (){
		//uperuser  used for restful calls
		Mockito.when( stateFileNumberRepository.getByStateFileNumber(Mockito.anyString())).thenReturn(sfn);
		UperPatient result = patientApplicationService.getApplByStateFileNumber( STATE_NUMBER);
		Assert.assertEquals(STATE_NUMBER, result.getStateFileNumber());
	}
		 
	
	@Test
	public void testGetNonPIIPatientsLikeName(){
		Mockito.when( patientApplicationRepository.getPatientsLikeName(Mockito.anyString(), Mockito.anyString())).thenReturn(pts);
		List<UperPatient> results = patientApplicationService.getNonPIIPatientsLikeName( pt.getPatientFirstName(), pt.getPatientLastName());
		Assert.assertEquals(results.size(), pts.size());
	} 
	
	
	@Test
	@Ignore
	 public void testCreateNewPatientApplication(){
		Mockito.when(securityService.getUserName()).thenReturn(currentUser.getName());
		Mockito.when(stateFileNumberRepository.getLastNumberIssued()).thenReturn(sfn);
		Mockito.when(patientApplicationService.generateStateFileNumber()).thenReturn( sfn);
		pa.setId(1);
		Mockito.when(patientApplicationRepository.save((PatientApplication)Mockito.anyObject())).thenReturn(pa);
		Mockito.when(patientApplicationRepository.getApplicationsForPatient(Mockito.anyInt())).thenReturn(null);
		Mockito.when(patientApplicationRepository.findOne(Mockito.anyInt()) ).thenReturn(pa);
		ApplicationBean result = patientApplicationService.createNewPatientApplication(pa.getId());
		Assert.assertEquals( "20140001HEM",result.getStateFileNumber());
		Assert.assertEquals(ApplicationType.NEW.toString(), result.getApplicationType());
		Mockito.verify(patientApplicationRepository, Mockito.atLeastOnce()).save((PatientApplication)Mockito.anyObject() );
	}
	
	 
	
	@Test
	public void testGenerateStateFileNumber(){
		Mockito.when(stateFileNumberRepository.getLastNumberIssued()).thenReturn(sfn);
		StateFileNumber result = patientApplicationService.generateStateFileNumber();
		Assert.assertEquals( "20140001HEM", result.getStateFileNumber() );
		
		sfn.setCounter(22);
		Mockito.when(stateFileNumberRepository.getLastNumberIssued()).thenReturn(sfn);
		result = patientApplicationService.generateStateFileNumber();
		Assert.assertEquals( "20140023HEM", result.getStateFileNumber() );
		
		
		sfn.setCounter(923);
		Mockito.when(stateFileNumberRepository.getLastNumberIssued()).thenReturn(sfn);
		result = patientApplicationService.generateStateFileNumber();
		Assert.assertEquals( "20140924HEM", result.getStateFileNumber() );
		
		
		sfn.setCounter(9967);
		Mockito.when(stateFileNumberRepository.getLastNumberIssued()).thenReturn(sfn);
		result = patientApplicationService.generateStateFileNumber();
		Assert.assertEquals( "20149968HEM", result.getStateFileNumber() );
		
 	}
	
	@Test
	public void testGetApplication(){
		Mockito.when(stateFileNumberRepository.getByStateFileNumber(Mockito.anyString())).thenReturn(sfn);
		ApplicationBean result = patientApplicationService.getApplication(STATE_NUMBER);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void saveApplication(){
		Mockito.when(patientApplicationRepository.findOne(Mockito.anyInt())).thenReturn(pa);
		Mockito.when(securityService.getUserName()).thenReturn(currentUser.getName());
		ApplicationBean result = patientApplicationService.savePatientApplication(bean);
		Assert.assertNotNull(result);
	}
	
	
	@Test
  	public void savePaymentType(){
		Mockito.when(patientApplicationRepository.findOne(Mockito.anyInt())).thenReturn(pa);
		Mockito.when(securityService.getUserName()).thenReturn(currentUser.getName());
		patientApplicationService.savePaymentType(pa.getId(), PaymentType.CASH);
		Mockito.verify(patientApplicationRepository, Mockito.atLeastOnce()).save((PatientApplication)Mockito.anyObject());
	 }
	
	@Test
	public void receivePayment(){
		Mockito.when(patientApplicationRepository.findOne(Mockito.anyInt())).thenReturn(pa);
		Mockito.when(securityService.getUserName()).thenReturn(currentUser.getName());
		patientApplicationService.receivePayment(pa.getId(), true);
		Mockito.verify(patientApplicationRepository, Mockito.atLeastOnce()).save((PatientApplication)Mockito.anyObject());
	 }
	
	
}
