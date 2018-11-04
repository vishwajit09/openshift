/**
 * 
 */
package com.vis.app.ws.shared.dto;

import java.io.Serializable;

/**
 * @author Visu
 * this will pass data among the layers DTO - data transfer object
 */
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String salt;
	private String encryptedPassword;
	private String UserId;
	private long id;//database id
	private String token;
	//Getter Setter
	public String getFirstName() {
		return firstName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long iD) {
		id = iD;
	}
	
	

}
