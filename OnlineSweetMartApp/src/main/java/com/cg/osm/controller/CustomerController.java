package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/*
 * Author : JEEVETHA 
 * Version : 1.0
 * Date : 04-04-2021
 * Description : This is Customer Controller
*/


@RestController
@RequestMapping("/api/osm")
public class CustomerController {

	@Autowired
	ICustomerService service;

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//ADD CUSTOMER
	@PostMapping(value = "/customer/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		Object result;
		HttpStatus status;
		if (!CustomerServiceImp.validateCustomerUserId(customer)) {
			result = "Invalid userid";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerUsername(customer)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer)) {
			result = "Invalid set SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerSweetItem(customer)) {
			result = "Invalid list SweetItem";
			status = HttpStatus.BAD_REQUEST;
		} else {
			result = service.addCustomer(customer);
			LOGGER.info("customer is added");
			status = HttpStatus.OK;
		}

		return new ResponseEntity<Object>(result, status);
	}

	//UPDATE CUSTOMER
	@PutMapping(value = "/customer/update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		Object result;
		HttpStatus status;
		if (!CustomerServiceImp.validateCustomerUserId(customer)) {
			result = "Invalid user id";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerUsername(customer)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer)) {
			result = "Invalid set of SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		} else if (!CustomerServiceImp.validateCustomerSweetItem(customer)) {
			result = "Invalid List of sweet items";
			status = HttpStatus.BAD_REQUEST;
		} else {
			result = service.updateCustomer(customer);
			status = HttpStatus.OK;
			LOGGER.info("Customer updated");
		}

		return new ResponseEntity<Object>(result, status);
	}

	//CANCEL CUSTOMER
	@DeleteMapping(value = "/customer/cancel/{id}")
	public ResponseEntity<Object> cancelCustomer(@PathVariable("id") int customerId) throws CustomerNotFoundException {
		CustomerDTO customer_delete = null;
		ResponseEntity<Object> response = null;
		if (!(customerId < 0)) {
			customer_delete = service.cancelCustomer(customerId);
			response = new ResponseEntity(customer_delete, HttpStatus.ACCEPTED);
			LOGGER.info("Customer cancelled");
		} else {
			response = new ResponseEntity("Customer cancel failed", HttpStatus.BAD_REQUEST);
			LOGGER.warn("Enter valid customer id");
		}
		return response;
	}

	
	//SHOW ALL CUSTOMER
	@GetMapping(value = "/customer/show-all", produces = "application/json")
	public List<CustomerDTO> showAllCustomers() {
		return service.showAllCustomers();
	}

	//SHOW CUSTOMER USING CUSTOMER ID
	@GetMapping(value = "/customer/show/{CustomerId}", produces = "application/json")
	public ResponseEntity<Object> showAllCustomers(@PathVariable("id") int customerId)
			throws CustomerNotFoundException {
		List<CustomerDTO> customer_showAll = null;
		ResponseEntity<Object> response = null;
		if (!(customerId < 0)) {
			customer_showAll = service.showAllCustomers(customerId);
			response = new ResponseEntity(customer_showAll, HttpStatus.ACCEPTED);
			LOGGER.info("Customer displayed");
		} else {
			response = new ResponseEntity("Customer display failed", HttpStatus.BAD_REQUEST);
			LOGGER.warn("Enter valid customer id");
		}
		return response;
	}

}
