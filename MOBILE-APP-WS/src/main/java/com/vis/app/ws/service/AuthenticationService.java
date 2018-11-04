/**
 * 
 */
package com.vis.app.ws.service;

import com.vis.app.ws.error.AuthenticationException;
import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 *
 */
public interface AuthenticationService {
	UserDTO authenticate(String userName,String userPassword)  throws AuthenticationException;
	String issueAccessToken(UserDTO userProfile) throws AuthenticationException;
	public void resetSecurityCredential(String password,UserDTO userDTO);

}
