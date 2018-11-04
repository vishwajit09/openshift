/**
 * 
 */
package com.vis.app.ws.io.dao;

import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 *
 */
public interface DAO {
	 public void openConnection();// to open database connection
	 public UserDTO getUserByUserName(String userEmail);
	 public UserDTO saveUser(UserDTO user);
	 public UserDTO getUser(String user);
	 public void closeConnection();//close db connection
	 public void updateUserProfile(UserDTO userprofile);

}
