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
public class AuthenticationDetails {
	private String id;
	private String token;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

		
	

}
