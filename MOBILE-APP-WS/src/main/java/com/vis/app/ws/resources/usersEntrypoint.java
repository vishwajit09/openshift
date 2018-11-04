/**
 * 
 */
package com.vis.app.ws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;

import com.vis.app.annotation.Secured;
import com.vis.app.ws.model.CreateUserRequestModel;
import com.vis.app.ws.model.CreateUserResponseModel;
import com.vis.app.ws.service.UserService;
import com.vis.app.ws.service.impl.UserServiceImpl;
import com.vis.app.ws.shared.dto.UserDTO;

/**
 * @author Visu
 *
 */
//this @path anaotation tell that this is resource class that will serve the http request

@Path("/users")
public class usersEntrypoint {
	//* @POST telling when http request comes with request POST then this method will be triggered*/
	@POST
	@Consumes(MediaType.APPLICATION_JSON) //this tell what type data we can accepts like JSON - inpt
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) //this tell how our application will respond that output
	public CreateUserResponseModel creatUsers(CreateUserRequestModel requestObject) {

		//  we need to send json payload when using post like user name etc our method will be   able to accpet this i CreateUserRequestModel s where we will create model .this is how we make data avalible to our method when post is called . CreateUserResponse model will return the response back 
	
		CreateUserResponseModel returnvalue = new CreateUserResponseModel();
		
		//Prepare User DTO
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(requestObject, userDto);	// it will taken copy only those field which are there in request model
		
		//create new user 
		
		UserService useService = new UserServiceImpl();//now pass the DTO to service layer which will be interfaces
		UserDTO createUserProfile = useService.createUser(userDto);// read from data will have new DTO having user data from database
		
		BeanUtils.copyProperties(createUserProfile, returnvalue);//copy data to response model
		
		return returnvalue;
		
		
	}
	
	//for getting value from 
	@Secured       //New Anottation create by us
	@GET
	@Path("/{id}")//this is the parameter user enter like user id 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public CreateUserResponseModel getUserProfile(@PathParam("id") String id ) {
		
		System.out.println("inside the get  call");
		
		CreateUserResponseModel  returnvalue  = new CreateUserResponseModel();
		
		UserService useService = new UserServiceImpl();   
		UserDTO UserProfile = useService.getUser(id);
		
		BeanUtils.copyProperties(UserProfile, returnvalue);//copy data to response model
		
		
		return returnvalue;
	}

}
