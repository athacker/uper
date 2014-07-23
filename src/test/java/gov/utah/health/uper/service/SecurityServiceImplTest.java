package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.RoleType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceImplTest {

	@InjectMocks
	private SecurityService securityService = new SecurityServiceImpl();
	private static final String USER_NAME = "jjohnson";
	private UperUserDetails currentUser;
	
	@Before
	public void setUp(){
		Collection<GrantedAuthority>gas = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_ADMIN");
		gas.add(ga);
		currentUser = new UperUserDetails(USER_NAME,"password",true, false, false, false,gas);
	  }
 
    @Test
    public void testGetCurrentUser() throws Exception{
    	//test setup
     	Authentication authentication = Mockito.mock(Authentication.class);
    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(currentUser);
    	
    	UperUserDetails user = securityService.getCurrentUser();
    	
    	List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>(user.getAuthorities() );
    	Assert.assertEquals(gas.get(0).getAuthority(), RoleType.ROLE_ADMIN.toString());
          
     }
       
    
    @Test
    public void testGetCurrentRole(){
    	Collection<GrantedAuthority>gas = new ArrayList<GrantedAuthority>();
    	GrantedAuthority ga = new GrantedAuthorityImpl(RoleType.ROLE_ADMIN.toString());
		gas.add(ga);
		currentUser = new UperUserDetails("Admin","password",true, false, false, false,gas);
		
		//test setup
    	Authentication authentication = Mockito.mock(Authentication.class);
    
       	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
       	securityContext.setAuthentication(authentication);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
	
     	//run test
    	Role userRole = securityService.getCurrentRole();
    	 
    	
    }
    
    
    @Test
    public void testLogout() throws Exception{
    	Authentication authentication = Mockito.mock(Authentication.class);
    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(currentUser);
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	securityService.logout(request);
     }
    
    
    @Test
    public void testGetUserName() throws Exception{
    	//test setup
     	Authentication authentication = Mockito.mock(Authentication.class);
    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(currentUser);
    	
    	String userName = securityService.getUserName();
     
    	Assert.assertEquals(USER_NAME, userName);
    	
    }
    
     
}
