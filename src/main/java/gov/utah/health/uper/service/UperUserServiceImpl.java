package gov.utah.health.uper.service;

import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.repository.RoleRepository;
import gov.utah.health.uper.repository.UperUserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("uperUserService")	
public class UperUserServiceImpl implements UperUserService {

	@Autowired private SecurityService securityService;
	@Autowired private UperUserRepository uperUserRepository;
 	@Autowired private RoleRepository roleRepository;
	
	@Override
	public UperUser getUperUserByLoginId(String loginId) {
		 return uperUserRepository.getUserByLoginid(loginId).get(0);
	}

	@Override
	public List<UperUser> getAllUsers() {
		return  uperUserRepository.findAll(new Sort(Sort.Direction.ASC, "lastName" ));
 	}

	@Override
	@Transactional
	public void saveUser(UperUser uperUser) {
		uperUser.setRole(roleRepository.findByRoleType(RoleType.ROLE_ADMIN));
		//find user by login name (to avoid duplicates)
		List<UperUser > uperUsers = uperUserRepository.getUserByLoginid(uperUser.getUserName());
		if (!uperUsers.isEmpty()){
			UperUser existingUser = (UperUser)uperUsers.get(0);
			uperUser.setId(existingUser.getId());
		}else{
			uperUser.setAddedUserId(securityService.getUserName());
			uperUser.setActive(Boolean.TRUE);
		}
		uperUser.setUpdatedUserId(securityService.getUserName());
		uperUserRepository.save(uperUser);
		 
	}

 

 

	
	
	
}
