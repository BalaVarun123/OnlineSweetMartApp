package com.cg.osm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.User;
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
}
