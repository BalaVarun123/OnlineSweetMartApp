package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.User;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.UserDTO;
import com.cg.osm.repository.IUserRepository;
import com.cg.osm.service.UserServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@Mock
	IUserRepository repo;

	@InjectMocks
	UserServiceImpl service;

	@Test
	public void addUserTest() throws UserNotFoundException {

		boolean bool = false;

		User user = new User(123L, "rahul", "Rahul@123", "Rahul@123", "CUSTOMER", true);
		when(repo.save(user)).thenReturn(user);

		UserDTO user_values = service.addUser(user);
		try {
			bool = service.validateUser(user);
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid user details");
		}
		assertEquals(true, bool);
		assertEquals("rahul", user_values.getUsername());
		assertEquals("Rahul@123", user_values.getPassword());
		assertEquals("Rahul@123", user_values.getPasswordConfirm());
		assertEquals("CUSTOMER", user_values.getType());
	
		

	}

	@Test
	public void getAllUserTest() throws UserNotFoundException {

		boolean bool1 = false;
		boolean bool2 = false;
		List<User> user_values = new ArrayList<User>();
		User user1 = new User(123L, "rahul", "Rahul@123", "Rahul@123", "CUSTOMER", true);
		User user2 = new User(223L, "rohit", "Rohit@123", "Rohit@123", "CUSTOMER", true);
		user_values.add(user1);
		user_values.add(user2);
		when(repo.findAll()).thenReturn(user_values);

		List<UserDTO> userdto = new ArrayList<UserDTO>();
		userdto = service.showAllUsers();
		try {
			bool1 = service.validateUser(user1);
			bool2 = service.validateUser(user1);
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid user details");
		}
		assertEquals(true, bool1);
		assertEquals(true, bool2);
		assertEquals(2, userdto.size());
		verify(repo, times(1)).findAll();
	}

	@Test
	public void getByIdTest() throws UserNotFoundException {
		boolean bool = false;
		User user = new User(123L, "rahul", "Rahul@123", "Rahul@123", "CUSTOMER", true);
		when(repo.findById(123L)).thenReturn(Optional.of(user));
		UserDTO user_values = service.showUser(123L);
		try {
			bool = service.validateUser(user);
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid user details");
		}
		assertEquals(true, bool);
		assertEquals(true, bool);
		assertEquals("rahul", user_values.getUsername());
		assertEquals("Rahul@123", user_values.getPassword());
		assertEquals("Rahul@123", user_values.getPasswordConfirm());
		assertEquals("CUSTOMER", user_values.getType());
		
	}

	@Test
	public void removeUser() throws UserNotFoundException {
		verify(repo, never()).deleteById(123L);

	}

	@Test
	public void updateProductTest() throws UserNotFoundException {
		boolean bool = false;

		User user = new User(123L, "rahul", "Rahul@123", "Rahul@123", "CUSTOMER", true);
		User updated_user = new User(123L, "rahul", "Rohit@123", "Rohit@123", "CUSTOMER", true);
		when(repo.findById(123L)).thenReturn(Optional.of(updated_user));
		UserDTO user_values = service.showUser(123L);
		try {
			bool = service.validateUser(user);
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid user details");
		}
		assertEquals(true, bool);
		assertEquals(true, bool);
		assertEquals("rahul", user_values.getUsername());
		assertEquals("Rohit@123", user_values.getPassword());
		assertEquals("Rohit@123", user_values.getPasswordConfirm());
		assertEquals("CUSTOMER", user_values.getType());
		
	}

}
