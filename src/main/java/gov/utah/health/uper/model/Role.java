package gov.utah.health.uper.model;
 

import gov.utah.health.uper.model.enums.RoleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="ROLE")
 public class Role extends AbstractBaseEntity {
	private static final long serialVersionUID = 7523432295622776147L;
	public Role(){
		super();
	}
	
 
	
	public Role(RoleType roleType){
		 this.roleType=roleType;
	}
	
  
	@Column(name="ROLE_TYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	
	
	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return roleType.toString();
	}


	
	 
	
	 
	 
	
	
	
}

