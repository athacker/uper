package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.Role;
import gov.utah.health.uper.model.enums.RoleType;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("roleRepository")
@PersistenceContext(unitName="punit")
public interface RoleRepository extends JpaRepository<Role, Integer>  {
	
	
	Role findByRoleType(RoleType roleType);
}
