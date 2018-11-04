/**
 * 
 */
package com.vis.app.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Visu
 *
 */

// @XmlRootElement to convert data into json 
@XmlRootElement
public class CreateUserRequestModel {
	//input JSON field 
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
