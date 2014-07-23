package gov.utah.health.uper.service;

 
import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.UperUserDetails;

import javax.servlet.http.HttpServletRequest;
/**
 * Provides security related methods.
 * 
 *
 */
public interface SecurityService {

	/**
	 * Logs user out of   system.
	 * @param request
	 */
	void logout(HttpServletRequest request);
	/**
	 * UserDetails used for Spring Login/Security Roles
	 * @return
	 */
	UperUserDetails getCurrentUser();
	/**
	 * Current Role of User
	 * @return
	 */
	Role getCurrentRole();
	
	/**
	 * 
	 * @returns logged in user name
	 */
	String getUserName();

}
