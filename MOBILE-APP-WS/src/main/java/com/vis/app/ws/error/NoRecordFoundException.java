/**
 * 
 */
package com.vis.app.ws.error;

/**
 * @author Visu
 *
 */
public class NoRecordFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066245402104400933L;
	
	//constructor
	
	public  NoRecordFoundException(String Message) {
		super(Message);
	}

}
