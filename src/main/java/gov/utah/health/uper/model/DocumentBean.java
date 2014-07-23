package gov.utah.health.uper.model;

public class DocumentBean {
	
	private int applicationId;
	private String stateFileNumber;
 
	private boolean H001;
	private boolean H003;
	private boolean H005;
	private boolean ID;
	private boolean DOC_RES;
	private boolean GUARD;
	
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getStateFileNumber() {
		return stateFileNumber;
	}
	public void setStateFileNumber(String stateFileNumber) {
		this.stateFileNumber = stateFileNumber;
	}
	public boolean isH001() {
		return H001;
	}
	public void setH001(boolean h001) {
		H001 = h001;
	}
	public boolean isH003() {
		return H003;
	}
	public void setH003(boolean h003) {
		H003 = h003;
	}
	public boolean isH005() {
		return H005;
	}
	public void setH005(boolean h005) {
		H005 = h005;
	}
	public boolean isID() {
		return ID;
	}
	public void setID(boolean iD) {
		ID = iD;
	}
	public boolean isDOC_RES() {
		return DOC_RES;
	}
	public void setDOC_RES(boolean dOC_RES) {
		DOC_RES = dOC_RES;
	}
	public boolean isGUARD() {
		return GUARD;
	}
	public void setGUARD(boolean gUARD) {
		GUARD = gUARD;
	}
	
	 
	

}
