package gov.utah.health.uper.controller;

import gov.utah.health.uper.model.Patient;
import gov.utah.health.uper.model.PatientBean;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.service.DocumentService;
import gov.utah.health.uper.service.PatientService;
import gov.utah.health.uper.service.SecurityService;
import gov.utah.health.uper.view.ClientCommand;
import gov.utah.health.uper.view.ClientCommandType;
import gov.utah.health.uper.view.Message;
import gov.utah.health.uper.view.Response;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired private PatientService patientService;
	@Autowired private SecurityService securityService;
	
	/**
	 * Save Patient Data on patientDetail.jsp save
	 * @param patientBean
	 * @return
	 */
	@RequestMapping(value = "patient", method = RequestMethod.POST)
	public @ResponseBody Response savePatient(@RequestBody PatientBean patientBean) {
	 	Response response = new Response();
 
	 	try{
		 	PatientBean bean = new PatientBean();
		 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
		 		bean = patientService.savePatient(patientBean);
		 	}	
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onPatientSaved",  	bean));
			response.addCommand(new ClientCommand(ClientCommandType.PROPERTY,"patient", bean ));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Success!","Patient Data has been saved.",  Message.STYLE_SUCCESS)));
		}catch(Exception e){
			LOG.error("An issue was encountered while saving patient data.",e);
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onPatientSaveError", patientBean));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Failure!","An issue was encountered while saving patient data.",  Message.STYLE_ERROR)));
		}
		 
		return response;
	}
		
	/**
	 * Patient Search Modal
	 * @param lastName
	 * @param firstName
	 * @return
	 */
	@RequestMapping(value = "patient/{lastName}/{firstName}", method = RequestMethod.GET)
	public @ResponseBody Response nameCheck(@PathVariable String lastName, @PathVariable String firstName   ) {
	 	Response response = new Response();
	 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
	 		List<Patient> patients= patientService.getPatientsLikeName(lastName, firstName);
	 		response.addCommand(new ClientCommand(ClientCommandType.METHOD,"onNameCheck", patients));
	 	}	
		return response;
	}
	
	
	
	/**
	 * 
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "patient/{patientId}", method = RequestMethod.GET)
	public @ResponseBody Response getPatient(@PathVariable String patientId   ) {
	 	Response response = new Response();
	 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
	 		PatientBean  patient= patientService.getPatient(Integer.parseInt( patientId  ) );
	 		response.addCommand(new ClientCommand(ClientCommandType.PROPERTY,"patient", patient ));
	 	}	
	  	return response;
	}
	
	
	
	
	
	
	
	
}
