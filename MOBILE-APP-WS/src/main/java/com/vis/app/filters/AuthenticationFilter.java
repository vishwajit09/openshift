/**
 * 
 */
package com.vis.app.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.HttpHeaderReader;

import com.vis.app.annotation.Secured;
import com.vis.app.ws.error.AuthenticationException;
import com.vis.app.ws.service.UserService;
import com.vis.app.ws.service.impl.UserServiceImpl;
import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)

public class AuthenticationFilter  implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//Extract Header Details
		String authorizationHeader 
		              = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer")) {
			throw new AuthenticationException("Authorization header must be provider");
		}
		
		// Extract token 
		
		String token = authorizationHeader.substring("Bearer".length()).trim();
		
		//extract userid
		
		String userId = requestContext.getUriInfo().getPathParameters().getFirst("id");
		
		validateToken(token,userId);
	}

	private void validateToken(String token, String userId) throws AuthenticationException {
		
		//Get User profile details
		UserService userService = new UserServiceImpl();
		UserDTO userProfile = userService.getUser(userId);
		
		
	}

}
