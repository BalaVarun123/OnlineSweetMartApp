package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Admin;
import com.cg.osm.model.AdminDTO;
@Component
public class AdminUtils {
	public static List<AdminDTO> convertToAdminDtoList(List<Admin> list){
		List<AdminDTO> dtolist = new ArrayList<AdminDTO>();
		for(Admin admin : list) 
			dtolist.add(convertToAdminDto(admin));
		return dtolist;
	}
	
	public static AdminDTO convertToAdminDto(Admin admin) {
		AdminDTO adminDto = new AdminDTO();
		adminDto.setId(admin.getId());
		adminDto.setPassword(admin.getPassword());
		adminDto.setCustomer(admin.getCustomer());
		adminDto.setUser(admin.getUser());
		adminDto.setItem(admin.getItem());
		adminDto.setCategory(admin.getCategory());
		adminDto.setCart(admin.getCart());
		adminDto.setProduct(admin.getProduct());
		return adminDto;
	}
	
	
	
	public static List<Admin> convertToAdminList(List<AdminDTO> dtoList){
		List<Admin> list = new ArrayList<Admin>();
		for(AdminDTO adminDTO : dtoList) 
			list.add(convertToAdmin(adminDTO));
		return list;
	}
	
	
	public static Admin convertToAdmin(AdminDTO adminDto) {
		Admin admin = new Admin();
		admin.setId(adminDto.getId());
		admin.setPassword(adminDto.getPassword());
		admin.setCustomer(adminDto.getCustomer());
		admin.setUser(adminDto.getUser());
		admin.setItem(adminDto.getItem());
		admin.setCategory(adminDto.getCategory());
		admin.setCart(adminDto.getCart());
		admin.setProduct(adminDto.getProduct());
		return admin;
	}
}
