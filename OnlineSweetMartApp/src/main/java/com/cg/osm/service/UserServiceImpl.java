package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;
import com.cg.osm.repository.IUserRepository;
import com.cg.osm.util.UserUtils;

public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository repository;
	
	@Override
	public UserDTO addUser(User user) {
		UserDTO userDTO;
		if (user == null) {
			userDTO = null;
		}
		else {
			userDTO = UserUtils.convertToUserDto(repository.save(user));
		}
		return userDTO;
	}

	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		UserDTO userDTO;
		if (user == null  ) {
			userDTO = null;
		}
		else if (!repository.existsById(user.getUserId())) {
			throw new UserNotFoundException("Invalid user id.");
		}
		else {
			userDTO = UserUtils.convertToUserDto(repository.save(user));
		}
		return userDTO;
	}

	@Override
	public UserDTO cancelUser(long userId) throws UserNotFoundException {
		 User user = repository.findById( userId).orElse(null);
		 UserDTO userDTO = null;
		 if (user == null)
			 throw new UserNotFoundException("Invalid user id.");
		 else {
			 repository.delete(user);
			 userDTO = UserUtils.convertToUserDto(user);
		 }
			 
		return userDTO;
	}

	@Override
	public List<UserDTO> showAllUsers() {
		//Add implementation
		return null;
	}
	
	
	public static boolean validateUser(User user) {
		boolean flag;
		if (user == null)
			flag = false;
		else {
			if (validateUserId(user) && validateUserName(user) && validatePassword(user) && validatePasswordConfirm(user) && validateType( user)) {
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
		UserServiceImpl service = new UserServiceImpl();
		Long userId = user.getUserId();
		if (userId >= 0 && service.repository.existsById(userId)) {
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
		if (username != null && username.matches("[a-zA-Z ]+"))
			flag = true;
		return flag;
	}
	
	public static boolean validatePassword(User user) {
		boolean flag = true;
		//Add password validation
		return flag;
	}
	
	public static boolean validatePasswordConfirm(User user) {
		String password = user.getPassword();
		String passwordConfirm = user.getPasswordConfirm();
		boolean flag;
		if (password.equals(passwordConfirm) && validatePassword(user)) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean validateType(User user) {
		boolean flag = true;
		//Add user type validation
		return flag;
	}
	
	
	
	
	

}