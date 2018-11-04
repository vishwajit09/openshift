/**
 * 
 */
package com.vis.app.ws.service.impl;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.vis.app.ws.error.AuthenticationException;
import com.vis.app.ws.error.ErrorMessages;
import com.vis.app.ws.io.dao.DAO;
import com.vis.app.ws.io.dao.MySQLDAO;
import com.vis.app.ws.service.AuthenticationService;
import com.vis.app.ws.service.UserService;
import com.vis.app.ws.shared.dto.UserDTO;
import com.vis.app.ws.utils.UserProfileUtils;

/**
 * @author Visu
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {
	
    DAO database;
	
	public UserDTO authenticate(String userName, String userPassword) throws AuthenticationException {
		UserService userService = new UserServiceImpl(); 
		UserDTO storedUser = userService.getUserByUserName(userName);
		
		if (storedUser ==null) {
			throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
		}
		
		String encryptedPassword = null;
		encryptedPassword = new UserProfileUtils().generateSecurePassword(userPassword, storedUser.getSalt());
		boolean authenticated = false;
		if(encryptedPassword!= null && encryptedPassword.equalsIgnoreCase(storedUser.getEncryptedPassword())) {
			if(userName!=null && userName.equalsIgnoreCase(storedUser.getEmail())) {
				authenticated = true;
			}
		}
		
		if(!authenticated) {
			throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
		}
		
		return storedUser;
	}

	@Override
	public String issueAccessToken(UserDTO userProfile) throws AuthenticationException {
		String  returnValue = null;
		String newSaltasPostFix = userProfile.getSalt();
		String accessMaterialToken = userProfile.getUserId()+newSaltasPostFix;
				
		byte[]  encryptedAccessToken = null;
		
		try {
			encryptedAccessToken = new UserProfileUtils().encrypt(userProfile.getEncryptedPassword(),accessMaterialToken);
			
			
		} catch (InvalidKeySpecException e) {
			Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE,null,e);
			throw new AuthenticationException("Failed to issue Secure access Token");
		} 
		String encryptedBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
		
		int tokenLength = encryptedBase64Encoded.length();
		String tokenToSaveInDatabase = encryptedBase64Encoded.substring(0, tokenLength/2);
		
		returnValue =encryptedBase64Encoded.substring(tokenLength/2,tokenLength);
		userProfile.setToken(tokenToSaveInDatabase);
		updateUserProfile(userProfile);
		
		storeAccessToken(userProfile);
		
		return returnValue;
	}

	private void updateUserProfile(UserDTO userProfile) {
	 
		database = new MySQLDAO();
		
		try {
			database.openConnection();
			System.out.println(userProfile.getEncryptedPassword());
			database.updateUserProfile(userProfile);
					
			
		}finally {
			this.database.closeConnection();
		}
		
	}

	@Override
	public void resetSecurityCredential(String password, UserDTO userprofile) {
       // Generate new salt
		UserProfileUtils userUtils = new UserProfileUtils();
		String salt =userUtils.getSalt(30);
		//generate new password
		String securePassword = userUtils.generateSecurePassword(password, salt);
		userprofile.setSalt(salt);
		System.out.println("securePassword"+ securePassword);
		userprofile.setEncryptedPassword(securePassword);
		
		//update user profile with sal and password
		
		updateUserProfile(userprofile);
		//
		
	}
	
	private void storeAccessToken(UserDTO userProfile) {
		
		this.database = new MySQLDAO();
		try {
			database.openConnection();
			database.updateUserProfile(userProfile);
			
		} finally {
			database.closeConnection();
		}
		
	}

}
