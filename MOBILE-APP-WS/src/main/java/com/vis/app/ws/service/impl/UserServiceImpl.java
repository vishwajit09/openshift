/**
 * 
 */
package com.vis.app.ws.service.impl;

import com.vis.app.ws.error.ErrorMessages;
import com.vis.app.ws.error.NoRecordFoundException;
import com.vis.app.ws.io.dao.DAO;
import com.vis.app.ws.io.dao.MySQLDAO;
import com.vis.app.ws.service.UserService;
import com.vis.app.ws.shared.dto.UserDTO;
import com.vis.app.ws.utils.UserProfileUtils;
import com.vis.app.ws.error.CouldNotFindUser;

/**
 * @author Visu
 *
 */
public class UserServiceImpl implements UserService {
	
	DAO database;
	
	
	public UserServiceImpl() {
		this.database = new MySQLDAO();//instance of DAO
	}


	UserProfileUtils userProfileUtils = new UserProfileUtils();
	
	
	// for creating new users

	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub 
	   UserDTO returnValue = new UserDTO();
	   
	   //validate the required field create a new util class
	   System.out.println("before validation");
	   
	   userProfileUtils.validateRequiredField(user);
	   
	   //check if user exist  //create an entity object 	   
	   UserDTO existingUser = getUserByUseremail(user.getEmail());
	   System.out.println("after check validation");
	   
	   if(existingUser !=null) {
		   throw new CouldNotFindUser(ErrorMessages.RECORD_ALREADY_EXIST.name());
	   }
	    
	  
	   //generate secure public user id create aplha numeric id 
	   String userID = userProfileUtils.generateUser(30);
	   //now set the user if in userdto
	   
	   user.setUserId(userID);
	   
	   //generate salt 
	   
	   String salt = userProfileUtils.getSalt(30);
	   
	   //generate secure passowrd
	   String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
	   user.setSalt(salt);
	   user.setEncryptedPassword(encryptedPassword);
	   
	   //record data into database
	   
	   returnValue  = saveUser(user);
	   
	   //retun the user profile
	   
		return returnValue;
	}
	
	// for GET request 
	
	@Override
	public UserDTO getUser(String id) {
		System.out.println("inside this");
		UserDTO returnValue = null;
		
		try {
			
			this.database.openConnection();
			System.out.println("opne connection");
			returnValue = this.database.getUser(id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		} finally {
			this.database.closeConnection();
		}
		
		return returnValue;
	}

	
	
	private UserDTO getUserByUseremail(String userEmail) {
		UserDTO userDTO = null;
		
		if(userEmail == null || userEmail.isEmpty()) {
			return null;
		}
		
		try {
			this.database.openConnection();
			System.out.println("opne connection");
			userDTO = this.database.getUserByUserName(userEmail);
			System.out.println("after connection");
			
		}finally {
			this.database.closeConnection();
		}
		
		return userDTO;
		
		
		
	}
	
	private UserDTO saveUser(UserDTO user) {
		
		UserDTO returnValue = null;
		
		try {
			this.database.openConnection();
			returnValue = this.database.saveUser(user);
		
		}finally {
			this.database.closeConnection();
		}
		
		return returnValue;
		
	}
	
	@Override
	public  UserDTO getUserByUserName(String userName) {
		UserDTO userDTO = null;
		
		if(userName == null || userName.isEmpty()) {
			return null;
		}
		
		try {
			this.database.openConnection();
			System.out.println("opne connection");
			userDTO = this.database.getUserByUserName(userName);
			System.out.println("after connection");
			
		}finally {
			this.database.closeConnection();
		}
		
		return userDTO;
		
		
		
	}

	
}
