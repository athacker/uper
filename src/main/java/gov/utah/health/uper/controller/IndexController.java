
package gov.utah.health.uper.controller;

 
import gov.utah.health.uper.model.AddressBean;
import gov.utah.health.uper.model.UperUserDetails;
import gov.utah.health.uper.model.enums.RoleType;
import gov.utah.health.uper.service.AddressService;
import gov.utah.health.uper.service.SecurityService;
import gov.utah.health.uper.view.ClientCommand;
import gov.utah.health.uper.view.ClientCommandType;
import gov.utah.health.uper.view.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *  
 * Main controller that starts application off and fowards user to index.jsp
 *
 */
@Controller
public class IndexController {
 
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired private SecurityService securityService;
	@Autowired private AddressService addressService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index( Model model) {
		ModelAndView mav;
		UperUserDetails u = securityService.getCurrentUser() ;
		if ( RoleType.ROLE_NOT_AUTHORIZED.equals(securityService.getCurrentRole().getRoleType() )){
			mav = new ModelAndView("notAuthorized");
			LOG.warn("User is not authorized.:  " +   u.getUsername()  );
		}else{
			mav = new ModelAndView("index");
			LOG.debug("Current Role:  " + securityService.getCurrentRole() );
		}	
		
		
	 	mav.addObject("currentUser", u.getUsername()  );
		mav.addObject("role", securityService.getCurrentRole() );
		
		return mav;
	}
	

	
	/**
	 * zip-code to pre-populate city state
	 * web service call
	 * @return
	 */
	@RequestMapping(value = "/address/{isParent}/{zip}", method = RequestMethod.GET)
	public @ResponseBody Response getCityState(@PathVariable boolean isParent, @PathVariable String zip) {
	 	Response response = new Response();
	 	AddressBean bean = addressService.getCityStateByZip(zip);
	 	bean.setParent(isParent);
 		response.addCommand(new ClientCommand(ClientCommandType.METHOD,"setCityState", bean ));
 		return response;
	}
	
	
	
 
}
