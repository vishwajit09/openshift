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
public class LoginCredintials {
	private String userName;
	private String userPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

	
	

}
