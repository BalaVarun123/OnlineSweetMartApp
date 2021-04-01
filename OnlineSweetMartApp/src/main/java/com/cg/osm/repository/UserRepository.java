package com.cg.osm.repository;

import java.util.List;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;

public interface UserRepository  {

	public User addUser(User user);
	public User updateUser(User user) throws UserNotFoundException;
	public User cancelUser(int userId) throws UserNotFoundException;
	public List<User> showAllUsers();
}
