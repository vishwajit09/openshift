/**
 * 
 */
package com.vis.app.ws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.vis.app.ws.model.AuthenticationDetails;
import com.vis.app.ws.model.LoginCredintials;
import com.vis.app.ws.service.AuthenticationService;
import com.vis.app.ws.service.impl.AuthenticationServiceImpl;
import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 *
 */
@Path("/authentication")
public class AuthenticationEntryPoint {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public AuthenticationDetails userLogin(LoginCredintials logincredintials) {
		AuthenticationDetails returnValue = new AuthenticationDetails();
		
		AuthenticationService authenticationService = new AuthenticationServiceImpl();
		UserDTO authenticateUser = authenticationService.authenticate(logincredintials.getUserName(), logincredintials.getUserPassword());
		
		//Reset the token every time user login ie generate a new token
		authenticationService.resetSecurityCredential(logincredintials.getUserPassword(),authenticateUser);
		
		
		String accessToken  = authenticationService.issueAccessToken(authenticateUser);
		returnValue.setId(authenticateUser.getUserId());
		returnValue.setToken(accessToken);
		
		return returnValue;
	}

}
