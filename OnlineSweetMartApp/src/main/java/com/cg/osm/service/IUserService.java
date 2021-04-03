package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.User;
import com.cg.osm.model.UserDTO;
import com.cg.osm.error.UserNotFoundException;

public interface IUserService {

	public UserDTO addUser(User user);

	public UserDTO updateUser(User user) throws UserNotFoundException;

	public UserDTO cancelUser(long userId) throws UserNotFoundException;//Changed datatype of userId to long because datatype of User's userId attribute is Long

	public List<UserDTO> showAllUsers();
}
