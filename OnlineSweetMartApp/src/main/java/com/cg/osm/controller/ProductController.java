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

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ProductServiceImpl;

/*
 * Author      : KANAKASAI T
 * Version     : 1.0
 * Date        : 05-04-2021
 * Description : Implementation for product controller
*/
@RestController
@RequestMapping("/api/osm")
public class ProductController {

	@Autowired
	IProductService service;

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	/*
	 * Method: addproduct
	 * Description: It is used to add product details into product table
	 * @param : Product Object
	 * @returns : It returns ProductDTO Object with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: ProductNotFoundException
	 */
	@PostMapping(value = "/product/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product) throws ProductNotFoundException {
		ResponseEntity<ProductDTO> productResponse;
		if (ProductServiceImpl.validateProduct(product)) {

			ProductDTO result = service.addProduct(product);
			productResponse = new ResponseEntity<ProductDTO>(result, HttpStatus.ACCEPTED);
			LOGGER.info("Product Added");

		} else

			throw new ProductNotFoundException("Please enter valid product details");

		return productResponse;

	}
	/*
	 * Method: updateproduct
	 * Description: It is used to update product details in product table
	 * @param : Product Object
	 * @returns : It returns ProductDTO Object with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: ProductNotFoundException
	 */
	@PutMapping(value = "/product/update/{productId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product,
			@PathVariable("productId") int productId) throws ProductNotFoundException {
		ResponseEntity<ProductDTO> productResponse;
		if (ProductServiceImpl.validateProduct(product) && ProductServiceImpl.validateProductId(productId)) {

			ProductDTO result = service.updateProduct(productId, product);
			productResponse = new ResponseEntity<ProductDTO>(result, HttpStatus.ACCEPTED);
			LOGGER.info("Product Updated");

		} else {

			throw new ProductNotFoundException("Please enter valid product details");
		}
		return productResponse;
	}
	/*
	 * Method: updateproduct
	 * Description: It is used to update product details in product table
	 * @param : int productId
	 * @DeleteMapping:  It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: ProductNotFoundException
	 */
	@DeleteMapping(value = "/product/cancel/{productId}", produces = "application/json")
	public void cancelProduct(@PathVariable("productId") int productId) throws ProductNotFoundException {
		service.cancelProduct(productId);
		LOGGER.info("Product Deleted");

	}
	/*
	 * Method: showAllproducts
	 * Description: It is used to get all the product details in product table
	 * @GetMapping:  It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 */
	@GetMapping(value = "/product/show-all", produces = "application/json")
	public List<ProductDTO> showAllProducts() {
		return service.showAllProducts();

	}
	/*
	 * Method: showAllProducts using productId
	 * Description: It is used to get the product details using productId
	 * @param : int productId
	 * @DeleteMapping:  It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: ProductNotFoundException
	 */
	@GetMapping(value = "/product/show-by-id/{productId}", produces = "application/json")
	public ProductDTO showAllProductDTO(@PathVariable("productId") int productId) throws ProductNotFoundException {
		return service.showAllProducts(productId);

	}

}