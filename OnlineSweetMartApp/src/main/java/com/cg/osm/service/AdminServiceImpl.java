package com.cg.osm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Admin;
import com.cg.osm.entity.SweetOrder;
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
		Optional<Admin> orderBIllOptional = repository.findById(adminId);
		if (orderBIllOptional.isPresent())
			listAdmins.add(orderBIllOptional.get());
		return AdminUtils.convertToAdminDtoList(listAdmins);
	}
	
	
	public static boolean validateAdmin(Admin admin) {
		boolean flag;
		if (!(validateAdminCreatedDate(admin) && validateAdminListSweetOrder(admin) &&  validateAdminId(admin) && validateAdminTotalCost(admin))) {
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
			if () {
				flag = false;
			}
			else {
				flag = true;
			}
		}
		return flag;
	}

}
