package gov.utah.health.uper.model;

import gov.utah.health.uper.model.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UPER_USER")
public class UperUser extends AbstractBaseEntity {

	private static final long serialVersionUID = 752388956228862777L;

	public UperUser() {
		super();
	}

	public UperUser(String name,  Role aRole) {
		userName = name;
		role = aRole;
	}

 

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private  Role role;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "ACTIVE", nullable = false,  columnDefinition="bit")
	private Boolean active;



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

 	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	
}
