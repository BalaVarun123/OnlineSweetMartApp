package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;

public interface IUserService {

	public UserDTO addUser(User user);

	public UserDTO updateUser(User user) throws UserNotFoundException;

	public UserDTO cancelUser(long userId) throws UserNotFoundException;//Changed the data type of argument to long because the data type of User's userId attribute is Long. 

	public List<UserDTO> showAllUsers();
	
	public UserDTO showUser(long  userId) throws UserNotFoundException;
}
