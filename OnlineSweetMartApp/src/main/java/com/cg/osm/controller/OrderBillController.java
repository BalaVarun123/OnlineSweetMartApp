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

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.service.IOrderBillService;
import com.cg.osm.service.OrderBillServiceImpl;

@RestController
@RequestMapping("/api/osm")
public class OrderBillController {
	
	@Autowired
	IOrderBillService service;
	
	
	
	
	@PostMapping(value = "/order-bill/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addOrderBill(@RequestBody OrderBill orderbill) {
		Object result;
		HttpStatus status;
		if (!OrderBillServiceImpl.validateOrderBillCreatedDate(orderbill)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillId(orderbill)) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillListSweetOrder(orderbill)) {
			result = "Invalid listSweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillTotalCost(orderbill)) {
			result = "Invalid totalCost.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addOrderBill(orderbill);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	
	@PutMapping(value = "/order-bill/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateOrderBill(@RequestBody OrderBill orderbill) throws OrderBillNotFoundException {
		Object result;
		HttpStatus status;
		if (!OrderBillServiceImpl.validateOrderBillCreatedDate(orderbill)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillId(orderbill)) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillListSweetOrder(orderbill)) {
			result = "Invalid listSweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillTotalCost(orderbill)) {
			result = "Invalid totalCost.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateOrderBill(orderbill);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	@DeleteMapping(value = "/order-bill/cancel/{orderBillId}")
	public ResponseEntity<Object> cancelOrderBill(@PathVariable("orderBillId") int orderBillId) throws OrderBillNotFoundException{
		Object result;
		HttpStatus status;
		if (orderBillId < 0) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.cancelOrderBill(orderBillId);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Object> (result,status);
	}
	
	@GetMapping(value = "/order-bill/show-all", produces = "application/json")
	public List<OrderBillDTO> showAllOrderBills(){
		return service.showAllOrderBills();
	}
	
	@GetMapping(value = "/order-bill/show/{orderBillId}", produces = "application/json")
	public ResponseEntity<Object> showAllOrderBills(@PathVariable("orderBillId") int orderBillId){
		Object result;
		HttpStatus status;
		if (orderBillId < 0) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.showAllOrderBills(orderBillId);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<Object> (result,status);
	}
	

	
	
}
