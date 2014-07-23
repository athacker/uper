package gov.utah.health.uper.reports;

import gov.utah.health.uper.model.PatientApplication;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelApplicationReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> formData = (Map<String,Object>) model.get("formData");
		
		List<PatientApplication >applications = (List<PatientApplication >)formData.get("applications");
		
		//misc formatting
		CellStyle dateFormat = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		dateFormat.setDataFormat(  createHelper.createDataFormat().getFormat("m/d/yy"));
		
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("UPER Applications");
	
		
		int rowNum = 1;
		HSSFRow row = sheet.createRow(rowNum++);
		//PATIENT DATA
		row.createCell(0).setCellValue("Patient First Name");
		row.createCell(1).setCellValue("Patient Last Name");
		row.createCell(3).setCellValue("DOB Patient");
		row.createCell(4).setCellValue("Physician Name");
		row.createCell(5).setCellValue("DOPL ID");
		
		//APPLICATION DATA		
		row.createCell(6).setCellValue("Application Status");
		row.createCell(7).setCellValue("State File Number");
		row.createCell(8).setCellValue("Certified Paper");
		row.createCell(9).setCellValue("Issue Date");
		row.createCell(10).setCellValue("Expiration Date" );
		
		
		for (PatientApplication pa:applications){
			row = sheet.createRow(rowNum++);
			//PATIENT DATA
			row.createCell(0).setCellValue(pa.getPatient().getPatientFirstName());
			row.createCell(1).setCellValue(pa.getPatient().getPatientLastName());
			Cell cell = row.createCell(3); 
			cell.setCellStyle(dateFormat);
			cell.setCellValue(pa.getPatient().getPatientDob());
	
			row.createCell(4).setCellValue(pa.getPatient().getPhysicianName() );
			row.createCell(5).setCellValue(pa.getPatient().getDoplNumber());
			
			
			
			//APPLICATION DATA
			row.createCell(6).setCellValue(pa.getApplicationStatus().toString());
			row.createCell(7).setCellValue(pa.getStateFileNumber().getStateFileNumber());
			row.createCell(8).setCellValue(pa.getSecurityPaperNumber());
			if (null !=pa.getIssuedDate()  ){
				cell = row.createCell(9);
				cell.setCellStyle(dateFormat);
				cell.setCellValue(pa.getIssuedDate()  );
			}
			if (null !=pa.getExpirationDate()  ){
				cell =row.createCell(10);
				cell.setCellStyle(dateFormat);
				cell.setCellValue(pa.getExpirationDate() );
			}
			
		 
			}

	
	
		//autosize columns
		for(int i=0; i<10; i++){
			sheet.autoSizeColumn(i);
		}	
	
	
	
	
	}
		
		
	



}


