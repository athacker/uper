package gov.utah.health.uper.service;

import gov.utah.dts.addresslookup.client.Address;
import gov.utah.dts.addresslookup.client.AddressLookupSOAPRequest;
import gov.utah.health.uper.model.AddressBean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author athacker
 *
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService{
	
	
 	@Autowired ApplicationPropertyService applicationPropertyService;
 	@Autowired UtilityService utilityService;
	
	private static String END_POINT_KEY="address.service.endpoint";
	
	@SuppressWarnings("unchecked")
	public List<Address>getAddressByZipCode(String zipCode){
		String endpoint = applicationPropertyService.getPropertyAsString(END_POINT_KEY);
	  	AddressLookupSOAPRequest addressLookupSOAPRequest = new AddressLookupSOAPRequest(endpoint);
	    Address address = new Address();
	    address.setZipCode(zipCode);
	    return ( List<Address> ) addressLookupSOAPRequest.selectAddressInfo(address);
	    
	}
	/**
	 * returns an address bean used to auto populate zip,city,state
	 * @param zipCode
	 * @return
	 */
	public AddressBean getCityStateByZip(String zipCode) {
		AddressBean bean = new AddressBean();
		bean.setZipCode(zipCode);
		List<Address> results = getAddressByZipCode(zipCode); 
		if ( !results .isEmpty()) {
			Address address = getAddressByZipCode(zipCode).get(0);
			bean.setValidZip(true);
			bean.setState(address.getStateCode());
			bean.setCity(utilityService.toProperCase(address.getCityName() ));
		} else{
			bean.setValidZip(false);
		}
		return bean;
	}
	
	
	
	
}
