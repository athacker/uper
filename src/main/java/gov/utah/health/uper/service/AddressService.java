package gov.utah.health.uper.service;

import gov.utah.dts.addresslookup.client.Address;
import gov.utah.health.uper.model.AddressBean;

import java.util.List;

public interface AddressService {
	
	/**
	 * hits a webservice to collect data associated to a zip code.
	 * @param zipCode
	 * @return
	 */
	 List<Address>getAddressByZipCode(String zipCode);
	 
	 
	 /**
	  * hits a webservice to collect data associated to a zip code.
	  * @param zip
	  * @return
	  */
	 AddressBean getCityStateByZip(String zip);
	 
 }
