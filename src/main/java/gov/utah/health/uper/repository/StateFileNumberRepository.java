package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.StateFileNumber;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("stateFileNumberRepository")
@PersistenceContext(unitName="punit")
public interface StateFileNumberRepository extends JpaRepository<StateFileNumber, Integer>  {

		 
	 	@Query("Select s from StateFileNumber s where s.counter=(select max(sf.counter) from StateFileNumber sf)")
	 	StateFileNumber getLastNumberIssued();
	 	
	 	@Query("Select s from StateFileNumber s where s.stateFileNumber = :stateFileNumber")
	 	StateFileNumber getByStateFileNumber( @Param("stateFileNumber") String stateFileNumber);
	
}
