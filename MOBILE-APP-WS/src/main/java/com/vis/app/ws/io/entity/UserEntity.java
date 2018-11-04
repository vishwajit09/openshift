/**
 * 
 */
package com.vis.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Visu
 * this entity will be stored(presistance in database)
 */

@Entity(name="users")  //database name

public class UserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5402606618993678735L;
	@Id                  // hibernate anatoation this is database id auto generate , and it will incremenet based 
	@GeneratedValue      //on the dabasecall
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String salt;
	private String encryptedPassword;
	private String UserId;
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	

}
