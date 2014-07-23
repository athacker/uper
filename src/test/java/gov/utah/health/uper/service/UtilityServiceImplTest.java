package gov.utah.health.uper.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UtilityServiceImplTest {

	private UtilityService utilityService = new UtilityServiceImpl();
 	
	@Before
	public void setUp(){ }
 
	@Test
	public void testFormatExpected() throws Exception{
		String strDate="07-01-2014";
		Date result = utilityService.formatDate(strDate);
	 	String strResult = utilityService.formatDateToString(result);
	 	Assert.assertEquals(strDate, strResult);
	}
	
	
	@Test
	public void testFormatMask() throws Exception{
		String expected = "07-01-2014";
		String strDate="07012014";
		Date result = utilityService.formatDate(strDate);
	 	String strResult = utilityService.formatDateToString(result);
	 	Assert.assertEquals(expected, strResult);
	}
   
    
	@Test
	public void testToProperCase() throws Exception{
		
		String result = utilityService.toProperCase("PARK CITY");
		Assert.assertEquals(result, "Park City");
		
		result = utilityService.toProperCase("HEBER");
		Assert.assertEquals(result, "Heber");
		
		result = utilityService.toProperCase("SALT LAKE CITY");
		Assert.assertEquals(result, "Salt Lake City");
 	
	}
    
   
	@Test
	public void testFormatDopl(){
		String dopl="111114405";
		String result = utilityService.formatDopl(dopl);
		Assert.assertEquals(result, "11111-4405");
		
		
		dopl="22222224405";
		result = utilityService.formatDopl(dopl);
		Assert.assertEquals(result, "2222222-4405");
		
		
		dopl="3334405";
		result = utilityService.formatDopl(dopl);
		Assert.assertEquals(result, "333-4405");
		
	}
   
	
	@Test
	public void testGetAge(){
		String stDate = "01-01-2001";
		Date dtDate = utilityService.formatDate(stDate);
	 	int age = utilityService.getAge(dtDate);
		Assert.assertTrue(age>12) ;
	}
	
     
}
