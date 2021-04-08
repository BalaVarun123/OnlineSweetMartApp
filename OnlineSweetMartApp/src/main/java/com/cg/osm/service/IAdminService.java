package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Admin;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.model.AdminDTO;

public interface IAdminService {
	public AdminDTO addAdmin(Admin admin);
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException;
	public AdminDTO cancelAdmin(int adminId) throws AdminNotFoundException;
	public List<AdminDTO> showAllAdmins();
	public List<AdminDTO> showAllAdmins(int adminId);
}
