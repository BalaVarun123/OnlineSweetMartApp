package com.cg.osm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.osm.error.AdminNotFoundException;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.CommonException;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.error.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {


	static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class); 
	
	@ExceptionHandler({CommonException.class,HttpClientErrorException.class})
	public ResponseEntity<String> exceptionHandler(Exception exception) {
		String errorMessage = exception.getMessage();
		LOGGER.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}