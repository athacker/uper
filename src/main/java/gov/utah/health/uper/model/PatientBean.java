package gov.utah.health.uper.model;

import java.util.List;

public class PatientBean {
	
	private int patientId;
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String dob;
	private boolean isMinor;
	
	private String addressCurrent;
	private String cityCurrent;
	private String stateCurrent;
	private String zipCurrent;
	
	private String parentMiddleName="";
	private String parentLastName="";
	private String parentFirstName="";
	private String parentDob="";
	private String parentAddressCurrent="";
	private String parentCityCurrent="";
	private String parentStateCurrent="";
	private String parentZipCurrent="";
	
	private String physicianName;
	private boolean isPhysicianBoardCertified=false;
	private String doplNumber;
	private String licenseExpireDate;
	
	private List<String> stateFileNumbers;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean isMinor() {
		return isMinor;
	}

	public void setMinor(boolean isMinor) {
		this.isMinor = isMinor;
	}

	public String getAddressCurrent() {
		return addressCurrent;
	}

	public void setAddressCurrent(String addressCurrent) {
		this.addressCurrent = addressCurrent;
	}

	public String getCityCurrent() {
		return cityCurrent;
	}

	public void setCityCurrent(String cityCurrent) {
		this.cityCurrent = cityCurrent;
	}

	public String getStateCurrent() {
		return stateCurrent;
	}

	public void setStateCurrent(String stateCurrent) {
		this.stateCurrent = stateCurrent;
	}

	public String getZipCurrent() {
		return zipCurrent;
	}

	public void setZipCurrent(String zipCurrent) {
		this.zipCurrent = zipCurrent;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentDob() {
		return parentDob;
	}

	public void setParentDob(String parentDob) {
		this.parentDob = parentDob;
	}

	public String getParentAddressCurrent() {
		return parentAddressCurrent;
	}

	public void setParentAddressCurrent(String parentAddressCurrent) {
		this.parentAddressCurrent = parentAddressCurrent;
	}

	public String getParentCityCurrent() {
		return parentCityCurrent;
	}

	public void setParentCityCurrent(String parentCityCurrent) {
		this.parentCityCurrent = parentCityCurrent;
	}

	public String getParentStateCurrent() {
		return parentStateCurrent;
	}

	public void setParentStateCurrent(String parentStateCurrent) {
		this.parentStateCurrent = parentStateCurrent;
	}

	public String getParentZipCurrent() {
		return parentZipCurrent;
	}

	public void setParentZipCurrent(String parentZipCurrent) {
		this.parentZipCurrent = parentZipCurrent;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public boolean isPhysicianBoardCertified() {
		return isPhysicianBoardCertified;
	}

	public void setPhysicianBoardCertified(boolean isPhysicianBoardCertified) {
		this.isPhysicianBoardCertified = isPhysicianBoardCertified;
	}

	public String getDoplNumber() {
		return doplNumber;
	}

	public void setDoplNumber(String doplNumber) {
		this.doplNumber = doplNumber;
	}

	public String getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(String licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

	public List<String> getStateFileNumbers() {
		return stateFileNumbers;
	}

	public void setStateFileNumbers(List<String> stateFileNumbers) {
		this.stateFileNumbers = stateFileNumbers;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}
	

	
	
	

}
