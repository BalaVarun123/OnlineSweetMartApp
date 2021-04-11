package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;
import com.cg.osm.service.IUserService;
import com.cg.osm.service.UserServiceImpl;

/*
 * Author      : Raksha R
 * Version     : 1.0
 * Date        : 06-04-2021
 * Description : This is User Controller
*/


@RestController
@RequestMapping("/api/osm")
public class UserController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IUserService service;
	

	/************************************************************************************
	 * Method       : addUser
	 * Description  : It is used to add User into user Table
	 * @param user  : User Object
	 * @returns     : It returns UserDTO Object with details
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By   : Raksha R
     * Created Date : 06-04-2021 
     *
	 ************************************************************************************/
	
	@PostMapping(value = "/user/add" , consumes = "application/json")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		logger.info("add-User URL is opened");
		logger.info("addUser() is initiated");
		Object result;
		HttpStatus status;
		if (!UserServiceImpl.validateUserId(user)) {
			result = "Invalid userId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validateUserName(user)) {
			result = "Invalid userName.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validatePassword(user)) {
			result = "Invalid password.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validatePasswordConfirm(user))
		{
			result= "Password confirm failed";
			status= HttpStatus.BAD_REQUEST;
		}
		else if (!user.getPassword().equals(user.getPasswordConfirm()))
		{
			result= "Passwords don't match";
			status= HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validateType(user)) {
			result = "Invalid type.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addUser(user);
			status = HttpStatus.OK;
		}
		logger.info("addUser() has executed");
		return new ResponseEntity<Object>(result,status);
	} 
	
	
	
	/************************************************************************************
	 * Method         : updateUser 
	 * Description    : It is used to update User into User table
	 * @param user    : User Object
	 * @returns user  : It returns UserDTO Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : UserNotFoundException
	 *  Created By    : Raksha R
     * Created Date   : 06-04-2021 
	
	 ************************************************************************************/
		
	
	
	@PutMapping(value= "/user/update" , consumes = "application/json")
	public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException
	{
		logger.info("update-User URL is opened");
		logger.info("updateUser() is initiated");
		Object result;
		HttpStatus status;
		if (!UserServiceImpl.validateUserId(user)) {
			result = "Invalid userId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validateUserName(user)) {
			result = "Invalid userName.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validatePassword(user)) {
			result = "Invalid password.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validatePasswordConfirm(user))
		{
			result= "Password confirm failed";
			status= HttpStatus.BAD_REQUEST;
		}
		else if (!user.getPassword().equals(user.getPasswordConfirm()))
		{
			result= "Passwords don't match";
			status= HttpStatus.BAD_REQUEST;
		}
		else if (!UserServiceImpl.validateType(user)) {
			result = "Invalid type.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateUser(user);
			status = HttpStatus.OK;
		}
		logger.info("updateUser() has executed");
		return new ResponseEntity<Object>(result,status);
		
	}
	
	/************************************************************************************
	 * Method          : cancelUser
	 * Description     : It is used to remove User from user table
	 * @param id       : long userId
	 * @returns user   : It returns UserDTO Object with details
	 * @DeleteMapping  : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception      : UserNotFoundException
	 * Created By      : Raksha R
     * Created Date    : 06-04-2021 
	 ************************************************************************************/
	
	
	@DeleteMapping(value= "/user/cancel/{userId}")
	public ResponseEntity<Object> cancelUser(@PathVariable ("userId") long userId) throws UserNotFoundException
	{
		logger.info("cancel-Usert URL is opened");
		logger.info("cancelUser() is initiated");
		Object result;
		HttpStatus status;
		if (userId < 0) {
			result = "Invalid userId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.cancelUser(userId);
			status = HttpStatus.OK;
		}
		logger.info("cancelUser() has executed");
		return new ResponseEntity<Object> (result,status);
	}
	
	
	/************************************************************************************
	 * Method         : showUserById
	 * Description    : It is used to view User from user table
	 * @param         : long userId
	 * @returns user  : It returns UserDTO Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : UserNotFoundException
	 * Created By     : Raksha R
     * Created Date   : 06-04-2021 
	 * 
	 ************************************************************************************/
	
	
	@GetMapping(value="/user/show/{id}" , produces="application/json")
	public UserDTO showUser(@PathVariable("id") long  userId) throws UserNotFoundException{
		logger.info("show-UserURL is opened");
		logger.info("showUser() is initiated");
		logger.info("showUser() has executed");
		return service.showUser(userId);
		
	}
	
	
	/************************************************************************************
	 * Method         : showAllUsers
	 * Description    : It is used to view all User details present in user table
	 * @returns user  : It returns all List<UserDTO> Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By     : Raksha R
     * Created Date   :06-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping(value="/user/show-all" , produces="application/json")
	public List<UserDTO> showAllUsers()
	{
		logger.info("show-All-Users URL is opened");
		logger.info("showAllUsers() is initiated");
		return service.showAllUsers();
	}
	
	
	
}
