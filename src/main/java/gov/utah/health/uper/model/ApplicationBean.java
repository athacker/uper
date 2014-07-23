package gov.utah.health.uper.model;

public class ApplicationBean {

	private int patientApplicationId;
	private String applicationDate;
	private String applicationStatus;
	private String applicationType;
	private int patientId;
	private String patientFirstName;
	private String patientLastName;
	private boolean isMinor;
	

	private String stateFileNumber;
	private String securityPaperNumber;
	private String issuedDate;
	private String expirationDate;

	private boolean paymentReceived;
	private String checkMoneyOrderNumber;
	private String paymentType;
	
	
	public int getPatientApplicationId() {
		return patientApplicationId;
	}
	public void setPatientApplicationId(int patientApplicationId) {
		this.patientApplicationId = patientApplicationId;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getStateFileNumber() {
		return stateFileNumber;
	}
	public void setStateFileNumber(String stateFileNumber) {
		this.stateFileNumber = stateFileNumber;
	}
	public String getSecurityPaperNumber() {
		return securityPaperNumber;
	}
	public void setSecurityPaperNumber(String securityPaperNumber) {
		this.securityPaperNumber = securityPaperNumber;
	}
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isPaymentReceived() {
		return paymentReceived;
	}
	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	public String getCheckMoneyOrderNumber() {
		return checkMoneyOrderNumber;
	}
	public void setCheckMoneyOrderNumber(String checkMoneyOrderNumber) {
		this.checkMoneyOrderNumber = checkMoneyOrderNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isMinor() {
		return isMinor;
	}
	public void setMinor(boolean isMinor) {
		this.isMinor = isMinor;
	}

	
	
	
	
	
}
