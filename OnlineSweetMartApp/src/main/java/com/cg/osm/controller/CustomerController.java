package com.cg.osm.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.osm.entity.Customer;
import com.cg.osm.entity.CustomerInput;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.service.ICustomerService;
import com.cg.osm.util.CartUtils;
import com.cg.osm.util.SweetItemUtils;
import com.cg.osm.util.SweetOrderUtils;
import com.cg.osm.service.CustomerServiceImp;

/*
 * Author      : Jeevetha S
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : This is Customer Controller
*/

@RestController
@RequestMapping("/api/osm")
public class CustomerController {

	@Autowired
	ICustomerService service;
	@Autowired
	RestTemplate restTemplate;
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());

	/************************************************************************************
	 * Method       : addCustomer 
	 * Description  : Adding a new customer in the table
	 * @param cart  : Customer Object
	 * @returns     : It returns ResponseEntity Object with details
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception   : CustomerNotFoundException
	 * Created By   : Jeevetha S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	
	@PostMapping(value = "/customer/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addCustomer(@RequestBody CustomerInput customer) throws CustomerNotFoundException {
		Object result;
		HttpStatus status;
		
		Customer customer1 = new Customer();
		customer1.setUsername(customer.getUsername());
		customer1.setUserId(customer.getUserId());
		List<Integer> itemIds = customer.getSweetItems();
		List<SweetItem> sweetItems = new ArrayList<SweetItem>();
		for (Integer itemId : itemIds) {
			sweetItems.add(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+itemId, SweetItemDTO.class)));
		}
		List<Integer> orderIds = customer.getSweetOrders();
		Set<SweetOrder> sweetOrders = new HashSet<SweetOrder>();
		for (Integer orderId : orderIds) {
			sweetOrders.add(SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+orderId, SweetOrderDTO.class)));
		}
		
		customer1.setSweetItems(sweetItems);
		customer1.setSweetOrders(sweetOrders);
		customer1.setCart(CartUtils.convertToCart(restTemplate.getForObject("http://localhost:9191/api/osm/show-cart-by-id/"+customer.getCartId(), CartDTO.class)));
		if (!CustomerServiceImp.validateCustomerUserId(customer1)) {
			result = "Invalid userid";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerUsername(customer1)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer1)) {
			result = "Invalid set SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSweetItem(customer1)) {
			result = "Invalid list SweetItem";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addCustomer(customer1);
			LOGGER.info("customer is added");
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	

	
	/************************************************************************************
	 * Method         : updateCustomer
	 * Description    : It updates the customer
	 * @param cart    : Customer Object
	 * @returns cart  : It returns ResponseEntity Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CustomerNotFoundException
	 * Created By     : Jeevetha S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	
	@PutMapping(value = "/customer/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerInput customer) throws CustomerNotFoundException {
		Object result;
		HttpStatus status;
		

		Customer customer1 = new Customer();
		customer1.setUsername(customer.getUsername());
		customer1.setUserId(customer.getUserId());
		//SweetOrder sweetOrder = SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+sweetItem.getSweetOrderId(), SweetOrderDTO.class));
		List<Integer> itemIds = customer.getSweetItems();
		List<SweetItem> sweetItems = new ArrayList<SweetItem>();
		for (Integer itemId : itemIds) {
			sweetItems.add(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+itemId, SweetItemDTO.class)));
		}
		List<Integer> orderIds = customer.getSweetOrders();
		Set<SweetOrder> sweetOrders = new HashSet<SweetOrder>();
		for (Integer orderId : orderIds) {
			sweetOrders.add(SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+orderId, SweetOrderDTO.class)));
		}
		
		customer1.setSweetItems(sweetItems);
		customer1.setSweetOrders(sweetOrders);
		customer1.setCart(CartUtils.convertToCart(restTemplate.getForObject("http://localhost:9191/api/osm/show-cart-by-id/"+customer.getCartId(), CartDTO.class)));
		
		
		
		if (!CustomerServiceImp.validateCustomerUserId(customer1)) {
			result = "Invalid user id";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerUsername(customer1)) {
			result = "Invalid username";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSetSweetOrders(customer1)) {
			result = "Invalid set of SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!CustomerServiceImp.validateCustomerSweetItem(customer1)) {
			result = "Invalid List of sweet items";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateCustomer(customer1);
			status = HttpStatus.OK;
			LOGGER.info("Customer updated");
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	
	/************************************************************************************
	 * Method         : CancelCustomer
	 * Description    : Deletes the customer
	 * @param cart    : Customer Object
	 * @returns cart  : It returns ResponseEntity Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CustomerNotFoundException
	 * Created By     : Jeevetha S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping(value="/customer/cancel/{id}")
	  public ResponseEntity<Object> cancelCustomer(@PathVariable("id") int customerId) throws CustomerNotFoundException
	  {
		  CustomerDTO customer_delete = null;
		  ResponseEntity<Object> response = null;
		  if (!(customerId<0))
		  {
			  customer_delete=service.cancelCustomer(customerId);
			  response =	new ResponseEntity(customer_delete,HttpStatus.ACCEPTED);
			  LOGGER.info("Customer cancelled");
		  }
		  else 
		  {
		    response =	new ResponseEntity("Customer cancel failed",HttpStatus.BAD_REQUEST);
		    LOGGER.warn("Enter valid customer id");
		  }
		  return response;   
	  }
	
	
	/************************************************************************************
	 * Method         : showAllCustomers
	 * Description    : Displays the customer
	 * @param cart    : Customer Object
	 * @returns cart  : It returns ResponseEntity Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CustomerNotFoundException
	 * Created By     : Jeevetha S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	
	@GetMapping(value = "/customer/show-all", produces = "application/json")
	public List<CustomerDTO> showAllCustomers(){
		return service.showAllCustomers();
	}
	
	/************************************************************************************
	 * Method         : showAllCustomers
	 * Description    : Displays the customer using customer id
	 * @param cart    : Customer Object
	 * @returns cart  : It returns ResponseEntity Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CustomerNotFoundException
	 * Created By     : Jeevetha S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	
	
	@GetMapping(value = "/customer/show/{id}", produces = "application/json")
	public CustomerDTO  showAllCustomers(@PathVariable("id") int customerId) throws CustomerNotFoundException
	  {
		List<CustomerDTO> customer_showAll = null;
		CustomerDTO response;
		  if (!(customerId<0))
		  {
			  customer_showAll=service.showAllCustomers(customerId);
			  response =customer_showAll.get(0);
			  LOGGER.info("Customer displayed");
		  }
		  else 
		  {
		    response =	null;
		    LOGGER.warn("Enter valid customer id");
		  }
		   return response;
	  }
	

	
	}
	

