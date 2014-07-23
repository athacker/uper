package gov.utah.health.uper.controller;

import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.service.SecurityService;
import gov.utah.health.uper.service.UperUserService;
import gov.utah.health.uper.view.ClientCommand;
import gov.utah.health.uper.view.ClientCommandType;
import gov.utah.health.uper.view.Message;
import gov.utah.health.uper.view.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

	 @Autowired private UperUserService userService;
  	 @Autowired private SecurityService securityService;
	 
	@RequestMapping(value = "/user", method = RequestMethod.GET )
	public @ResponseBody Response getUsers() {
		Response response = new Response();
		if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
			response.addCommand(new ClientCommand(ClientCommandType.PROPERTY, "users", userService.getAllUsers() ));
		}	
	 	return response;
	}
	
 
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces={"application/json"})
	public @ResponseBody Response saveUser(@RequestBody UperUser user) {
		Response response = new Response();
		 
		try{
			if (RoleType.ROLE_ADMIN.equals(securityService.getCurrentRole().getRoleType())  ){
				userService.saveUser(user);
			}	
		  	response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Success!","User Details were successfully saved.",  Message.STYLE_SUCCESS)));
		  	response.addCommand(new ClientCommand(ClientCommandType.PROPERTY, "users", userService.getAllUsers() ));
		}catch(Exception e){
			LOG.error("An Exception was caught creating an new Application.", e);
			response.addCommand(new ClientCommand(ClientCommandType.METHOD, "addMessage"
					, new Message("Failure!","An issue was encountered while saving a user.",  Message.STYLE_ERROR)));
		}
		return response;
	}
	
	
	
	 
	
}
