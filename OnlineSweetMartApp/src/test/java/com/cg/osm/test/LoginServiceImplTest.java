package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.service.ILoginService;
import com.cg.osm.service.IUserService;
import com.cg.osm.service.LoginServiceImpl;
@SpringBootTest

class LoginServiceImplTest {
	
	@Autowired
    ILoginService loginService;
	@Autowired
	IUserService userService;
	
	User user;
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	@BeforeEach
	void beforeEach()
	{
		user =  new User();
		user.setUserId(userService.addUser(user).getUserId());
	    
	}
	
	@AfterEach
	void afterEach()
	{
		try {
			userService.cancelUser(user.getUserId());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// TEST CASES FOR LOGIN
	@Test
	void testLogin1()  throws UserNotFoundException
	{
		LOGGER.info("Initiating test case 1 for login");
		user = new User(-1L,"userName","Password123$","Password123$","type",true);
		user.setUserId(userService.addUser(user).getUserId());
		try
		{
			loginService.login(-1L, "Password123$");
		}
		catch (UserNotFoundException exception)
		{
			assertEquals("Invalid user id.", exception.getMessage());
		}
	}
	
	@Test
	void testLogin2()  throws UserNotFoundException
	{
		LOGGER.info("Initiating test case 2 for login");
		user = new User (100L,"userNameOne","Password12$","password12$","typeOne",true);
		user.setUserId(userService.addUser(user).getUserId());
		try
		{
			
			loginService.login(100L,"Password12$");
			assertEquals(true,loginService.isLoggedIn(100L));
			
		}
		catch (UserNotFoundException exception)
		{
		
		}
	}
	
	// TEST CASES FOR LOGOUT
	@Test
	void testLogout1() throws UserNotFoundException 
	{
		LOGGER.info("Initiating test case 1 for logout");
		user = new User(-1L,"userName","Password123$","Password123$","type",true);
		user.setUserId(userService.addUser(user).getUserId());
		try
		{
			loginService.logout(-1L);
		}
		catch (UserNotFoundException exception)
		{
			assertEquals("Invalid user id.", exception.getMessage());
		}
	}
	
	@Test
	void testLogout2() throws UserNotFoundException
	{
		LOGGER.info("Initiating test case 2 for logout");
		user = new User (100L,"userNameOne","Password12$","password12$","typeOne",true);
		user.setUserId(userService.addUser(user).getUserId());
		try
		{
			
			loginService.logout(100L);
			assertEquals(false,loginService.isLoggedIn(100L));
			
		}
		catch (UserNotFoundException exception)
		{
		
		}
	}
	
	

	  // TEST CASES FOR ISLOGGEDIN
	  @Test
	  void testIsLoggedIn1() throws UserNotFoundException
	  {
		  LOGGER.info("Test case for IsLoggedIn 1");
	    user = new User(-70L,"userNameFour","Password1@","Password1@","typeThree",true);
	    user.setUserId(userService.addUser(user).getUserId());
	    try
	    {
	    	loginService.isLoggedIn(-70L);
	    }
	    catch (UserNotFoundException exception)
		{
			assertEquals("Invalid user id.", exception.getMessage());
		}
	    
	  }
	  
	  @Test
	  void testIsLoggedIn2() throws UserNotFoundException
	  {
		  LOGGER.info("Test case for IsLoggedIn 2");
	  }
	  @Test
	  void testIsLoggedIn3() throws UserNotFoundException
	  {
		  LOGGER.info("Test case for IsLoggedIn 3");
	  }
	  
	 //TEST CASE FOR VALIDATE USER ID	
	  @Test 
	  void testValidateUserId() throws UserNotFoundException 
	 {
		  LOGGER.info("Testing testValidateUserId()");
		  user = new User(30L,"userNameFive","Password2@","Password2@","typeFour",true);
		  userService.addUser(user);
		  assertNotNull(loginService.login(30L, "Password2@"));
		  assertTrue(LoginServiceImpl.validateUserId(30L));
		 // user.setUserId(-5L);
		  //assertFalse(LoginServiceImpl.validateUserId(-5L));
		  
		  
	 }
	      
		  // TEST CASE FOR VALIDATE USER PASSWORD
		  @Test
		  void testValidateLoginPassword() 
		  {
			LOGGER.info("Testing testValidateLoginPassword");
		    user = new User(60L,"userNameSix","password7$","password7$","typeFive",true);
		    userService.addUser(user);
		    try
		    {
		    	loginService.login(60L, "password7$");
		    }
		    catch (UserNotFoundException exception)
		    {
		    	assertEquals("Invalid user id.",exception.getMessage());
		    }
		  }
		 
}
