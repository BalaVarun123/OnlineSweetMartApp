package com.cg.osm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Admin;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.User;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.repository.IAdminRepository;
import com.cg.osm.util.AdminUtils;

@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	public IAdminRepository repository;
	@Override
	public AdminDTO addAdmin(Admin admin) {
		AdminDTO adminDTO;
		if (admin == null)
			adminDTO =  null;
		else 
			adminDTO = AdminUtils.convertToAdminDto(repository.save(admin));
		return adminDTO;
	}

	@Override
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException {
		AdminDTO adminDTO;
		if (admin == null)
			adminDTO = null;
		Admin existingAdmin = repository.findById(admin.getId()).orElse(null);
		if (existingAdmin == null) {
			throw new AdminNotFoundException("Invalid id.");
		}
		else {
			adminDTO =  AdminUtils.convertToAdminDto(repository.save(admin));
		}
		return adminDTO;
	}

	@Override
	public AdminDTO cancelAdmin(int adminId) throws AdminNotFoundException {
		Admin existingAdmin = repository.findById(adminId).orElse(null);
		if (existingAdmin == null) {
			throw new AdminNotFoundException("Invalid id.");
		}
		else {
			repository.delete(existingAdmin);
			return AdminUtils.convertToAdminDto(existingAdmin);
		}
	}

	@Override
	public List<AdminDTO> showAllAdmins() {
		List<Admin> listAdmins = repository.findAll();
		return AdminUtils.convertToAdminDtoList(listAdmins);
	}

	@Override
	public List<AdminDTO> showAllAdmins(int adminId) {
		List<Admin> listAdmins = new ArrayList<Admin>();
		Optional<Admin> adminOptional = repository.findById(adminId);
		if (adminOptional.isPresent())
			listAdmins.add(adminOptional.get());
		return AdminUtils.convertToAdminDtoList(listAdmins);
	}
	
	
	public static boolean validateAdmin(Admin admin) {
		boolean flag;
		if (!(validateId(admin) && validatePassword(admin) &&  validateCustomer(admin) &&  validateUser(admin) &&  validateSweetItem(admin) &&  validateCategory(admin) &&  validateCart(admin) &&  validateProduct(admin))) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
	
	public static boolean validateId(Admin admin) {
		boolean flag;
		int id;
		if (admin == null ) {
			flag = false;
		}
		else {
			id = admin.getId();
			if (id < 0) {
				flag = false;
			}
			else {
				flag = true;
			}
		}
		return flag;
	}
	
	public static boolean validatePassword(Admin admin) {
		boolean flag;
		String password;
		if (admin == null ) {
			flag = false;
		}
		else {
			password = admin.getPassword();
			if (password != null && password.matches(".*[@#$%^&+=].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.length() >= 8) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		return flag;
	}
	
	
	public static boolean validateCustomer(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			Customer customer = admin.getCustomer();
			if (customer == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	
	public static boolean validateUser(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			User user = admin.getUser();
			if (user == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	public static boolean validateSweetItem(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			SweetItem sweetItem = admin.getItem();
			if (sweetItem == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	
	public static boolean validateCategory(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			Category category = admin.getCategory();
			if (category == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	
	public static boolean validateCart(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			Cart cart = admin.getCart();
			if (cart == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	
	public static boolean validateProduct(Admin admin) {
		boolean flag;
		if (admin == null ) {
			flag = false;
		}
		else {
			Product product = admin.getProduct();
			if (product == null)
				flag = false;
			else
				flag = true;
		}
		return flag;
	}
	
}
