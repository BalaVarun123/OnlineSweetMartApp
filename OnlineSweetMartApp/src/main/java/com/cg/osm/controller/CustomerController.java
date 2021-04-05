package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.service.ICustomerService;
import com.cg.osm.service.CustomerServiceImp;

@RestController
@RequestMapping("/api/osm")
public class CustomerController {

	@Autowired
	ICustomerService service;

	@PostMapping(value = "/customer/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		Object result;
		HttpStatus status;
		if (!CustomerServiceImp.validateCustomerUserId(customer)) {
			result = "Invalid userid";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerUsername(customer)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer)) {
			result = "Invalid listSweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSweetItem(customer)) {
			result = "Invalid listSweetItem";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addCustomer(customer);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	
	@PutMapping(value = "/customer/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		Object result;
		HttpStatus status;
		if (!CustomerServiceImp.validateCustomerUserId(customer)) {
			result = "Invalid user id";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerUsername(customer)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer)) {
			result = "Invalid list of SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSweetItem(customer)) {
			result = "Invalid List of sweet items";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateCustomer(customer);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	@DeleteMapping(value = "/customer/cancel/", produces = "application/json")
	public CustomerDTO cancelCustomer(@PathVariable("Customer") int customer) throws CustomerNotFoundException{
		return service.cancelCustomer(customer);
	}
	
	@GetMapping(value = "/customer/show-all", produces = "application/json")
	public List<CustomerDTO> showAllCustomers(){
		return service.showAllCustomers();
	}
	
	@GetMapping(value = "/customer/show/", produces = "application/json")
	public List<CustomerDTO> showAllCustomers(int customerId){
		return service.showAllCustomers();
	}

	
}


