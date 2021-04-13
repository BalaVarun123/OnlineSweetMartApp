package com.cg.osm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;
import com.cg.osm.repository.IUserRepository;
import com.cg.osm.util.UserUtils;


/*
 * Author      : BALASUBRAMANIAN S
 * Version     : 1.0
 * Date        : 05-04-2021
 * Description : This is User Service Layer
*/


@Service
public class UserServiceImpl implements IUserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImp.class);
	private static final String MESSAGE_INVALID_ID = "Invalid user id.";
	@Autowired
	IUserRepository repository;
	

	
	/*
	 * Description     : This method Adds new User
	 * Input Parameter : User Object 
	 * Return Value    : UserDTO Object 
	 */
	
	@Override
	public UserDTO addUser(User user) {
		LOGGER.info("addUser() service is initiated");
		UserDTO userDTO;
		if (user == null) {
			userDTO = null;
		}
		else {
			userDTO = UserUtils.convertToUserDto(repository.save(user));
		}
		LOGGER.info("addUser() service has executed");
		return userDTO;
	}
	
	/*
	 * Description     : This method Updates existing User
	 * Input Parameter : User Object 
	 * Return Value    : UserDTO Object 
	 * Exception       : UserNotFoundException
	 */

	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		LOGGER.info("updateUser() service is initiated");
		UserDTO userDTO;
		if (user == null  ) {
			userDTO = null;
		}
		else if (!repository.existsById(user.getUserId())) {
			throw new UserNotFoundException(MESSAGE_INVALID_ID);
		}
		else {
			userDTO = UserUtils.convertToUserDto(repository.save(user));
		}
		LOGGER.info("updateUser() service has executed");
		return userDTO;
	}

	/*
	 * Description     : This method Deletes existing User
	 * Input Parameter : long 
	 * Return Value    : UserDTO Object 
	 * Exception       : UserNotFoundException
	 */
	
	@Override
	public UserDTO cancelUser(long userId) throws UserNotFoundException {
		LOGGER.info("cancelUser() service is initiated");
		 User user = repository.findById( userId).orElse(null);
		 UserDTO userDTO = null;
		 if (user == null)
			 throw new UserNotFoundException(MESSAGE_INVALID_ID);
		 else {
			 repository.delete(user);
			 userDTO = UserUtils.convertToUserDto(user);
		 }
		   
		 LOGGER.info("cancelUser() service has executed");
			 
		return userDTO;
	}
	
	/*
	 * Description      : This method Shows existing User
	 * Input Parameter  : long
	 * Return Value     : UserDTO Object 
	 * Exception        : UserNotFoundException
	 */

	
	public UserDTO showUser(long  userId) throws UserNotFoundException{
		LOGGER.info("showUser() service is initiated");
		User user = repository.findById( userId).orElse(null);
		 UserDTO userDTO = null;
		 if (user == null)
			 throw new UserNotFoundException(MESSAGE_INVALID_ID);
		 else {
			 userDTO = UserUtils.convertToUserDto(user);
		 }
		 
		 LOGGER.info("showUser() service is initiated");
		 return userDTO;
	}
	

	/*
	 * Description     : This method Shows all existing User details.
	 * Return Value    : List<UserDTO> Object 
	 */
	

	@Override
	public List<UserDTO> showAllUsers() {
		LOGGER.info("showAllUsers() service is initiated");
		LOGGER.info("showAllUsers() service has executed");
		return UserUtils.convertToUserDtoList(repository.findAll());
	
	}
	
	
	
	public static boolean validateUser(User user) {
		boolean flag;
		if (user == null)
			flag = false;
		else {
			if (validateUserId(user) && validateUserName(user) && validatePassword(user) && validateType( user)) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		return flag;
	}
	
	public static boolean validateUserId(User user) {
		boolean flag;
		Long userId = user.getUserId();
		if (userId >= 0 ) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	public static boolean validateUserName(User user) {
		boolean flag = false;
		String username = user.getUsername();
		if (username != null && username.matches("^[a-zA-Z0-9 ]+$")&& user.getUsername().length()>2)
			flag = true;
		return flag;
	}
	
	public static boolean validatePassword(User user) {
		boolean flag = true;
		String password = user.getPassword();
		if (password != null && password.matches(".*[@#$%^&+=].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.length() >= 8) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	
	public static boolean validatePasswordConfirm(User user) {
		boolean flag = true;
		String password = user.getPasswordConfirm();
		if (password != null && password.matches(".*[@#$%^&+=].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.length() >= 8) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	
	public static boolean validateType(User user) {
		boolean flag = true;
		String type = user.getType();
		if (type == null) {
			flag = false;
		}
		else {
			if (type.equals("CUSTOMER") || type.equals("ADMIN")) {
				flag = true;
			}
			else {
				flag = false;
			}
		}

		return flag;
	}
	
	
	
	
	

}