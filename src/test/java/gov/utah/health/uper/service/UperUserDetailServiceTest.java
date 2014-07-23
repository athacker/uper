package gov.utah.health.uper.service;

import java.util.ArrayList;

import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.repository.UperUserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gov.utah.health.uper.service.UperUserDetailsService;


@RunWith(MockitoJUnitRunner.class)
public class UperUserDetailServiceTest {
	
	
	
	@InjectMocks
	private UperUserDetailsService hempRegUserDetailService = new UperUserDetailsService();
	
	
	@Mock UperUserRepository uperUserRepository;
	
	private UperUser uperUser;
	private static final String USER="atest";
	
	@Before
	public void setUp(){
		uperUser = new UperUser(USER, new Role(RoleType.ROLE_ADMIN));
	}
	
	
	
	
	
	@Test
	public void testLogin(){
		ArrayList<UperUser> test = new ArrayList<UperUser>();
		test.add(uperUser);
		Mockito.when(uperUserRepository.getUserByLoginid(Mockito.anyString())).thenReturn( test);
		UperUserDetails testUserDetails = (UperUserDetails)hempRegUserDetailService.loadUserByUsername(USER);
		Assert.assertEquals(testUserDetails.getUsername(), USER );
	}
	
}
