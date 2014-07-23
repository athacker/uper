package gov.utah.health.uper.controller;

import gov.utah.health.uper.model.ApplicationBean;
import gov.utah.health.uper.model.enums.PaymentType;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.service.DocumentService;
import gov.utah.health.uper.service.PatientApplicationService;
import gov.utah.health.uper.service.SecurityService;
import gov.utah.health.uper.view.ClientCommand;
import gov.utah.health.uper.view.ClientCommandType;
import gov.utah.health.uper.view.Message;
import gov.utah.health.uper.view.Response;

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
public class ApplicationController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired private DocumentService documentService;
	@Autowired private PatientApplicationService patientApplicationService;
	@Autowired private SecurityService securityService;
	
	@RequestMapping(value = "application", method = RequestMethod.POST, produces={"application/json"})
	public @ResponseBody Response createApplication(@RequestBody  String patientId) {
		Response response = new Response();
		ApplicationBean bean = new ApplicationBean();
		try{
			if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
				bean = patientApplicationService.createNewPatientApplication(Integer.parseInt( patientId)  );
			}
		 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationCreated", bean));
		 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Success!","New Application has been created.",  Message.STYLE_SUCCESS)));
		}catch(Exception e){
			LOG.error("An Exception was caught creating an new Application.", e);
		 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationLoadError", bean));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Failure!","An issue was encountered while creating a new Application.",  Message.STYLE_ERROR)));
		}
		return response;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param applicationId
	 * @return
	 */
	@RequestMapping(value = "application/{stateFileNumber}", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Response getApplication(@PathVariable String stateFileNumber) {
		Response response = new Response();
		ApplicationBean bean = new ApplicationBean();
		try{
			if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
				bean = patientApplicationService.getApplication( stateFileNumber  );
			}
		  	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationLoad", bean));
			response.addCommand(new ClientCommand(ClientCommandType.PROPERTY,"application", bean ));
			response.addCommand(new ClientCommand(ClientCommandType.PROPERTY,"documents", documentService.getDocumentsForApplication(stateFileNumber) ));
		}catch(Exception e){
			LOG.error("An Exception was caught retrieving an Application: getApplication.", e);
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationLoadError", bean));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Failure!","An issue was encountered while retrieving an Application.",  Message.STYLE_ERROR)));
		}
		return response;
	}
	
	
/**
 * 
 * @param bean
 * @return
 */
	@RequestMapping(value = "saveapplication", method = RequestMethod.POST, produces={"application/json"})
	public @ResponseBody Response saveApplication(@RequestBody ApplicationBean bean) {
		Response response = new Response();
		 
		try{
			if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
				bean = patientApplicationService.savePatientApplication(bean);
			}	
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Success!","Application was successfully updated.",  Message.STYLE_SUCCESS)));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationLoad", bean));
		}catch(Exception e){
			LOG.error("An Exception was caught creating an new Application.", e);
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onApplicationLoadError", bean));
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Failure!","An issue was encountered updating Application.",  Message.STYLE_ERROR)));
		}
		return response;
	}
	
	
	
	@RequestMapping(value = "document/{applicationId}/{documentId}", method = RequestMethod.GET)
	public @ResponseBody Response saveDocument(@PathVariable String applicationId, @PathVariable String documentId    ) {
	 	Response response = new Response();
	 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
	 		documentService.saveDocument(Integer.parseInt(applicationId),documentId);
	 	}	
	 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onDocumentChange",   documentId));
	 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
				, new Message("Success!","Document was successfully saved.",  Message.STYLE_SUCCESS)));
		return response;
	}
	
	
	
	
	@RequestMapping(value = "document/delete/{applicationId}/{documentId}", method = RequestMethod.GET)
	public @ResponseBody Response deleteDocument(@PathVariable String applicationId, @PathVariable String documentId   ) {
	 	Response response = new Response();
	 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
	 		documentService.deleteDocument(Integer.parseInt(applicationId), documentId);
	 	}	
	 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onDocumentChange",   documentId));
		response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
				, new Message("Success!","Document was successfully removed.",  Message.STYLE_SUCCESS)));
		return response;
	}
	
	
	
	@RequestMapping(value = "paymentType/{applicationId}/{paymentType}", method = RequestMethod.GET)
	public @ResponseBody Response savePaymentType(@PathVariable int applicationId, @PathVariable  String paymentType ) {
	 	Response response = new Response();
	 	LOG.debug("Payment Type:  " + paymentType);
	 	if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
	 		patientApplicationService.savePaymentType(applicationId, PaymentType.valueOf(paymentType));
	 	}	
	 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onPaymentChange",   ""));
		response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
				, new Message("Success!","Application Payment Type was successfully updated.",  Message.STYLE_SUCCESS)));
		return response;
	}
	
	
	@RequestMapping(value = "payment/{applicationId}/{isPaid}", method = RequestMethod.GET)
	public @ResponseBody Response payment(@PathVariable int applicationId, @PathVariable  boolean isPaid  ) {
	 	Response response = new Response();
	 	LOG.debug("is Paid:  " +isPaid );
		if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
			patientApplicationService.receivePayment(applicationId, isPaid );
		}	
	 	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "onPaymentChange",   ""));
		response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
				, new Message("Success!","Application has been successfully updated to Paid.",  Message.STYLE_SUCCESS)));
		return response;
	}
	
	
	
	
}
