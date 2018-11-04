/**
 * 
 */
package com.vis.app.ws.error;

/**
 * @author Visu
 *
 */

//every new custome exception should have own class
public class MissingRequiredFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469150057999583727L;
	
	public MissingRequiredFieldException(String message) {
		super(message);
	}
	
	

}
