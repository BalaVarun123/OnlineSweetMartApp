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

	/*
	 * Author      : BALASUBRAMANIAN S
	 * Version     : 1.0
	 * Date        : 04-04-2021
	 * Description : RestController class for AdminService.
	*/
	
	@Autowired
	IAdminService service;
	@Autowired
	RestTemplate restTemplate;
	static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	
	

	/************************************************************************************
	 * Method       : addAdmin 
	 * Description  : It is used to add Admin instance.
	 * @param       : AdminInput Object
	 * @returns     : It returns ResponseEntity<Object> Object.
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By   : BALASUBRAMANIAN S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	
	
	@PostMapping(value = "/admin/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  addAdmin(@RequestBody AdminInput admin) {
		LOGGER.info("/admin/add URL is opened.");
		LOGGER.info("addAdmin is initiated.");
		
		
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
		LOGGER.info("addAdmin is terminated with http status :"+status);
		return new ResponseEntity<Object>(result,status);
		
	}
	
	
	

	/************************************************************************************
	 * Method       : updateAdmin 
	 * Description  : It is used to update Admin details.
	 * @param       : AdminInput Object
	 * @returns     : It returns ResponseEntity<Object>.
	 * @PutMapping  : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception   : AdminNotFoundException
	 * Created By   : BALASUBRAMANIAN S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	
	@PutMapping(value = "/admin/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object>  updateAdmin(@RequestBody AdminInput admin) throws AdminNotFoundException {
		
		LOGGER.info("/admin/update URL is opened.");
		LOGGER.info("updateAdmin is initiated.");
		
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
		LOGGER.info("updateAdmin is terminated with http status :"+status);
		return new ResponseEntity<Object>(result,status);
		
	}
	
	

	/************************************************************************************
	 * Method         : cancelAdmin 
	 * Description    : It is used to remove Admin details from the database.
	 * @param         : int adminId
	 * @returns       : It returns ResponseEntity<Object> Object.
	 * @DeleteMapping : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable  : It is used to get an integer value from the URL.
	 * @exception     : AdminNotFoundException
	 * Created By     : BALASUBRAMANIAN S
     * Created Date   : 04-04-2021 
     *
	 ************************************************************************************/
	@DeleteMapping(value = "/admin/cancel/{adminId}", produces = "application/json")
	public ResponseEntity<Object> cancelAdmin(@PathVariable("adminId") int adminId) throws AdminNotFoundException{
		LOGGER.info("/admin/cancel/ URL is opened.");
		LOGGER.info("cancelAdmin is initiated.");
		
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
		
		LOGGER.info("cancelAdmin is terminated with http status :"+status);
		
		return new ResponseEntity<Object> (result,status);
	}
	
	

	/************************************************************************************
	 * Method       : showAllAdmins 
	 * Description  : It is used to get all Admin records.
	 * @returns     : It returns List<AdminDTO> Object.
	 * @GetMapping  : It is used to handle the HTTP GET requests matched with given URI expression..
	 * Created By   : BALASUBRAMANIAN S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	
	@GetMapping(value = "/admin/show-all", produces = "application/json")
	public List<AdminDTO> showAllAdmins(){
		LOGGER.info("/admin/show-all URL is opened.");
		LOGGER.info("showAllAdmins is initiated.");
		List<AdminDTO> listDTO = service.showAllAdmins();
		LOGGER.info("showAllAdmins is terminated.");
		return listDTO;
	}
	
	
	

	/************************************************************************************
	 * Method       : showAdmin 
	 * Description  : It is used to get Admin by adminId.
	 * @param       : int adminId.
	 * @returns     : It returns ResponseEntity<Object> Object.
	 * @GetMapping  : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @PathVariable: It is used to get an integer value from the URL.
	 * Created By   : BALASUBRAMANIAN S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	@GetMapping(value = "/admin/show/{adminId}", produces = "application/json")
	public ResponseEntity<Object> showAdmin(@PathVariable("adminId") int adminId){
		LOGGER.info("/admin/show/ URL is opened.");
		LOGGER.info("showAdmin is initiated.");
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
		LOGGER.info("showAdmin is terminated with http status :"+status);
		return new ResponseEntity<Object> (result,status);
	}
	

}
