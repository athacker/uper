package gov.utah.health.uper.controller;

import gov.utah.health.uper.model.UperPatient;
import gov.utah.health.uper.model.UperUser;
import gov.utah.health.uper.service.PatientApplicationService;
import gov.utah.health.uper.service.UperUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.thoughtworks.xstream.XStream;

  
 @Controller
 @RequestMapping("rest/")
public class WebServiceController {
	 
	 private static final Logger LOG = LoggerFactory.getLogger(WebServiceController.class);
	
	 @Autowired private PatientApplicationService patientApplicationService;
 	 @Autowired private UperUserService uperUserService;
	
	 /**
	  *  
	  * @param user
	  * @param password
	  * @param request
	  * @return
	  */
	 @RequestMapping(value = "authenticate/{user}/{password}", method = RequestMethod.GET )
	 public String authenticate(@PathVariable String user, @PathVariable String password, HttpServletRequest request ) {
	  
		 String  authenticationToken="";
		 UperUser sysUser = uperUserService.getUperUserByLoginId(user);
	 
		 if (null !=sysUser){
			  String key = UUID.randomUUID().toString().toUpperCase();
			  StandardPBEStringEncryptor jasypt = new  StandardPBEStringEncryptor ();
			  jasypt.setPassword(password);
			  authenticationToken=jasypt.encrypt(key) ;
			  request.getSession().setAttribute("token", authenticationToken);
			  LOG.info("User has been authenticated.");
		  }  
		
		 return "authenticated";
	 	   
 		}
	 
	 
	 
	 /**
	  * 
	  * @param lastName
	  * @param firstName
	  * @param request
	  * @return
	  */
	 @ResponseBody 
	 @ResponseStatus(HttpStatus.OK)
	 @RequestMapping(value = "patient/{lastName}/{firstName}", method = RequestMethod.GET, produces={"application/xml"})
	 public String patientsByName(  @PathVariable String lastName, @PathVariable String firstName, HttpServletRequest request    ) {
		  	 
		   	 XStream xstream = new XStream();
			 List<UperPatient> noPiiPatients = patientApplicationService.getNonPIIPatientsLikeName(lastName, firstName);
		 	 return xstream.toXML( noPiiPatients);
	}
	
	 
	 
	 
	 /**
	  * 
	  * @param stateFileNumber
	  * @param request
	  * @return
	  */
	 @ResponseBody
	 @ResponseStatus(HttpStatus.OK)
	 @RequestMapping(value="application/{stateFileNumber}",  method = RequestMethod.GET, produces={"application/xml"} )
	 public String getApplication(  @PathVariable String stateFileNumber, HttpServletRequest request  ){
 
		 XStream xstream = new XStream();
		 UperPatient bean;
		 
		 if (StringUtils.isEmpty(stateFileNumber )){
	    	 bean = new UperPatient();
	     }else{
	    	 bean= patientApplicationService.getApplByStateFileNumber(stateFileNumber);
	    	 
	     }
		
		 return xstream.toXML( bean);
	
	 }
 
	 
	 

}
  