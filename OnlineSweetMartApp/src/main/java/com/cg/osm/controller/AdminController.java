package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Admin;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.service.AdminServiceImpl;
import com.cg.osm.service.IAdminService;

@RestController
@RequestMapping("/api/osm")
public class AdminController {

	
	@Autowired
	IAdminService service;
	
	
	
	@PostMapping(value = "/admin/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  addAdmin(@RequestBody Admin admin) {
		HttpStatus status;
		Object result;
		
		if (!AdminServiceImpl.validateId(admin)) {
			result = "Invalid id.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCustomer(admin)) {
			result = "Invalid customer.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateUser(admin)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}else if (!AdminServiceImpl.validateSweetItem(admin)) {
			result = "Invalid item.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCategory(admin)) {
			result = "Invalid category.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCart(admin)) {
			result = "Invalid cart.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateProduct(admin)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addAdmin(admin);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<Object>(result,status);
		
	}
	
	
	@PutMapping(value = "/admin/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException {
		HttpStatus status;
		Object result;
		
		if (!AdminServiceImpl.validateId(admin)) {
			result = "Invalid id.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCustomer(admin)) {
			result = "Invalid customer.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateUser(admin)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}else if (!AdminServiceImpl.validateSweetItem(admin)) {
			result = "Invalid item.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCategory(admin)) {
			result = "Invalid category.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCart(admin)) {
			result = "Invalid cart.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateProduct(admin)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateAdmin(admin);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<Object>(result,status);
		
	}
	
	@DeleteMapping(value = "/admin/cancel/{adminId}", produces = "application/json")
	public AdminDTO cancelAdmin(@PathVariable("adminId") int adminId) throws AdminNotFoundException{
		return service.cancelAdmin(adminId);
	}
	
	@GetMapping(value = "/admin/show-all", produces = "application/json")
	public List<AdminDTO> showAllAdmins(){
		return service.showAllAdmins();
	}
	
	@GetMapping(value = "/admin/show/{adminId}", produces = "application/json")
	public List<AdminDTO> showAllAdmins(@PathVariable("adminId") int adminId){
		return service.showAllAdmins(adminId);
	}
	

}