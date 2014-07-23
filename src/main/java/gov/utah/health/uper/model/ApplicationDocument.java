package gov.utah.health.uper.model;

import gov.utah.health.uper.model.enums.DocumentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "APPLICATION_DOCUMENTS" )
public class ApplicationDocument extends AbstractBaseEntity {
	
	private static final long serialVersionUID = 7523657295622779988L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_ID")
	@JsonIgnore
	private PatientApplication patientApplication;
	
	@Column(name="APPLICATION_ID",   insertable = false, updatable = false)
	private Integer applicationId;
		
	@Column(name="DOCUMENT_TYPE")
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Column(name="DOCUMENTATION")
	@Lob
	private String document;

	public PatientApplication getPatientApplication() {
		return patientApplication;
	}

	public void setPatientApplication(PatientApplication patientApplication) {
		this.patientApplication = patientApplication;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	 
	
	
	
}
