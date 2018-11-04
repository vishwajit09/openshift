/**
 * 
 */
package com.vis.app.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Visu
 *
 */
@XmlRootElement
public class ErrorMessageResponse {
	private String errorMessage;
	private String errorMessageKey;
	private String href;
	
	
	
	public ErrorMessageResponse() {
			}
	
	public ErrorMessageResponse(String errorMessage, String errorMessageKey, String href) {
		this.errorMessage = errorMessage;
		this.errorMessageKey = errorMessageKey;
		this.href = href;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessageKey() {
		return errorMessageKey;
	}
	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	

}
