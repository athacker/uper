package gov.utah.health.uper.repository;

import gov.utah.health.uper.model.ApplicationDocument;
import gov.utah.health.uper.model.enums.DocumentType;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("applicationDocumentRepository")
@PersistenceContext(unitName="punit")
public interface ApplicationDocumentRepository extends JpaRepository<ApplicationDocument, Integer> {

	
	@Modifying
	@Transactional
	@Query( "delete from ApplicationDocument p where p.applicationId=:applicationId and p.documentType = :documentType") 
	void deleteDocument(@Param("applicationId") Integer applicationId, @Param("documentType")DocumentType documentType);
	
	
	@Query("select d.documentType from ApplicationDocument d where d.patientApplication.stateFileNumber in (Select sfn.id from StateFileNumber sfn where sfn.stateFileNumber = :stateFileNumber)")
	List<DocumentType>getApplicationDocuments(@Param("stateFileNumber") String stateFileNumber);
	
}
