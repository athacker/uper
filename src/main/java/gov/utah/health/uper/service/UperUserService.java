package gov.utah.health.uper.service;

import gov.utah.health.uper.model.UperUser;

import java.util.List;

public interface UperUserService {
	/**
	 * 
	 * @param loginId
	 * @return
	 */
	UperUser getUperUserByLoginId(String loginId);
	/**
	 * 
	 * @return
	 */
	List<UperUser >getAllUsers();
	 

	/**
	 * 
	 * @param uperUser
	 * @return
	 */
	void saveUser(UperUser uperUser);
	
}
