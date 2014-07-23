package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.PatientApplication;
import gov.utah.health.uper.model.StateFileNumber;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository("patientApplicationRepository")
@PersistenceContext(unitName="punit")
public interface PatientApplicationRepository extends JpaRepository<PatientApplication, Integer> {
	
	@Query("Select a from PatientApplication a where UPPER(a.patient.patientLastName) like :lastName and UPPER(a.patient.patientFirstName ) like :firstName")
	List<PatientApplication> getPatientsLikeName( @Param("lastName") String lastName, @Param("firstName")String firstName );
	
	@Query("Select a.stateFileNumber.stateFileNumber from PatientApplication a where a.patientId=:patientId")
	List<String> getStateFileNumbersForPatient( @Param("patientId") Integer patientId);
	
	@Query("Select a from PatientApplication a where a.patientId=:patientId")
	List<PatientApplication> getApplicationsForPatient( @Param("patientId") Integer patientId);
	
	
	
	 
}
