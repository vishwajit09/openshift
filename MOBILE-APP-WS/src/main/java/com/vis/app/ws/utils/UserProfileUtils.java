/**
 * 
 */
package com.vis.app.ws.utils;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import java.util.Random;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.vis.app.ws.error.ErrorMessages;
import com.vis.app.ws.error.MissingRequiredFieldException;
import com.vis.app.ws.shared.dto.UserDTO;	

/**
 * @author Visu
 * to validate all data
 */
public class UserProfileUtils {

	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	
	//for create secure 
	
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	//first wau to generate unique id
	
	
	public String generateUUID() {
		String returnUUID = UUID.randomUUID().toString().replaceAll("-", "");
		return returnUUID;
	}
	
	
	//another way 
	
	private String generateRandomString(int length) {
		StringBuilder returnvalue = new StringBuilder(length);
		for(int i = 0 ; i<length;i++) {
			returnvalue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnvalue);
	}
	
	public String generateUser(int length)
	{
		return generateRandomString(length);
	}
	public void validateRequiredField(UserDTO user) throws MissingRequiredFieldException 
	{
		if (user.getFirstName() == null ||
			user.getFirstName().isEmpty() ||
			user.getLastName() == null ||
			user.getLastName().isEmpty() ||
			user.getEmail() == null ||
			user.getEmail().isEmpty() ||
			user.getPassword() == null ||
			user.getPassword().isEmpty() ) {
			throw new MissingRequiredFieldException (
					ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
			
		}
		
		
	}
	
	//generate salt
	
	public String getSalt(int length) {
		return generateRandomString(length);
		
	}
	
	//genrate secure passowrd
	
	public String generateSecurePassword(String password , String salt) {
		String returnvalue = null;
		System.out.println("password"+ password);
		byte[] securePassword = hash(password.toCharArray(),salt.getBytes());
		System.out.println("securePassword :" + securePassword);
		returnvalue = Base64.encodeBase64String(securePassword);
		System.out.println("password :"+ password + " returnvalue :" + returnvalue + " salt: " + salt);
		
		return returnvalue;
	}

	public byte[] hash(char[] password,byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password,salt,ITERATIONS,KEY_LENGTH);
		Arrays.fill(password,Character.MIN_VALUE);
		try {
			 SecretKeyFactory skf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			 return skf.generateSecret(spec).getEncoded();
			   
		}catch(NoSuchAlgorithmException e) {
			throw new AssertionError("Error while hasing the password"+ e.getMessage(),e);
		}catch (InvalidKeySpecException e) {
			throw new AssertionError("Error while hasing the password"+ e.getMessage(),e);
		}
		finally {
			spec.clearPassword();
			
		}
		
		
	}
	
	
	public byte[] encrypt(String securePassword,String accessTokenMaterial) throws InvalidKeySpecException {
		
		return hash(securePassword.toCharArray(),accessTokenMaterial.getBytes());
		
	}

}
