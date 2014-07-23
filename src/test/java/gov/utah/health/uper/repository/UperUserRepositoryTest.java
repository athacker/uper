package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.UperUser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-Test.xml")
@Transactional
public class UperUserRepositoryTest {
	
	@Autowired private UperUserRepository uperUserRepository;
	 
	
	@Test
	public void testFindAll(){
		List<UperUser> users =  uperUserRepository.findAll();
 	}
	
	@Test
	public void testFindByLoginId(){
		List<UperUser> users =  uperUserRepository.findAll();
		if( !users.isEmpty()){
			List<UperUser> systemUser =uperUserRepository.getUserByLoginid(users.get(0).getUserName());
			Assert.assertNotNull(systemUser);
		}	
	}
	 
}
