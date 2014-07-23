package gov.utah.health.uper.reports;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.PatientBean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfWriter;

public class Registration extends AbstractPdfView{
	private static Logger LOG = LoggerFactory.getLogger( Registration.class);
	private static Font subFont12 = new Font(Font.HELVETICA, 12, Font.BOLD);
 
	private static Font normalFontBold  = new Font(Font.HELVETICA, 10, Font.BOLD);
	
	private static Font footerFont = new Font(Font.HELVETICA, 8, Font.NORMAL);
	private static Font footerFontBold  = new Font(Font.HELVETICA, 8, Font.BOLD);
	
	private static final String PATIENT="Patient";
	private static final String PARENT="Parent/Guardian (if Patient is under age 18 at time of registration)";
	private static final String PHYSICIAN="Supervising Physician";
	
	private static final String FOOTER= "The above named patient is registered with the Office of Vital Records and Statistics as a person subject to S25-55-103 and S58-37.4.3 Utah Code Annotated 1953. Any changes to the above information should be reported to the Office of Vital Records and Statistics, Utah Department of Health, PO Box 141012, Salt Lake City, UT 84114-1012.";
	private static final String FOOTER_WARN="VALID ONLY IN UTAH";
	private static final String FOOTER_WARNING="ANY ALTERATIONS OR ERASURE VOIDS THIS CERTIFICATE";
	
 
	@Override
	protected void buildPdfDocument(Map<String, Object> model, 	Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response)throws DocumentException {
		try{ 
			
			Map<String,Object> dataCollections = (Map<String,Object> ) model.get("formData");
		 	ApplicationBean app = (ApplicationBean)dataCollections.get("application");
			PatientBean pt = (PatientBean)dataCollections.get("patient");
			setUpPage(document);
		    buildHeader(document, app);
		    buildPatient(document, pt);
		    buildParent(document, pt);
		    buildPhysician(document, pt);
		    buildFooter(document);
			writer.setOpenAction(PdfAction.gotoLocalPage(1, new PdfDestination( PdfDestination.XYZ, 0, 10000, 1), writer));
		}catch(Exception e){
			LOG.error("Exception was caught while preparing UPER Registration.", e);
		}
	}
	
	
	/**
	 * Set up spacing on page
	 * @param document
	 */
	private void setUpPage(Document document){
		document.setPageSize(PageSize.NOTE);
		//left, right, top, bottom
        document.setMargins(100, 100, 100, 100);
        document.open();
        
	}
	
	/**
	 * help method to add spaces between sections
	 * @param document
	 * @throws DocumentException
	 */
	private void addSpacing(Document document)throws DocumentException{
	    document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
    }
	
	
	
	/**
	 * Builds Header Information
	 * @param document
	 * @throws DocumentException
	 */
	private void buildHeader(Document document, ApplicationBean app) throws DocumentException{
 
	    Paragraph preface = new Paragraph("Utah Plant Extract Registry", subFont12 ); 
	    preface.setAlignment(Element.ALIGN_CENTER);
	    document.add( preface );
	   
	    
	    preface = new Paragraph("Issue Date:  ", subFont12); 
	    preface.add(app.getIssuedDate());
	    preface.setAlignment(Element.ALIGN_CENTER);
	    document.add( preface );
	    
	    preface = new Paragraph("Expiration Date:  " ,subFont12); 
	    preface.add(app.getExpirationDate());
	    
	    preface.setAlignment(Element.ALIGN_CENTER);
	    document.add( preface );
	    
	    preface = new Paragraph("Registration Number: ",  subFont12); 
	    preface.add(app.getStateFileNumber() );
	    preface.setAlignment(Element.ALIGN_CENTER);
	    document.add( preface );
	    addSpacing(document);
 	    
 }

	
	
	private void buildPatient(Document document, PatientBean pt)throws DocumentException{
		 Paragraph preface = new Paragraph(PATIENT, normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Name: ", normalFontBold); 
	     preface.add(pt.getFirstName() + " " + pt.getLastName() );
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     
	     
	     preface = new Paragraph("Date of Birth: ", normalFontBold); 
	 	 preface.add(pt.getDob());
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Address: ", normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     StringBuilder sb = new StringBuilder(pt.getAddressCurrent());
	     sb.append(" ");
	     sb.append(pt.getCityCurrent());
	     sb.append(", ");
	     sb.append(pt.getStateCurrent());
	     sb.append(" ");
	     sb.append(pt.getZipCurrent());
	    
	     preface.add(sb.toString());
	     document.add( preface );
		 addSpacing(document);
	     
	     
	 }
	
	
	private void buildParent(Document document, PatientBean pt)throws DocumentException{
		 Paragraph preface = new Paragraph(PARENT, normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Name: ", normalFontBold); 
	     preface.add(pt.getParentFirstName() + " " + pt.getParentLastName() );
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Date of Birth: ", normalFontBold); 
	     preface.add(pt.getParentDob()   );
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Address: ", normalFontBold); 
	     StringBuilder sb = new StringBuilder(pt.getParentAddressCurrent());
	     sb.append(" ");
	     sb.append(pt.getParentCityCurrent());
	     sb.append(", ");
	     sb.append(pt.getParentStateCurrent());
	     sb.append(" ");
	     sb.append(pt.getParentZipCurrent());
	    
	     preface.add(sb.toString());
	     
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     addSpacing(document);
	     
	}
	
	private void buildPhysician(Document document, PatientBean pt)throws DocumentException{
		 Paragraph preface = new Paragraph(PHYSICIAN, normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Name and Title: ", normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     preface.add(pt.getPhysicianName());
	     document.add( preface );
	     
	     preface = new Paragraph("Utah License Number: ", normalFontBold); 
	     preface.add(pt.getDoplNumber());
	     preface.setAlignment(Element.ALIGN_LEFT);
	     document.add( preface );
	     
	     preface = new Paragraph("Expiration Date: ", normalFontBold); 
	     preface.setAlignment(Element.ALIGN_LEFT);
	     preface.add(pt.getLicenseExpireDate());
	     document.add( preface );
		 addSpacing(document);
	     
	}
	
	
	/**
	 * Builds Footer Information
	 * @param document
	 * @throws DocumentException
	 */
	private void buildFooter(Document document)throws DocumentException{

		 Paragraph preface = new Paragraph(FOOTER, footerFont); 
	     preface.setAlignment(Element.ALIGN_CENTER);
	     document.add( preface );
	     document.add( Chunk.NEWLINE );
	     preface = new Paragraph(FOOTER_WARN, footerFontBold); 
	     preface.setAlignment(Element.ALIGN_CENTER);
	     document.add( preface );
	     
	     preface = new Paragraph(FOOTER_WARNING, footerFontBold); 
	     preface.setAlignment(Element.ALIGN_CENTER);
	     document.add( preface );
		
	}
	
	

}
