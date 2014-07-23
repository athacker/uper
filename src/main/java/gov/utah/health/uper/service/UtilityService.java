package gov.utah.health.uper.service;

import java.util.Date;

public interface UtilityService {
	 
 	 	 
 	/**
 	 * formats phone to be used for reporting
 	 * @param phone
 	 * @return
 	 */
 	String formatPhone(String phone);
 	
 	
 	Date formatDate(String date); 
 	
 	/**
 	 * formats date to be used for reporting.
 	 * @param date
 	 * @return
 	 */
 	String formatDateToString(Date date);
 	
 	/**
 	 * 
 	 * @param text
 	 * @return
 	 */
 	String toProperCase(String text);
 	
 	/**
 	 * 
 	 * @param dopl
 	 * @return
 	 */
 	String formatDopl(String dopl);
 	
 	
 	/**
 	 */
 	int getAge(Date dateOfBirth);
	
}
