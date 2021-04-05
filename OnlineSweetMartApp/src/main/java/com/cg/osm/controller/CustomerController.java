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
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.service.ICustomerService;

@RestController
@RequestMapping(value = "/api/osm")
public class CustomerController {
	  @Autowired
	  ICustomerService service;
	  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	  
	  @PostMapping(value = "/customer/add", consumes = "application/json")
	  public CustomerDTO addCustomer(@RequestBody Customer customer)
	  {
		  return service.addCustomer(customer);	  
		  }
	  
	  
	  @PutMapping(value = "/customer/update",consumes = "application/json")
		public CustomerDTO  updateEmployee(@RequestBody Customer customer) throws CustomerNotFoundException {
			
		 return 	service.updateCustomer(customer);
			
		}
	  @DeleteMapping("/customer/cancel/{id}")
		public ResponseEntity<String>  cancelCustomerById(@PathVariable("id") int customerId) throws CustomerNotFoundException {
			
			service.cancelCustomer(customerId);
			
			return  new ResponseEntity<String>("Record Deleted Successfully",HttpStatus.OK);
			

		}
		
		
		@GetMapping(value="/customer/get-all",produces = "application/json")
		public  List<CustomerDTO>  showAllCustomers(){
			
			LOGGER.warn("Get All Executed");
			
			return 	service.showAllCustomers();
			
		}
		
		
		@GetMapping("/customer/get-by-id/{customerId}")
		public List<CustomerDTO> findByCustomerId(@PathVariable int CustomerId) {
			// TODO Auto-generated method stub
			return service.showAllCustomers(CustomerId);
		}
		
		@ExceptionHandler({CustomerNotFoundException.class})
		public ResponseEntity<String> handleException(){
		return new ResponseEntity<String> ("Customer Not Found ", HttpStatus.NOT_FOUND);
		}
		
		
		
       }
