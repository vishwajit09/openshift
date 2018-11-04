/**
 * 
 */
package com.vis.app.ws.service;

import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 * interface to call Service
 */
public interface UserService {
	
	public UserDTO createUser(UserDTO user);

	public UserDTO getUser(String id);
	
	public UserDTO getUserByUserName(String userName);
	

}
