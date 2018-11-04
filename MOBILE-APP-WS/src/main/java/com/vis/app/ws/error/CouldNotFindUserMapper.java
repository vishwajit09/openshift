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
public class CouldNotFindUserMapper implements ExceptionMapper<CouldNotFindUser> {

	public Response toResponse(CouldNotFindUser exception) {
		//
		ErrorMessageResponse errormsg = new ErrorMessageResponse(exception.getMessage(),
				ErrorMessages.RECORD_ALREADY_EXIST.name(), "https://www.google.com/");
		return Response.status(Response.Status.BAD_REQUEST).entity(errormsg).build();// since its builder we need to
																						// build the response
	}

}
