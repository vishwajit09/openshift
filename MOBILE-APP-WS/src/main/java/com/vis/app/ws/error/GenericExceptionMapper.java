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
@Provider // to register in jax-rs
public class GenericExceptionMapper implements ExceptionMapper<Throwable>  {

	@Override
	public Response toResponse(Throwable exception) {
	    System.out.println("exception vis" +  exception.toString());
		ErrorMessageResponse  errormsg = new ErrorMessageResponse(exception.getMessage(),
				ErrorMessages.INTERNAL_SERVER_ERROR.name(),
                "https://www.google.com/");
return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
		entity(errormsg).
		build();//since its builder we need to build the response
		
	}

}
