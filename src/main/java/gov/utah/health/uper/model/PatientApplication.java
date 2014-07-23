package gov.utah.health.uper.model;

import gov.utah.health.uper.model.enums.ApplicationStatus;
import gov.utah.health.uper.model.enums.ApplicationType;
import gov.utah.health.uper.model.enums.PaymentType;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "PATIENT_APPLICATION")
public class PatientApplication extends AbstractBaseEntity {

	private static final long serialVersionUID = 7523882295655779988L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PATIENT_ID")
	@JsonIgnore
	private Patient patient;

	@Column(name = "PATIENT_ID", insertable = false, updatable = false)
	private Integer patientId;

	@Column(name = "APPLICATION_TYPE")
	@Enumerated(EnumType.STRING)
	private ApplicationType applicationType;

	@Column(name = "APPLICATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	@Column(name = "ISSUED_DATE")
	@Temporal(TemporalType.DATE)
	private Date issuedDate;

	@Column(name = "EXPIRATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "APPLICATION_STATUS")
	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "STATE_FILE_NUMBER_ID", unique = true)
	private StateFileNumber stateFileNumber;

	// number on paper the application is printed on.
	@Column(name = "SECURITY_PAPER_NUMBER")
	private String securityPaperNumber;

	@Column(name = "PAYMENT_TYPE")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Column(name = "PAYMENT_RECEIVED", columnDefinition="bit")
	private Boolean paymentReceived;

	@Column(name = "CHECK_MONEY_ORDER_NUMBER")
	private String checkMoneyOrderNumber;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public StateFileNumber getStateFileNumber() {
		return stateFileNumber;
	}

	public void setStateFileNumber(StateFileNumber stateFileNumber) {
		this.stateFileNumber = stateFileNumber;
	}

	public String getSecurityPaperNumber() {
		return securityPaperNumber;
	}

	public void setSecurityPaperNumber(String securityPaperNumber) {
		this.securityPaperNumber = securityPaperNumber;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(Boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public String getCheckMoneyOrderNumber() {
		return checkMoneyOrderNumber;
	}

	public void setCheckMoneyOrderNumber(String checkMoneyOrderNumber) {
		this.checkMoneyOrderNumber = checkMoneyOrderNumber;
	}

}
