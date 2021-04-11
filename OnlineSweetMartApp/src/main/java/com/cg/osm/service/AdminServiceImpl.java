package com.cg.osm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.controller.AdminController;
import com.cg.osm.entity.Admin;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.User;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.repository.IAdminRepository;
import com.cg.osm.util.AdminUtils;

/*
 * Author      : BALASUBRAMANIAN S
 * Version     : 1.0
 * Date        : 05-04-2021
 * Description : This is Cart Service Layer
*/




@Service
public class AdminServiceImpl implements IAdminService{

	
	@Autowired
	public IAdminRepository repository;
	
	static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	
	@Override
	public AdminDTO addAdmin(Admin admin) {
		LOGGER.info("addAdmin service method is initiated.");
		AdminDTO adminDTO;
		if (admin == null) {
			LOGGER.error("admin is null.");
			adminDTO =  null;
		}
		else 
			adminDTO = AdminUtils.convertToAdminDto(repository.save(admin));
		LOGGER.info("addAdmin service method is terminated.");
		return adminDTO;
	}

	@Override
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException {
		LOGGER.info("updateAdmin service method is initiated.");
		AdminDTO adminDTO;
		if (admin == null) {
			LOGGER.error("admin is null.");
			adminDTO = null;
		}
		else {
			Admin existingAdmin = repository.findById(admin.getId()).orElse(null);
			if (existingAdmin == null) {
				LOGGER.error("Invalid id.");
				throw new AdminNotFoundException("Invalid id.");
			}
			else {
				adminDTO =  AdminUtils.convertToAdminDto(repository.save(admin));
			}
		}
		LOGGER.info("updateAdmin service method is terminated.");
		return adminDTO;
	}

	@Override
	public AdminDTO cancelAdmin(int adminId) throws AdminNotFoundException {
		LOGGER.info("cancelAdmin service method is initiated.");
		AdminDTO adminDTO;
		Admin existingAdmin = repository.findById(adminId).orElse(null);
		if (existingAdmin == null) {
			LOGGER.error("Invalid id.");
			throw new AdminNotFoundException("Invalid id.");
		}
		else {
			repository.delete(existingAdmin);
			adminDTO = AdminUtils.convertToAdminDto(existingAdmin);
		}
		LOGGER.info("cancelAdmin service method is terminated.");
		return adminDTO;
	}

	@Override
	public List<AdminDTO> showAllAdmins() {
		LOGGER.info("showAllAdmins service method is initiated.");
		List<AdminDTO> listAdminDTO;
		List<Admin> listAdmins = repository.findAll();
		listAdminDTO =  AdminUtils.convertToAdminDtoList(listAdmins);
		LOGGER.info("showAllAdmins service method is terminated.");
		return listAdminDTO;
	}

	@Override
	public List<AdminDTO> showAllAdmins(int adminId) {
		LOGGER.info("showAllAdmins(int adminId) service method is initiated.");
		List<AdminDTO> listAdminDTO;
		List<Admin> listAdmins = new ArrayList<Admin>();
		Optional<Admin> adminOptional = repository.findById(adminId);
		if (adminOptional.isPresent())
			listAdmins.add(adminOptional.get());
		listAdminDTO = AdminUtils.convertToAdminDtoList(listAdmins);
		LOGGER.info("showAllAdmins(int adminId) service method is terminated.");
		return listAdminDTO;
	}
	
	

	
	
	public static boolean validateId(Admin admin) {
		boolean flag;
		int id;
		AdminServiceImpl service = new AdminServiceImpl(); 
		if (admin == null ) {
			flag = false;
		}
		else {
			id = admin.getId();
			if (id < 0 ) {
				flag = false;
			}
			else {
				flag = true;
			}
		}
		LOGGER.info("validateId is executed.");
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
		LOGGER.info("validateCustomer is executed.");
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
		LOGGER.info("validateUser is executed.");
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
		LOGGER.info("validateSweetItem is executed.");
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
		LOGGER.info("validateCategory is executed.");
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
		LOGGER.info("validateCart is executed.");
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
		LOGGER.info("validateProduct is executed.");
		return flag;
	}
	
}
