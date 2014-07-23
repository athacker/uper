package gov.utah.health.uper.service;

import gov.utah.health.uper.model.ApplicationProperty;
import gov.utah.health.uper.repository.ApplicationPropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class  ApplicationPropertyServiceImplTest{
	
	
	@InjectMocks
	private ApplicationPropertyService service = new ApplicationPropertyServiceImpl();
	 
	@Mock private ApplicationPropertyRepository applicationPropertyRepository;
 
	ApplicationProperty prop;
	List<ApplicationProperty >props;
	
	@Before
	public void setUp(){
		 prop = new ApplicationProperty();
		 prop.setName("email.smt.host");
		 prop.setValue("utah.gov.smpt.host");
		 props = new ArrayList<ApplicationProperty>();
		 props.add(prop);
	 }
		
	@Test
	public void testGetPropertyAsString() throws Exception{
		Mockito.when(applicationPropertyRepository.findByName(Mockito.anyString())).thenReturn(prop );
 		String value = service.getPropertyAsString("email.smt.host");
 		Assert.assertEquals(prop.getValue(), value);
 		Mockito.verify(applicationPropertyRepository ,Mockito.atLeastOnce()).findByName(Mockito.anyString());
	}
	 
	
	
	@Test
	public void testGetPropertyMap() throws Exception{
		Mockito.when(applicationPropertyRepository.findAll() ).thenReturn(props);
 		Map<String, String>properties = service.getPropertyMap();
 		Assert.assertEquals(properties.size(), props.size());
 		Mockito.verify(applicationPropertyRepository ,Mockito.atLeastOnce()).findAll( );
	}
	
	
	
}
