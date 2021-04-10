package com.cg.osm.controller;
import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.SweetOrderInput;
import com.cg.osm.entity.User;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.model.UserDTO;
import com.cg.osm.service.ISweetOrderService;
import com.cg.osm.service.SweetOrderServiceImpl;
import com.cg.osm.util.SweetItemUtils;
import com.cg.osm.util.SweetOrderUtils;
import com.cg.osm.util.UserUtils;


@RestController
@RequestMapping("/api/osm")

public class SweetOrderController {
	@Autowired
	ISweetOrderService sweetOrderService;
	@Autowired
	 RestTemplate restTemplate;
	@PostMapping(value = "/addSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addSweetOrder(@RequestBody SweetOrderInput sweetOrder) {
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
		/*
		 * else if (!SweetOrderServiceImpl.vaidateGroupedProducts(sweetOrder)) { result
		 * = "Invalid groupedProducts."; status = HttpStatus.BAD_REQUEST; }
		 */
		
		
		else {
			result = sweetOrderService.addSweetOrder(sweetOrder1);
			status = HttpStatus.ACCEPTED;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	@PutMapping(value = "/updateSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateSweetOrder(@RequestBody SweetOrderInput sweetOrder)throws SweetOrderNotFoundException {
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
		/*
		 * else if (!SweetOrderServiceImpl.vaidateGroupedProducts(sweetOrder)) { result
		 * = "Invalid groupedProducts."; status = HttpStatus.BAD_REQUEST; }
		 */
		
		
		else {
			result = sweetOrderService.updateSweetOrder(sweetOrder1);
			status = HttpStatus.ACCEPTED;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	@DeleteMapping(value="/cancelSweetOrder/{sweetOrderId}", produces = "application/json")
	
	 public ResponseEntity<Object> cancelSweetOrder(@PathVariable("sweetOrderId") int sweetOrderId) throws SweetOrderNotFoundException
	  {
		  SweetOrderDTO sweetOrder_cancel = null;
		  ResponseEntity<Object> response = null;
		  if (!(sweetOrderId==0))
		  {
			  sweetOrder_cancel=sweetOrderService.cancelSweetOrder(sweetOrderId);
			  response =new ResponseEntity<Object>(sweetOrder_cancel,HttpStatus.ACCEPTED);
		  }
		  else 
		  {
		    response =	new ResponseEntity<Object>("SweetOrder Cancellation failed",HttpStatus.BAD_REQUEST);
		  }
		  return response;  
	  }
	@GetMapping(value="/showAllSweetOrders", produces = "application/json")
	public ResponseEntity<List<SweetOrderDTO>> showAllSweetOrders() {
		List<SweetOrderDTO> showAllSweetOrders = sweetOrderService.showAllSweetOrders();
		return new ResponseEntity<List<SweetOrderDTO>>(showAllSweetOrders, HttpStatus.ACCEPTED);
	}
	 @GetMapping(value="/calculateTotalCost/{sweetOrderId}")
	  public double calculateTotalCost(@PathVariable("sweetOrderId") int sweetOrderId) throws SweetOrderNotFoundException 
		{
			if (!(sweetOrderId==0))
			 return sweetOrderService.calculateTotalCost(sweetOrderId);
			else 
				return 0;
		}
	 @GetMapping(value="/showAllSweetOrder/{id}", produces = "application/json")
		public SweetOrderDTO showSweetOrder(@PathVariable("id") int sweetOrderId) throws SweetOrderNotFoundException {
		 return sweetOrderService.showSweetOrder(sweetOrderId);
		}
	 
}