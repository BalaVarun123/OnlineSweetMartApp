package com.cg.osm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.osm.error.AdminNotFoundException;

import com.cg.osm.error.CustomerNotFoundException;

import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.error.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {

	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid id, Admin Not Found.")
	@ResponseBody
	@ExceptionHandler({AdminNotFoundException.class})
	public void handleAdminNotFoundException(){
		LOGGER.error("Invalid id , Admin Not Found.");
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid orderBillId, OrderBill Not Found.")
	@ResponseBody
	@ExceptionHandler({OrderBillNotFoundException.class})
	public void  handleOrderBillNotFoundException(){
		LOGGER.error("Invalid orderBillId , OrderBill Not Found.");
	}
	

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid CustomerId, Customer Not Found.")
	@ResponseBody
	@ExceptionHandler({CustomerNotFoundException.class})
	public void  handleCustomerNotFoundException(){
		LOGGER.error("Invalid customerId , customer Not Found.");
	}


	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid categoryId, Category Not Found")
	@ResponseBody
	@ExceptionHandler({CategoryNotFoundException.class})
	public void handleCategoryNotFoundException()
	{
		LOGGER.error("Invalid categoryId, Category Not Found");
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid Productid, Product Not Found.")
	@ResponseBody
	@ExceptionHandler({ProductNotFoundException.class})
	public void handleProductNotFoundException(){
		LOGGER.error("Invalid productid , Product Not Found.");
	}

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Invalid userid, Product Not Found.")
	@ResponseBody
	@ExceptionHandler({UserNotFoundException.class})
	public void handleUserNotFoundException(){
		LOGGER.error("Invalid userId , User Not Found.");
	}
}

