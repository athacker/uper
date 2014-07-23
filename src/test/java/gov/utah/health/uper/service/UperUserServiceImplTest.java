package gov.utah.health.uper.service;

import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.repository.RoleRepository;
import gov.utah.health.uper.repository.UperUserRepository;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class UperUserServiceImplTest {
	
	@InjectMocks 
	UperUserService uperUserService  = new UperUserServiceImpl();
		
	@Mock private SecurityService securityService;
	@Mock private RoleRepository roleRepository;
	@Mock private UperUserRepository uperUserRepository;
	
	
	private UperUser uperUser;
	private static final String USER_NAME="dmartin";
		
	@Before
	public void setUp(){
		uperUser = new UperUser();
		uperUser.setUserName(USER_NAME);
	}
	
	
	@Test
	public void testGetUperUserByLoginId(){
		ArrayList<UperUser> test = new ArrayList<UperUser>();
		test.add(uperUser);
		Mockito.when( uperUserRepository.getUserByLoginid(USER_NAME) ).thenReturn(test);
		UperUser result = uperUserService.getUperUserByLoginId(USER_NAME);
		Assert.assertEquals(result.getUserName(), USER_NAME);
		Mockito.verify(uperUserRepository, Mockito.atLeastOnce() ).getUserByLoginid( USER_NAME);
	}
	
	
	@Test
	public void testGetAllUser(){
		List<UperUser> result = uperUserService.getAllUsers();
		Assert.assertNotNull(result);
	 }
	
	@Test
	public void testSaveUser(){
		Mockito.when(roleRepository.findByRoleType(RoleType.ROLE_ADMIN) ).thenReturn(new Role(RoleType.ROLE_ADMIN));
		uperUserService.saveUser(uperUser);
		Mockito.verify(uperUserRepository, Mockito.atLeastOnce()).save((UperUser)Mockito.anyObject());
	}
	
	
}
