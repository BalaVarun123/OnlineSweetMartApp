package com.cg.osm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Login;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.service.ILoginService;

@RestController
@RequestMapping("/api/osm")
public class LoginController {

	
	@Autowired
	ILoginService service;
	
	@PostMapping("/login")
	String login(@RequestBody Login login) throws UserNotFoundException{
		String response;
		if (service.login(login.getUserId(), login.getPassword())) {
			response = "Login successful.";
		}
		else {
			response = "Login failed.";
		}
		return response;
	}
}
