package com.cg.osm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.osm.error.CommonException;

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