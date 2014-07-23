package gov.utah.health.uper.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
@Table(name = "STATE_FILE_NUMBER" )
@SuppressWarnings("serial") 
public class StateFileNumber extends AbstractBaseEntity {

	@Version int version;
	
	@Column(name="state_file_number", unique=true )
	private String stateFileNumber;
	
	@Column(name="counter", unique=true )
	private int counter;
	
	@Column(name="issue_date" )
	@Temporal(TemporalType.DATE)
	private Date issueDate;
		
	 
	@OneToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(name="application_id" )
	private PatientApplication patientApplication;
		
	@Column(name="application_id", updatable=false, insertable=false)
	private Integer patientApplicationId;
	
	
	public String getStateFileNumber() {
		return stateFileNumber;
	}

	public void setStateFileNumber(String stateFileNumber) {
		this.stateFileNumber = stateFileNumber;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public PatientApplication getPatientApplication() {
		return patientApplication;
	}

	public void setPatientApplication(PatientApplication patientApplication) {
		this.patientApplication = patientApplication;
	}

	public Integer getPatientApplicationId() {
		return patientApplicationId;
	}

	public void setPatientApplicationId(Integer patientApplicationId) {
		this.patientApplicationId = patientApplicationId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

 
 
	
	
	
	
	
	
	
}
