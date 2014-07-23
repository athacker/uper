package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.RoleType;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service("securityService")
public class SecurityServiceImpl implements SecurityService{
	 
	
	public void logout(HttpServletRequest request){
		SecurityContextHolder.getContext().setAuthentication(null);
		request.getSession().invalidate();
		return;
 }

	public UperUserDetails getCurrentUser(){
		return (UperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 	}
	
	
	public Role getCurrentRole(){
		Role role = new Role();
		for (GrantedAuthority ga:  SecurityContextHolder.getContext().getAuthentication().getAuthorities() ){
			//user should only have 1 role
			role = new Role(RoleType.valueOf( ga.getAuthority() )); 
		}
		return role;
	}

	@Override
	public String getUserName() {
		return getCurrentUser().getUsername();
	}
	
	
	
 


}
