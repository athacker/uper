package gov.utah.health.uper.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UperUserDetails extends User{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 111113L;

	/** The agency name. */
	private String agencyName;
	
	/** The name. */
	private String name;
	
	/** The id. */
	private Long id;
	
	/** The allowed agencies. */
	private String allowedAgencies;
	
	/** The agency. */
	private String agency;
	
	/** The admin. */
	private Boolean admin;
	
	public UperUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAllowedAgencies() {
		return allowedAgencies;
	}

	public void setAllowedAgencies(String allowedAgencies) {
		this.allowedAgencies = allowedAgencies;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
	
	
	
}
