/**
 * 
 */
package com.vis.app.ws.error;

/**
 * @author Visu
 *
 */
//Error message class that need to be converted into JSON and Return back as response
public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing Field Please check all required field are provided"),
	RECORD_ALREADY_EXIST("record Already Exist"),
	INTERNAL_SERVER_ERROR("Internal Server Error"),
	NO_RECORD_FOUND("Record Not found"),
	AUTHENTICATION_FAILED("Authenctication Failed");
		

	private String ErrorMessage;

	private ErrorMessages(String errorMessage) {
		this.ErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	
	
}
