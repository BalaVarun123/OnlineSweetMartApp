package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;
import com.cg.osm.repository.IUserRepository;
import com.cg.osm.util.UserUtils;
@Service
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
		String password = user.getPassword();
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