package com.cg.osm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.repository.IUserRepository;
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	IUserRepository repository;

	@Override
	public boolean login(Long userId, String password) throws UserNotFoundException {
		boolean result;
		User user = repository.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Invalid user id.");
		}
		else {
			String correctPassword = user.getPassword();
			if (correctPassword == null) {
				result =  false;
			}
			else {
				if (correctPassword.equals(password)) {
					user.setLoggedIn(true);
					repository.save(user);
					result = true;
				}
				else {
					result = false;
				}
			}
			
			
		}
		return result;
	}

	@Override
	public boolean logout(Long userId) throws UserNotFoundException {
		boolean result;
		User user = repository.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Invalid user id.");
		}
		else {
			user.setLoggedIn(false);
			repository.save(user);
			result = true;
		}	
		return result;
	}

	@Override
	public boolean isLoggedIn(Long userId) throws UserNotFoundException {
		boolean result;
		User user = repository.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Invalid user id.");
		}
		else {
			result = user.isLoggedIn();
		}	
		return result;
	}
	
	
	public static boolean validateUserId(Long userId) {
		if (userId > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean validateLoginPassword(String password) {
		return password != null && password.matches(".*[@#$%^&+=].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.length() >= 8;
	}

}
