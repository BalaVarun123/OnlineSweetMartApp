package com.cg.osm.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/osm")
public class UserController {
	
	@Autowired
	IUserService service;
	
	@PostMapping(value = "/user/add" , consumes = "application/json")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
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
		return new ResponseEntity<Object>(result,status);
	} 
	@PutMapping(value= "/user/update" , consumes = "application/json")
	public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException
	{
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
		return new ResponseEntity<Object>(result,status);
		
	}
	@DeleteMapping(value= "/user/cancel/{userId}")
	public ResponseEntity<Object> cancelUser(@PathVariable ("userId") long userId) throws UserNotFoundException
	{
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
		return new ResponseEntity<Object> (result,status);
	}
	@GetMapping(value="/user/show-all" , produces="application/json")
	public List<UserDTO> showAllUsers()
	{
		return service.showAllUsers();
	}
	
	@GetMapping(value="/user/show/{id}" , produces="application/json")
	public UserDTO showUser(@PathVariable("id") long  userId) throws UserNotFoundException{
		return service.showUser(userId);
	}
	
	
}
