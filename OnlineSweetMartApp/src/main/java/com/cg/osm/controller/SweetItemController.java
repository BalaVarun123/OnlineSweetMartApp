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
import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.SweetItemServiceImp;

@RestController
@RequestMapping("/api/osm")

public class SweetItemController{
	@Autowired
	ISweetItemService sweetItemService;
	 final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@PostMapping(value = "/addSweetItem", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addSweetItem(@RequestBody SweetItem sweetItem) {
		Object result;
		HttpStatus status;
		if (!SweetItemServiceImp.validateSweetItemProduct(sweetItem)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem)) {
			result = "Invalid SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem)) {
			result = "Invalid orderItemId.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else {
			result = sweetItemService.addSweetItem(sweetItem);
			status = HttpStatus.ACCEPTED;
			 LOGGER.info("Sweet Item Added Successfully");
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	@PutMapping(value = "/updateSweetItem", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateSweetItem(@RequestBody SweetItem sweetItem) throws SweetItemNotFoundException {
		Object result;
		HttpStatus status;
		if (!SweetItemServiceImp.validateSweetItemProduct(sweetItem)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem)) {
			result = "Invalid SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem)) {
			result = "Invalid orderItemId.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else {
			result = sweetItemService.updateSweetItem(sweetItem);
			status = HttpStatus.ACCEPTED;
			LOGGER.info("Sweet Item Updated Successfully");
		}
		
		return new ResponseEntity<Object>(result,status);
		 
	}
	@DeleteMapping(value="/cancelSweetItem/{orderItemId}", produces = "application/json")
	
		 public ResponseEntity<Object> cancelSweetItem(@PathVariable("orderItemId") int orderItemId) throws SweetItemNotFoundException
		  {
			  SweetItemDTO sweetItem_cancel = null;
			  ResponseEntity<Object> response = null;
			  if (!(orderItemId==0))
			  {
				  sweetItem_cancel=sweetItemService.cancelSweetItem(orderItemId);
				  response =new ResponseEntity<Object>(sweetItem_cancel,HttpStatus.ACCEPTED);
				  LOGGER.info("Sweet Item Cancelled");
			  }
			  else 
			  {
			    response =	new ResponseEntity<Object>("SweetItem Cancellation failed",HttpStatus.BAD_REQUEST);
			  }
			  return response;   
		  }
	
	@GetMapping(value="/showAllSweetItems", produces = "application/json")
	public ResponseEntity<List<SweetItemDTO>> showAllSweetItems() {
		List<SweetItemDTO> showAllSweetItems = sweetItemService.showAllSweetItems();
		LOGGER.info("Showing All Sweet Items");
		return new ResponseEntity<List<SweetItemDTO>>(showAllSweetItems, HttpStatus.ACCEPTED);
		
	}
	
}
