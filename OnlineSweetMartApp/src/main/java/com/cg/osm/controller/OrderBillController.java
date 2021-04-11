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

/*
 * Author      : BALASUBRAMANIAN S
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : RestController class for OrderBill service. 
*/

@RestController
@RequestMapping("/api/osm")
public class OrderBillController {
	
	@Autowired
	IOrderBillService service;
	@Autowired
	RestTemplate restTemplate;
	
	static final Logger LOGGER = LoggerFactory.getLogger(OrderBillController.class);
	
	
	
	
	/************************************************************************************
	 * Method       : addOrderBill 
	 * Description  : It is used to add OrderBill details into order_bill Table
	 * @param 		: OrderBillInput Object
	 * @returns     : It returns ResponseEntity<Object>.
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By   : BALASUBRAMANIAN S
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/
	
	@PostMapping(value = "/order-bill/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addOrderBill(@RequestBody OrderBillInput orderBill) {
		LOGGER.info("/order-bill/add URL is opened.");
		LOGGER.info("addOrderBill is initiated.");
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
		LOGGER.info("addOrderBill is terminated with http status :"+status);
		return new ResponseEntity<Object>(result,status);
	}
	
	/************************************************************************************
	 * Method         : updateOrderBill 
	 * Description    : It is used to update OrderBill  details in order_bill table.
	 * @param         : OrderBillInput Object
	 * @returns       : It returns ResponseEntity<Object>.
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : OrderBillNotFoundException
	 * Created By     : BALASUBRAMANIAN S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping(value = "/order-bill/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateOrderBill(@RequestBody OrderBillInput orderBill) throws OrderBillNotFoundException {
		LOGGER.info("/order-bill/update URL is opened.");
		LOGGER.info("updateOrderBill is initiated.");
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
		LOGGER.info("updateOrderBill is terminated with http status :"+status);	
		return new ResponseEntity<Object>(result,status);
	}
	
	/************************************************************************************
	 * Method         : cancelOrderBill 
	 * Description    : It is used to remove OrderBill  details from order_bill table.
	 * @param         : int orderBillId
	 * @returns       : It returns ResponseEntity<Object>.
	 * @DeleteMapping : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable  : It is used to get an integer value from the URL.
	 * @exception     : OrderBillNotFoundException
	 * Created By     : BALASUBRAMANIAN S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping(value = "/order-bill/cancel/{orderBillId}")
	public ResponseEntity<Object> cancelOrderBill(@PathVariable("orderBillId") int orderBillId) throws OrderBillNotFoundException{
		Object result;
		HttpStatus status;
		LOGGER.info("/order-bill/cancel/ URL is opened.");
		LOGGER.info("cancelOrderBill is initiated.");
		if (orderBillId < 0) {
			result = "Invalid orderBillId.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.cancelOrderBill(orderBillId);
			status = HttpStatus.OK;
		}
		LOGGER.info("cancelOrderBill is terminated with http status :"+status);	
		return new ResponseEntity<Object> (result,status);
	}
	
	
	/************************************************************************************
	 * Method         : showAllOrderBills 
	 * Description    : It is used to get all OrderBill  details from the order_bill table.
	 * @returns       : It returns List<OrderBillDTO> object.
	 * @GetMapping	  : It is used to handle the HTTP GET requests matched with given URI expression.
	 * Created By     : BALASUBRAMANIAN S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping(value = "/order-bill/show-all", produces = "application/json")
	public List<OrderBillDTO> showAllOrderBills(){
		LOGGER.info("/order-bill/show-all URL is opened.");
		LOGGER.info("showAllOrderBills is initiated.");
		List<OrderBillDTO> listDTO = service.showAllOrderBills();
		LOGGER.info("showAllOrderBills is terminated.");	
		return listDTO;
	}
	
	
	
	/************************************************************************************
	 * Method         : showOrderBill 
	 * Description    : It is used to get OrderBill  details by orderBillId from order_bill table.
	 * @param         : int orderBillId
	 * @returns       : It returns ResponseEntity<Object>.
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @PathVariable  : It is used to get an integer value from the URL.
	 * Created By     : BALASUBRAMANIAN S
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping(value = "/order-bill/show/{orderBillId}", produces = "application/json")
	public ResponseEntity<Object> showOrderBill(@PathVariable("orderBillId") int orderBillId){
		LOGGER.info("/order-bill/show/ URL is opened.");
		LOGGER.info("showOrderBill is initiated.");
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
		LOGGER.info("showOrderBill is terminated with http status :"+status);	
		return new ResponseEntity<Object> (result,status);
	}
	

	
	
}
