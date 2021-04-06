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
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.service.ISweetOrderService;
import com.cg.osm.service.SweetOrderServiceImpl;


@RestController
@RequestMapping("/api/osm")

public class SweetOrderController {
	@Autowired
	ISweetOrderService sweetOrderService;
	
	@PostMapping(value = "/addSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addSweetOrder(@RequestBody SweetOrder sweetOrder) {
		Object result;
		HttpStatus status;
		if (!SweetOrderServiceImpl.validateCreatedDate(sweetOrder)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetOrderServiceImpl.validateUser(sweetOrder)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateSweetOrderId(sweetOrder)) {
			result = "Invalid sweetOrderId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateListItems(sweetOrder)) {
			result = "Invalid listItems.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.vaidateGroupedProducts(sweetOrder)) {
			result = "Invalid groupedProducts.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		else {
			result = sweetOrderService.addSweetOrder(sweetOrder);
			status = HttpStatus.ACCEPTED;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	@PutMapping(value = "/updateSweetOrder", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateSweetOrder(@RequestBody SweetOrder sweetOrder)throws SweetOrderNotFoundException {
		Object result;
		HttpStatus status;
		if (!SweetOrderServiceImpl.validateCreatedDate(sweetOrder)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetOrderServiceImpl.validateUser(sweetOrder)) {
			result = "Invalid user.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateSweetOrderId(sweetOrder)) {
			result = "Invalid SweetOrderId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.validateListItems(sweetOrder)) {
			result = "Invalid listItems.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetOrderServiceImpl.vaidateGroupedProducts(sweetOrder)) {
			result = "Invalid groupedProducts.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		else {
			result = sweetOrderService.updateSweetOrder(sweetOrder);
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
	  public double calculateTotalCost(@PathVariable("sweetOrderId") int sweetOrderId) 
		{
			if (!(sweetOrderId==0))
			 return sweetOrderService.calculateTotalCost(sweetOrderId);
			else 
				return 0;			
		}
}
		
	  










