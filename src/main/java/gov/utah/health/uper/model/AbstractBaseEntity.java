package gov.utah.health.uper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;

	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedDate;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "ADDED_USER_ID")
	private String addedUserId;

	@Column(name = "UPDATED_USER_ID")
	private String updatedUserId;

	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddedUserId() {
		return addedUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
 

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

 
	@PrePersist
	void prePersist() {
		 createdDate = new Date();
		 preUpdate();
	}

	@PreUpdate
	void preUpdate() {
		 updatedDate = new Date();
	}

	public void setAddedUserId(String addedUserId) {
		this.addedUserId = addedUserId;
	}
 

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

 
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

}
