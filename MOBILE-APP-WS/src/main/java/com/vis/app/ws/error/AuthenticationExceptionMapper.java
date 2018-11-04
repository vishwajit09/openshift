/**
 * 
 */
package com.vis.app.ws.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.vis.app.ws.model.ErrorMessageResponse;

/**
 * @author Visu
 *
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {
	
	public Response toResponse(AuthenticationException exception) {
		//
		ErrorMessageResponse  errormsg = new ErrorMessageResponse(exception.getMessage(),
				                                 ErrorMessages.AUTHENTICATION_FAILED.name(),
				                                 "https://www.google.com/");
		return Response.status(Response.Status.UNAUTHORIZED).
				entity(errormsg).
				build();//since its builder we need to build the response
	}

}
