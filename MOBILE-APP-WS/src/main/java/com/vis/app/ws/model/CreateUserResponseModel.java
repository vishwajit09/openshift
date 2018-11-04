/**
 * 
 */
package com.vis.app.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Visu
 *
 */
//@XmlRootElement to convert data into json 
@XmlRootElement
public class CreateUserResponseModel {
	//output
	private String firstName;
	private String lastName;
	private String email;
	private String href;
	private String userId;
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
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
