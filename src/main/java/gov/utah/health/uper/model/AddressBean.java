package gov.utah.health.uper.model;

public class AddressBean {
	
 	private boolean isValidZip;
	private String zipCode;
	private String city;
	private String state;
	private boolean isParent;
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isValidZip() {
		return isValidZip;
	}
	public void setValidZip(boolean isValidZip) {
		this.isValidZip = isValidZip;
	}
	 
	
 
	
	
	
}
