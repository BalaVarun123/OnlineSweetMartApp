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
import org.springframework.web.client.RestTemplate;

import com.cg.osm.entity.Admin;
import com.cg.osm.entity.AdminInput;
import com.cg.osm.entity.Customer;
import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.model.CartDTO;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.model.UserDTO;
import com.cg.osm.service.AdminServiceImpl;
import com.cg.osm.service.IAdminService;
import com.cg.osm.util.CartUtils;
import com.cg.osm.util.CategoryUtils;
import com.cg.osm.util.CustomerUtils;
import com.cg.osm.util.ProductUtils;
import com.cg.osm.util.SweetItemUtils;
import com.cg.osm.util.UserUtils;

@RestController
@RequestMapping("/api/osm")
public class AdminController {

	
	@Autowired
	IAdminService service;
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping(value = "/admin/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  addAdmin(@RequestBody AdminInput admin) {
		HttpStatus status;
		Object result;
		Admin admin1 = new Admin();
	
		admin1.setCustomer(CustomerUtils.convertToCustomer(restTemplate.getForObject("http://localhost:9191/api/osm/customer/show/"+admin.getCustomerId(), CustomerDTO.class)));
		admin1.setUser(UserUtils.convertToUser(restTemplate.getForObject("http://localhost:9191/api/osm/user/show/"+admin.getUserId(), UserDTO.class)));
		admin1.setItem(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+admin.getItemId(), SweetItemDTO.class)));
		admin1.setCategory(CategoryUtils.convertToCategory(restTemplate.getForObject("http://localhost:9191/api/osm/category/show/"+admin.getCategory(), CategoryDTO.class)));
		admin1.setCart(CartUtils.convertToCart(restTemplate.getForObject("http://localhost:9191/api/osm/show-cart-by-id/"+admin.getCart(), CartDTO.class)));
		admin1.setProduct(ProductUtils.convertToProduct(restTemplate.getForObject("http://localhost:9191/api/osm/product/show-by-id/"+admin.getProduct(), ProductDTO.class)));
		
		if (!AdminServiceImpl.validateId(admin1)) {
			result = "Invalid id.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCustomer(admin1)) {
			result = "Invalid customer.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateUser(admin1)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}else if (!AdminServiceImpl.validateSweetItem(admin1)) {
			result = "Invalid item.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCategory(admin1)) {
			result = "Invalid category.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCart(admin1)) {
			result = "Invalid cart.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateProduct(admin1)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addAdmin(admin1);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<Object>(result,status);
		
	}
	
	
	@PutMapping(value = "/admin/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  updateAdmin(@RequestBody AdminInput admin) throws AdminNotFoundException {
		HttpStatus status;
		Object result;
		Admin admin1 = new Admin();
		
		admin1.setCustomer(CustomerUtils.convertToCustomer(restTemplate.getForObject("http://localhost:9191/api/osm/customer/show/"+admin.getCustomerId(), CustomerDTO.class)));
		admin1.setUser(UserUtils.convertToUser(restTemplate.getForObject("http://localhost:9191/api/osm/user/show/"+admin.getUserId(), UserDTO.class)));
		admin1.setItem(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+admin.getItemId(), SweetItemDTO.class)));
		admin1.setCategory(CategoryUtils.convertToCategory(restTemplate.getForObject("http://localhost:9191/api/osm/category/show/"+admin.getCategory(), CategoryDTO.class)));
		admin1.setCart(CartUtils.convertToCart(restTemplate.getForObject("http://localhost:9191/api/osm/show-cart-by-id/"+admin.getCart(), CartDTO.class)));
		admin1.setProduct(ProductUtils.convertToProduct(restTemplate.getForObject("http://localhost:9191/api/osm/product/show-by-id/"+admin.getProduct(), ProductDTO.class)));
		admin1.setId(admin.getId());
		
		if (!AdminServiceImpl.validateId(admin1)) {
			result = "Invalid id.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCustomer(admin1)) {
			result = "Invalid customer.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateUser(admin1)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}else if (!AdminServiceImpl.validateSweetItem(admin1)) {
			result = "Invalid item.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCategory(admin1)) {
			result = "Invalid category.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateCart(admin1)) {
			result = "Invalid cart.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!AdminServiceImpl.validateProduct(admin1)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateAdmin(admin1);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<Object>(result,status);
		
	}
	
	@DeleteMapping(value = "/admin/cancel/{adminId}", produces = "application/json")
	public ResponseEntity<Object> cancelAdmin(@PathVariable("adminId") int adminId) throws AdminNotFoundException{
		Object result;
		HttpStatus status;
		if (adminId < 0) {
			result = "Invalid adminId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.cancelAdmin(adminId);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Object> (result,status);
	}
	
	@GetMapping(value = "/admin/show-all", produces = "application/json")
	public List<AdminDTO> showAllAdmins(){
		return service.showAllAdmins();
	}
	
	@GetMapping(value = "/admin/show/{adminId}", produces = "application/json")
	public ResponseEntity<Object> showAllAdmins(@PathVariable("adminId") int adminId){
		Object result;
		HttpStatus status;
		if (adminId < 0) {
			result = "Invalid adminId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.showAllAdmins(adminId);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Object> (result,status);
	}
	

}
