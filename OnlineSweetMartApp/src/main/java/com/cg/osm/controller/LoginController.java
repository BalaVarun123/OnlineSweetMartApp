package com.cg.osm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


/*
 * Author      : UJJWAL SINGH A
 * Version     : 1.0
 * Date        : 06-04-2021
 * Description : This is Cart Controller
*/


@RestController
@RequestMapping("/api/osm")
public class LoginController {

	static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService service;
	

	/************************************************************************************
	 * Method       : login 
	 * Description  : This method is used to authenticate and login the user.
	 * @param       : Login Object
	 * @PutMapping  : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception   : UserNotFoundException
	 * Created By   : UJJWAL SINGH A
     * Created Date : 06-04-2021 
     *
	 ************************************************************************************/
	
	@PutMapping("/login")
	String login(@RequestBody Login login) throws UserNotFoundException{
		LOGGER.info("login URL is opened");
		LOGGER.info("login() is initiated");
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
		LOGGER.info("login() has executed");
		return response;
	}

	/************************************************************************************
	 * Method       : logout 
	 * Description  : This method logs out the user.
	 * @param       : Long userId
	 * @PutMapping  : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @exception   : UserNotFoundException
	 * Created By   : UJJWAL SINGH A
     * Created Date : 06-04-2021 
     *
	 ************************************************************************************/
	
	@PutMapping("/logout/{userId}")
	String logout(@PathVariable("userId") Long userId) throws UserNotFoundException{
		LOGGER.info("logout URL is opened");
		LOGGER.info("logout() is initiated");
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
		LOGGER.info("logout() has executed");
		return response;
	}

	/************************************************************************************
	 * Method       : isLoggedIn 
	 * Description  : This method returns the login status of the user.
	 * @param       : Long userId
	 * @GetMapping  : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @exception   : UserNotFoundException
	 * Created By   : UJJWAL SINGH A
     * Created Date : 06-04-2021 
     *
	 ************************************************************************************/
	
	
	@GetMapping("/is-loggedin/{userId}")
	String isLoggedIn(@PathVariable("userId") Long userId) throws UserNotFoundException{
		LOGGER.info("isLoggedIn URL is opened");
		LOGGER.info("isLoggedIn() is initiated");
		String response;
		if (LoginServiceImpl.validateUserId(userId)) {
			if (service.isLoggedIn(userId)) {
				response = "User with id = "+userId+" is logged in.";
			}
			else {
				response = "User with id = "+userId+" is logged out.";
			}
		}
		else {
			response = "Invalid input.";
		}
		LOGGER.info("isLoggedIn() has executed");
		return response;
	}
	
}
