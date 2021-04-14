package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import com.cg.osm.model.UserDTO;
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
	void testLogin1()  
	{
		LOGGER.info("Initiating test case 1 for login");
		user = new User(-1L,"userName","Password123$","Password123$","type",true);
		
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
	void testLogin2()  
	{
		LOGGER.info("Initiating test case 2 for login");
		user = new User (100L,"userNameOne","Password12$","password12$","typeOne",true);
		UserDTO userDTO = userService.addUser(user);
		try
		{
			
			loginService.login(userDTO.getUserId(),"Password12$");
			assertEquals(true,loginService.isLoggedIn(userDTO.getUserId()));
			
		}
		catch (UserNotFoundException exception)
		{
		  LOGGER.error("User not found");
		}
	}
	
	// TEST CASES FOR LOGOUT
	@Test
	void testLogout1() 
	{
		LOGGER.info("Initiating test case 1 for logout");
		user = new User(-1L,"userName","Password123$","Password123$","type",true);
		
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
	void testLogout2() 
	{
		LOGGER.info("Initiating test case 2 for logout");
		user = new User (100L,"userNameOne","Password12$","password12$","typeOne",true);
		UserDTO userDTO = userService.addUser(user);
		try
		{
		loginService.logout(userDTO.getUserId());
		assertEquals(false,loginService.isLoggedIn(userDTO.getUserId()));
		}
		catch (UserNotFoundException exception) 
		{
		}
			
	}
	
	

	  // TEST CASES FOR ISLOGGEDIN
	  @Test
	  void testIsLoggedIn1() 
	  {
		  LOGGER.info("Test case for IsLoggedIn 1");
	    user = new User(-70L,"userNameFour","Password1@","Password1@","typeThree",true);
	    UserDTO userDTO = userService.addUser(user);
	   
	    try
	    {
	    loginService.login(userDTO.getUserId(), "Password1@");	
	    loginService.isLoggedIn(-70L);
	    }
	    catch (UserNotFoundException exception)
	    {
	    	assertEquals("Invalid user id.", exception.getMessage());
	    }
		
	    
	  }
	
	  @Test
	  void testIsLoggedIn2()
	  {
		  LOGGER.info("Test case for IsLoggedIn 2");
	    user = new User(70L,"userNameFour","Password1@","Password1@","typeThree",true);
	    UserDTO userDTO = userService.addUser(user);
	    try
	    {
	    	 loginService.login(userDTO.getUserId(),"Password1@");
                assertEquals(true,loginService.isLoggedIn(userDTO.getUserId()));
	    }
	    catch (UserNotFoundException exception)
		{
			
		}
	    
	  }


      @Test
      void testIsLoggedIn3() 
	  {
		  LOGGER.info("Test case for IsLoggedIn 3");
	    user = new User(70L,"userNameFour","Password1@","Password1@","typeThree",true);
	    UserDTO userDTO = userService.addUser(user);
	    try
	    {
	    	 loginService.login(userDTO.getUserId(),"Password1@");
             loginService.logout(userDTO.getUserId());
                assertEquals(false,loginService.isLoggedIn(userDTO.getUserId()));
	    }
	    catch (UserNotFoundException exception)
		{
			
		}
	    
	  }
	 //TEST CASE FOR VALIDATE USER ID	
	  @Test 
	  void testValidateUserId1()  
	 {
		  LOGGER.info("Testing testValidateUserId()");
		  
		  assertTrue(LoginServiceImpl.validateUserId(30L));
		 
		  
	 }
	      
	  @Test 
	  void testValidateUserId2() 
	 {
		  LOGGER.info("Testing testValidateUserId()");
		  
		  assertFalse(LoginServiceImpl.validateUserId(-30L));
		 
		  
	 }
		  // TEST CASE FOR VALIDATE USER PASSWORD
		  @Test
		  void testValidateLoginPassword1() throws UserNotFoundException 
		  {
			LOGGER.info("Testing testValidateLoginPassword");
		    
		    assertFalse(LoginServiceImpl.validateLoginPassword("password7$"));
		  }
		  
		  @Test
		  void testValidateLoginPassword2() throws UserNotFoundException 
		  {
			LOGGER.info("Testing testValidateLoginPassword");
		    
		    assertFalse(LoginServiceImpl.validateLoginPassword("p7$"));
		  }
		 
}
