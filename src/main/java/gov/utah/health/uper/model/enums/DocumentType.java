package gov.utah.health.uper.model.enums;

public enum DocumentType {
	 
	H001("Application for Reg Card Under 18"),
	H003("Patient Evaluation Record"),
	H005("Application for Reg"),
	ID("State Id"),
	DOC_RES("Photocopy of Secondary documentation proving residency (in case we're provided an out-of-state DL or ID as proof of identity)"),
	GUARD("Photocopy of court order if patient has a legal guardian. This will happen regardless of the age of the patient. We will have some adult patients who have a guardian and want that guardian's name on the certificate");
	 
	 	 
	private String label;
	private DocumentType(String myLabel){
		label=myLabel;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	 
	 
	 
	 
	 
	 
}
