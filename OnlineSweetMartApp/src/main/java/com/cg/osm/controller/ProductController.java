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

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/osm")
public class ProductController {
	
	@Autowired
	IProductService service;
	
	
	
	
	@PostMapping(value = "/product/add", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) throws ProductNotFoundException {
		Object result;
		HttpStatus status;
		if (!ProductServiceImpl.validateProductId(product)) {
			result = "Invalid productid.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!ProductServiceImpl.validateName(product)) {
			result = "Invalid product-name.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!ProductServiceImpl.validateProductPrice(product)) {
			result = "Invalid price.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addProduct(product);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	
	@PutMapping(value = "/product/update", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) throws ProductNotFoundException {
		Object result=service.updateProduct(product);
		HttpStatus status;
		if (!ProductServiceImpl.validateProductId(product)) {
			result = "Invalid productid.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!ProductServiceImpl.validateName(product)) {
			result = "Invalid product-name.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!ProductServiceImpl.validateProductPrice(product)) {
			result = "Invalid price.";
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			result = service.addProduct(product);
			status = HttpStatus.OK;
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	
	@DeleteMapping(value = "/product/cancel/{productid}", produces = "application/json")
	public void cancelProduct(@PathVariable("productid") int productid) throws ProductNotFoundException{
		 service.cancelProduct(productid);
	}
	
	@GetMapping(value = "/product/show-all", produces = "application/json")
	public List<ProductDTO> showAllProducts(){
		return service.showAllProducts();
	}
	
	@GetMapping(value = "/product/show/{product}", produces = "application/json")
	public ProductDTO showAllProductDTO(@PathVariable("productid") int productid) throws ProductNotFoundException{
		return service.showAllProducts(productid);
	}
	

	
	
}