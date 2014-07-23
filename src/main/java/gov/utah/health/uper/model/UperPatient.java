package gov.utah.health.uper.model;

public class UperPatient {
	
	private String firstName;
	private String lastName;
	private String stateFileNumber;
	private String issueDate;
	private String expiratonDate;
	private String applicationStatus;
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
	public String getStateFileNumber() {
		return stateFileNumber;
	}
	public void setStateFileNumber(String stateFileNumber) {
		this.stateFileNumber = stateFileNumber;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpiratonDate() {
		return expiratonDate;
	}
	public void setExpiratonDate(String expiratonDate) {
		this.expiratonDate = expiratonDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	

}
