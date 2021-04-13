package com.cg.osm.controller;
import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.SweetOrderInput;
import com.cg.osm.entity.User;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.model.UserDTO;
import com.cg.osm.service.ISweetOrderService;
import com.cg.osm.service.SweetOrderServiceImpl;
import com.cg.osm.util.SweetItemUtils;
import com.cg.osm.util.UserUtils;

/*
 * Author :ANNIE HEPZHIBHA K
 * Date : 07-04-2021
 * Description : This is SweetOrder Controller
*/

@RestController
@RequestMapping("/api/osm")

public class SweetOrderController {
	@Autowired
	ISweetOrderService sweetOrderService;
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	 RestTemplate restTemplate;
	/*
	 * Method       : addSweetoRDER
	 * Description  : It is used to addSweetOrder into SweetOrder Table
	 * Input Parameter  : SweetOrderObject
	 * Return Value    : It returns SweetOrderDTO Object with details
	 * PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception   : SweetOrderNotFoundException
	*/
	@PostMapping(value = "/addSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addSweetOrder(@RequestBody SweetOrderInput sweetOrder) {
		LOGGER.info("addSweetOrder() is initiated");
		Object result;
		HttpStatus status;
		SweetOrder sweetOrder1 = new SweetOrder();
		User user = UserUtils.convertToUser(restTemplate.getForObject("http://localhost:9191/api/osm/user/show/"+sweetOrder.getUserId(), UserDTO.class));
		List<Integer> itemIds = sweetOrder.getListItems();
		List<SweetItem> sweetItems = new ArrayList<SweetItem>();
		for (Integer itemId : itemIds) {
			sweetItems.add(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+itemId, SweetItemDTO.class)));
		}
		
		sweetOrder1.setUser(user);
		sweetOrder1.setListItems(sweetItems);
		sweetOrder1.setCreatedDate(sweetOrder.getCreatedDate());
		if (!SweetOrderServiceImpl.validateCreatedDate(sweetOrder1)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetOrderServiceImpl.validateUser(sweetOrder1)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateSweetOrderId(sweetOrder1)) {
			result = "Invalid sweetOrderId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateListItems(sweetOrder1)) {
			result = "Invalid listItems.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else {
			result = sweetOrderService.addSweetOrder(sweetOrder1);
			status = HttpStatus.ACCEPTED;
			 LOGGER.info("Sweet Order Added Successfully");
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	/*
	 * Method         : updateSweetOrder 
	 * Description    : It is used to update SweetOrder into SweetOrder table
	 * Input Parameter: SweetOrder Object
	 * Return Value   : It returns SweetOrderDTO Object with details
	 * PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception     : SweetOrderNotFoundException
	 */
	@PutMapping(value = "/updateSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateSweetOrder(@RequestBody SweetOrderInput sweetOrder)throws SweetOrderNotFoundException {
		LOGGER.info("updateSweetOrder() is initiated");
		Object result;
		HttpStatus status;
		
		SweetOrder sweetOrder1 = new SweetOrder();
		User user = UserUtils.convertToUser(restTemplate.getForObject("http://localhost:9191/api/osm/user/show/"+sweetOrder.getUserId(), UserDTO.class));
		List<Integer> itemIds = sweetOrder.getListItems();
		List<SweetItem> sweetItems = new ArrayList<SweetItem>();
		for (Integer itemId : itemIds) {
			sweetItems.add(SweetItemUtils.convertToSweetItem(restTemplate.getForObject("http://localhost:9191/api/osm/showSweetItem/"+itemId, SweetItemDTO.class)));
		}
		
		sweetOrder1.setUser(user);
		sweetOrder1.setListItems(sweetItems);
		sweetOrder1.setCreatedDate(sweetOrder.getCreatedDate());
		sweetOrder1.setSweetOrderId(sweetOrder.getSweetOrderId());
		
		if (!SweetOrderServiceImpl.validateCreatedDate(sweetOrder1)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetOrderServiceImpl.validateUser(sweetOrder1)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateSweetOrderId(sweetOrder1)) {
			result = "Invalid SweetOrderId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateListItems(sweetOrder1)) {
			result = "Invalid listItems.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		else {
			result = sweetOrderService.updateSweetOrder(sweetOrder1);
			status = HttpStatus.ACCEPTED;
			 LOGGER.info("Sweet Order Updated Successfully");
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	    /* Method         : cancelSweetOrder
		 * Description    : It is used to remove SweetOrder from SweetOrder table
		 * InputParameter : integer sweetOrderId
		 * Return Value   : It returns SweetOrderDTO Object with details
		 * DeleteMapping  : It is used to handle the HTTP DELETE requests matched with given URI expression.
		 * RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
		 * Exception      : SweetOrderNotFoundException
		 */
	@DeleteMapping(value="/cancelSweetOrder/{sweetOrderId}", produces = "application/json")
	
	 public ResponseEntity<Object> cancelSweetOrder(@PathVariable("sweetOrderId") int sweetOrderId) throws SweetOrderNotFoundException
	  {
		LOGGER.info("cancelSweetOrder() is initiated");
		  SweetOrderDTO sweetOrder_cancel = null;
		  ResponseEntity<Object> response = null;
		  if (!(sweetOrderId==0))
		  {
			  sweetOrder_cancel=sweetOrderService.cancelSweetOrder(sweetOrderId);
			  response =new ResponseEntity<Object>(sweetOrder_cancel,HttpStatus.ACCEPTED);
			  LOGGER.info("Sweet Order Cancelled");
		  }
		  else 
		  {
		    response =	new ResponseEntity<Object>("SweetOrder Cancellation failed",HttpStatus.BAD_REQUEST);
		  }
		  return response;  
	  }

	/*
	 * Method        : showAllSweetOrders()
	 * Description   : It is used to view allSweetOrder details present in sweetOrder table
	 * Return Value  : It returns all List<SweetOrderDTO> Object with details
	 * GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception     : SweetOrderNotFoundException
	 */
	@GetMapping(value="/showAllSweetOrders", produces = "application/json")
	public ResponseEntity<List<SweetOrderDTO>> showAllSweetOrders() {
		LOGGER.info("showAllSweetOrders() is initiated");
		List<SweetOrderDTO> showAllSweetOrders = sweetOrderService.showAllSweetOrders();
		LOGGER.info("showAllSweetOrders() has executed");
		return new ResponseEntity<List<SweetOrderDTO>>(showAllSweetOrders, HttpStatus.ACCEPTED);
		
	}
	 /* Method         :calculateTotalCost()
	 * Description    : It is used to calculate total cost of the SweetOrder from sweetOrder table
	 * Input Parameter  : integer sweetOrderId
	 * Return Value  : It returns total cost of the sweet order with details
	 * GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception     : SweetOrderNotFoundException
	 */
	
	
	 @GetMapping(value="/calculateTotalCost/{sweetOrderId}")
	  public double calculateTotalCost(@PathVariable("sweetOrderId") int sweetOrderId) throws SweetOrderNotFoundException 
		{
			if (!(sweetOrderId==0))
			 return sweetOrderService.calculateTotalCost(sweetOrderId);
			else 
				return 0;
		}
	    /* Method           :showSweetOrderBySweetOrderId()
		 * Description      : It is used to view SweetOrder from sweetOrder table
		 * InputParameter: integer sweetOrderId
		 * Return Value: It returns SweetOrderDTO Object with details
		 * GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
		 * RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
		 * Exception     : SweetOrderNotFoundException
		 */
		
	 @GetMapping(value="/showAllSweetOrder/{id}", produces = "application/json")
		public SweetOrderDTO showSweetOrder(@PathVariable("id") int sweetOrderId) throws SweetOrderNotFoundException {
		 LOGGER.info("showSweetOrderBySweetOrderId() is initiated");
		 LOGGER.info("showSweetOrderBySweetOrderId() has executed");
		 return sweetOrderService.showSweetOrder(sweetOrderId);
		 
		}
	 
}