package com.cg.osm.service;

import com.cg.osm.error.UserNotFoundException;

public interface ILoginService {
	boolean login(Long userId,String password) throws UserNotFoundException;
	boolean logout(Long userId) throws UserNotFoundException;
	boolean isLoggedIn(Long userId) throws UserNotFoundException;
}
