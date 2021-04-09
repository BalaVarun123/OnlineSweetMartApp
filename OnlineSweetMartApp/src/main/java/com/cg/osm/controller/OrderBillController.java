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

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.OrderBillInput;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.service.IOrderBillService;
import com.cg.osm.service.OrderBillServiceImpl;
import com.cg.osm.util.SweetOrderUtils;

@RestController
@RequestMapping("/api/osm")
public class OrderBillController {
	
	@Autowired
	IOrderBillService service;
	@Autowired
	RestTemplate restTemplate;
	
	
	
	@PostMapping(value = "/order-bill/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addOrderBill(@RequestBody OrderBillInput orderBill) {
		Object result;
		HttpStatus status;
		
		OrderBill orderBill1 = new OrderBill();
		orderBill1.setCreatedDate(orderBill.getCreatedDate());
		orderBill1.setTotalCost(orderBill.getTotalCost());
		List<Integer> orderIds = orderBill.getListSweetOrder();
		List<SweetOrder> sweetOrders = new ArrayList<SweetOrder>();
		for (Integer orderId : orderIds) {
			sweetOrders.add(SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+orderId, SweetOrderDTO.class)));
		}
		orderBill1.setListSweetOrder(sweetOrders);
		
		if (!OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill1)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillId(orderBill1)) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill1)) {
			result = "Invalid listSweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillTotalCost(orderBill1)) {
			result = "Invalid totalCost.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addOrderBill(orderBill1);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	
	@PutMapping(value = "/order-bill/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateOrderBill(@RequestBody OrderBillInput orderBill) throws OrderBillNotFoundException {
		Object result;
		HttpStatus status;
		
		OrderBill orderBill1 = new OrderBill();
		orderBill1.setCreatedDate(orderBill.getCreatedDate());
		orderBill1.setTotalCost(orderBill.getTotalCost());
		List<Integer> orderIds = orderBill.getListSweetOrder();
		List<SweetOrder> sweetOrders = new ArrayList<SweetOrder>();
		for (Integer orderId : orderIds) {
			sweetOrders.add(SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+orderId, SweetOrderDTO.class)));
		}
		orderBill1.setListSweetOrder(sweetOrders);
		orderBill1.setOrderBillId(orderBill.getOrderBillId());
		if (!OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill1)) {
			result = "Invalid createdDate.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillId(orderBill1)) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill1)) {
			result = "Invalid listSweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!OrderBillServiceImpl.validateOrderBillTotalCost(orderBill1)) {
			result = "Invalid totalCost.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.updateOrderBill(orderBill1);
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
