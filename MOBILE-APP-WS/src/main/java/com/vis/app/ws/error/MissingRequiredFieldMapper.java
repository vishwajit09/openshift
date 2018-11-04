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
@Provider   //special to tell how to handle or exception as its has mapper
// every custom exception mapper need to implement ExceptionMapper. it generic so we need to map ExceptionMapper<MissingRequiredFieldException> as well as in response . and build the response 
public class MissingRequiredFieldMapper implements ExceptionMapper<MissingRequiredFieldException>{

	public Response toResponse(MissingRequiredFieldException exception) {
		//
		ErrorMessageResponse  errormsg = new ErrorMessageResponse(exception.getMessage(),
				                                 ErrorMessages.MISSING_REQUIRED_FIELD.name(),
				                                 "https://www.google.com/");
		return Response.status(Response.Status.BAD_REQUEST).entity(errormsg).build();//since its builder we need to build the response
	}

}
