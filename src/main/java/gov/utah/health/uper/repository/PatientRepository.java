package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.Patient;
import java.util.List;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository("patientRepository")
@PersistenceContext(unitName="punit")
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@Query("Select p from Patient p where UPPER(p.patientLastName) like :lastName and UPPER(p.patientFirstName ) like :firstName")
	List<Patient> getPatientsLikeName(@Param("lastName") String lastName, @Param("firstName")String firstName  );
}
