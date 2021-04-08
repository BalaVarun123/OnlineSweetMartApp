package com.cg.osm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Login;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.service.ILoginService;
import com.cg.osm.service.LoginServiceImpl;

@RestController
@RequestMapping("/api/osm")
public class LoginController {

	
	@Autowired
	ILoginService service;
	
	@PutMapping("/login")
	String login(@RequestBody Login login) throws UserNotFoundException{
		String response;
		Long userId = login.getUserId();
		String password = login.getPassword();
		if (LoginServiceImpl.validateUserId(userId) && LoginServiceImpl.validateLoginPassword(password)) {
			if (service.login(userId, password)) {
				response = "Login successful.";
			}
			else {
				response = "Login failed.";
			}
		}
		else {
			response = "Invalid input.";
		}
		
		return response;
	}
	
	@PutMapping("/logout/{userId}")
	String logout(@PathVariable("userId") Long userId) throws UserNotFoundException{
		String response;
		if (LoginServiceImpl.validateUserId(userId)) {
			if (service.logout(userId)) {
				response = "Logout successful.";
			}
			else {
				response = "Logout failed.";
			}
		}
		else {
			response = "Invalid input.";
		}
		return response;
	}
	
	@GetMapping("/is-loggedin/{userId}")
	String isLoggedIn(@PathVariable("userId") Long userId) throws UserNotFoundException{
		String response;
		if (LoginServiceImpl.validateUserId(userId)) {
			if (service.isLoggedIn(userId)) {
				response = "User with id = "+userId+"is logged in.";
			}
			else {
				response = "User with id = "+userId+"is logged out.";
			}
		}
		else {
			response = "Invalid input.";
		}
		return response;
	}
	
}
