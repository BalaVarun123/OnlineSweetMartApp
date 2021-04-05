package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;



import com.cg.osm.entity.User;
import com.cg.osm.model.UserDTO;

public class UserUtils {
	public static List<UserDTO> convertToUserDtoList(List<User> list){
		List<UserDTO> dtolist = new ArrayList<UserDTO>();
		for(User user : list) 
			dtolist.add(convertToUserDto(user));
		return dtolist;
	}
	
	public static UserDTO convertToUserDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		userDto.setType(user.getType());
		return userDto;
	}
	
	
	
	public static List<User> convertToUserList(List<UserDTO> dtoList){
		List<User> list = new ArrayList<User>();
		for(UserDTO userDTO : dtoList) 
			list.add(convertToUser(userDTO));
		return list;
	}
	
	
	public static User convertToUser(UserDTO userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setType(userDto.getType());
		return user;
	}
}