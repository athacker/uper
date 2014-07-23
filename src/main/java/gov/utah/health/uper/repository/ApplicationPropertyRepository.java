package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.ApplicationProperty;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("applicationPropertyRepository")
@PersistenceContext(unitName="punit")
public interface ApplicationPropertyRepository extends JpaRepository<ApplicationProperty, Integer>  {
	
	
	ApplicationProperty findByName(String name);
}
