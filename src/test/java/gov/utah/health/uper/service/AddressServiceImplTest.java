package gov.utah.health.uper.service;

import gov.utah.dts.addresslookup.client.Address;
import gov.utah.health.uper.model.AddressBean;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {
	
	@InjectMocks 
	private AddressServiceImpl addressService = new AddressServiceImpl();
	
	@Mock private ApplicationPropertyService applicationPropertyService;
	@Mock private UtilityService utilityService;
	
	@Test
	public void testGetAddress() {
		String test1 = "84098";
		Mockito.when(applicationPropertyService.getPropertyAsString(Mockito.anyString())).thenReturn("http://api.dts.utah.gov/addressLookup/services/AddressLookupService");
		List<Address>result= addressService.getAddressByZipCode(test1);
		Assert.assertEquals(test1, result.get(0).getZipCode());
	 }
	
	@Test
	public void testGetAddressBean() {
		String test1 = "84098";
		Mockito.when(applicationPropertyService.getPropertyAsString(Mockito.anyString())).thenReturn("http://api.dts.utah.gov/addressLookup/services/AddressLookupService");
		AddressBean result = addressService.getCityStateByZip(test1);
		Assert.assertEquals(test1, result.getZipCode());
	}
	
	
}
