package gov.utah.health.uper.service;
 
import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.repository.UperUserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service ("userDetailsService")
public class UperUserDetailsService implements UserDetailsService {

	
	private static final Logger LOG = LoggerFactory.getLogger(UperUserDetailsService.class);
	
	@Autowired UperUserRepository uperUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
	 	Collection<GrantedAuthority> userPermissions = new ArrayList<GrantedAuthority>();
	
	 	LOG.debug("Login User Name: " + loginId);
	 	List<UperUser>  users =   uperUserRepository.getUserByLoginid(loginId);
	 
	 	GrantedAuthority grantedAuthority;
	 	if (!users.isEmpty()){
	 		 UperUser systemUser = users.get(0);
	 		 grantedAuthority  = new GrantedAuthorityImpl(systemUser.getRole().getRoleType().toString());
	 	 }else{
	 		  grantedAuthority= new GrantedAuthorityImpl(RoleType.ROLE_NOT_AUTHORIZED.toString());
	 	 }
	  
	 
		 userPermissions.add(grantedAuthority);
		 UperUserDetails userDetails = new UperUserDetails(loginId,"", true, true, true, true,userPermissions );
		 
		return userDetails;
	}

}
