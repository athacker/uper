package gov.utah.health.uper.model;

import gov.utah.health.uper.model.enums.Gender;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "PATIENT" )
public class Patient extends AbstractBaseEntity{

	private static final long serialVersionUID = 7523882255622779988L;
	
	@Column(name="PATIENT_GENDER" )
	@Enumerated(EnumType.STRING)
	private Gender gender;
  
	
	@Column(name="PATIENT_FIRST_NAME" )
	private String patientFirstName;
	
	@Column(name="PATIENT_MIDDLE_NAME" )
	private String patientMiddleName;
	
	@Column(name="PATIENT_LAST_NAME" )
	private String patientLastName;
 
	@Column(name="PATIENT_DOB" )
	@Temporal(TemporalType.DATE)
	private Date patientDob;
	
	@Column(name="PATIENT_ADDRESS")
	private String patientAddress;
	
	@Column(name="PATIENT_CITY")
	private String patientCity;
	
	@Column(name="PATIENT_STATE")
	private String patientState;
	
	@Column(name="PATIENT_ZIP")
	private String patientZip;
		
	@Column(name="PARENT_FIRST_NAME" )
	private String parentFirstName;
	
	@Column(name="PARENT_MIDDLE_NAME" )
	private String parentMiddleName;
	
	@Column(name="PARENT_LAST_NAME" )
	private String parentLastName;
 
	@Column(name="PARENT_DOB" )
	@Temporal(TemporalType.DATE)
	private Date parentDob;
	
	@Column(name="PARENT_ADDRESS")
	private String parentAddress;
	
	@Column(name="PARENT_CITY")
	private String parentCity;
	
	@Column(name="PARENT_STATE")
	private String parentState;
	
	@Column(name="PARENT_ZIP")
	private String parentZip;
	
	@Column(name="PHYSICIAN_NAME")
	private String physicianName;
	
	@Column(name="PHYSICIAN_BOARD_CERTIFIED", columnDefinition="bit")
	private Boolean isPhysicianBoardCertified = false;
	
	@Column(name="PHYSICIAN_TITLE")
	private String physicianTitle;
	
	@Column(name="PHYSICIAN_DOPL")
	private String doplNumber;

	@Column(name="PHYSICIAN_LICENSE_EXPIRE_DATE")
	private Date licenseExpireDate;

	
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientMiddleName() {
		return patientMiddleName;
	}

	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public Date getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientCity() {
		return patientCity;
	}

	public void setPatientCity(String patientCity) {
		this.patientCity = patientCity;
	}

	public String getPatientState() {
		return patientState;
	}

	public void setPatientState(String patientState) {
		this.patientState = patientState;
	}

	public String getPatientZip() {
		return patientZip;
	}

	public void setPatientZip(String patientZip) {
		this.patientZip = patientZip;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	public Date getParentDob() {
		return parentDob;
	}

	public void setParentDob(Date parentDob) {
		this.parentDob = parentDob;
	}

	public String getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}

	public String getParentCity() {
		return parentCity;
	}

	public void setParentCity(String parentCity) {
		this.parentCity = parentCity;
	}

	public String getParentState() {
		return parentState;
	}

	public void setParentState(String parentState) {
		this.parentState = parentState;
	}

	public String getParentZip() {
		return parentZip;
	}

	public void setParentZip(String parentZip) {
		this.parentZip = parentZip;
	}

 
	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getPhysicianTitle() {
		return physicianTitle;
	}

	public void setPhysicianTitle(String physicianTitle) {
		this.physicianTitle = physicianTitle;
	}

	public String getDoplNumber() {
		return doplNumber;
	}

	public void setDoplNumber(String doplNumber) {
		this.doplNumber = doplNumber;  
	}

	public Date getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(Date licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

 
	public boolean isPhysicianBoardCertified() {
		return isPhysicianBoardCertified;
	}

	public void setPhysicianBoardCertified(boolean isPhysicianBoardCertified) {
		this.isPhysicianBoardCertified = isPhysicianBoardCertified;
	}

 
	
	
	
}
