package gov.utah.health.uper.repository;



import gov.utah.health.uper.model.UperUser;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("uperUserRepository")
@PersistenceContext(unitName="punit")
public interface UperUserRepository extends JpaRepository<UperUser, Integer>{
		
	@Query("Select distinct s from UperUser s where s.userName = :loginId and s.active=1")
	List<UperUser> getUserByLoginid(@Param("loginId")String loginId);
	
}
