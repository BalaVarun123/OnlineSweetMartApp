package com.cg.osm.service;

import com.cg.osm.error.UserNotFoundException;

public interface ILoginService {
	boolean login(long userId,String password) throws UserNotFoundException;
	boolean logout(long userId) throws UserNotFoundException;
	boolean isLoggedIn(long userId) throws UserNotFoundException;
}
