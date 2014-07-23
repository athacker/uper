package gov.utah.health.uper.controller;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.PatientBean;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.service.PatientApplicationService;
import gov.utah.health.uper.service.PatientService;
import gov.utah.health.uper.service.SecurityService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FormController {
	
	@Autowired private PatientApplicationService patientApplicationService;
	@Autowired private PatientService patientService;
	@Autowired private SecurityService securityService;
	
	
	
	/**
	 * Prints .pdf Utah Plant Extract Registration Form
	 * @param stateFileNumber
	 * @return
	 */
	@RequestMapping(value = "/printApplication/{stateFileNumber}", method = RequestMethod.GET)
	protected ModelAndView printApplication(@PathVariable String stateFileNumber) {
			
		Map<String,Object> formData = new HashMap<String,Object>();
		ApplicationBean app = patientApplicationService.getApplication(stateFileNumber);
		PatientBean pt =  patientService.getPatient(app.getPatientId() );
		if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
			formData.put("application",app );
			formData.put("patient", pt );
		}	
		//registration is a bean in views.xml -- Registration
		return new ModelAndView("registration","formData", formData);
		 
	}
	
	/**
	 * Prints .xls Management Report.
	 * @return
	 */
	@RequestMapping(value = "/applicationReport", method = RequestMethod.GET)
	protected ModelAndView applicationReport() {
			
		Map<String,Object> formData = new HashMap<String,Object>();
		if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
			formData.put("applications", patientApplicationService.getPtApplications() );
		}
		//applicationReport is a bean in views.xml -- ExcelApplicationReportView
		return new ModelAndView("applicationReport","formData",formData);
	}
	
	
	
	
}
