package com.cg.osm.controller;

import java.util.List;
import java.util.Optional;

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

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.service.CartServiceImp;
import com.cg.osm.service.ICartService;

/*
 * Author      : UJJWAL SINGH A
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : This is Cart Controller
*/

@RestController
@RequestMapping("/api/osm")
public class CartController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICartService cartService;
	
	
	
	
	
	/************************************************************************************
	 * Method       : addCart 
	 * Description  : It is used to add Cart into cart Table
	 * @param cart  : Cart Object
	 * @returns     : It returns CartDTO Object with details
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception   : CartNotFoundException
	 * Created By   : UJJWAL SINGH A
     * Created Date : 04-04-2021 
     *
	 ************************************************************************************/

	@PostMapping(value = "/add-cart", consumes = "application/json")
	public ResponseEntity<Object> addCart(@RequestBody Cart cart) throws CartNotFoundException {
		logger.info("add-cart URL is opened");
		logger.info("addCart() is initiated");
		CartDTO cartDTO = cartService.addCart(cart);
		logger.info("addCart() has executed");
		return new ResponseEntity<Object>(cartDTO, HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
	/************************************************************************************
	 * Method         : updateCart 
	 * Description    : It is used to update cart into cart table
	 * @param cart    : Cart Object
	 * @returns cart  : It returns CartDTO Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CartNotFoundException
	 * Created By     : UJJWAL SINGH A
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
		
	
	@PutMapping("/update-cart")
	public ResponseEntity<Object> updateCart(@RequestBody Cart cart) throws CartNotFoundException {
		logger.info("update-cart URL is opened");
		logger.info("updateCart() is initiated");
		CartDTO cartDTO = cartService.updateCart(cart);
		logger.info("updateCart() has executed");
		return new ResponseEntity<Object>(cartDTO, HttpStatus.ACCEPTED);
	}
	
	
	
	

	/************************************************************************************
	 * Method          : cancelCart
	 * Description     : It is used to remove cart from cart table
	 * @param id       : integer cartId
	 * @returns cart   : It returns CartDTO Object with details
	 * @DeleteMapping  : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception      : CartNotFoundException
	 * Created By      : UJJWAL SINGH A
     * Created Date    : 04-04-2021 
	 * 
	 ************************************************************************************/

    @DeleteMapping("delete-cart/{id}")
	public ResponseEntity<Object> cancelCart(int cartId) throws CartNotFoundException {
		logger.info("cancel-Cart URL is opened");
		logger.info("cancelCart() is initiated");
		CartDTO cartDTO = cartService.cancelCart(cartId);
		logger.info("cancelCart() has executed");
		return new ResponseEntity<Object>(cartDTO, HttpStatus.ACCEPTED);

	}
	
	
	
	

	
	/************************************************************************************
	 * Method         : showCartById
	 * Description    : It is used to view tenant from cart table
	 * @param cart    : integer cartId
	 * @returns cart  : It returns CartDTO Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CartNotFoundException
	 * Created By     : UJJWAL SINGH A
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/show-cart-by-id/{cartId}")
	public CartDTO showCartById(@PathVariable("cartId") int cartId) throws CartNotFoundException {
		logger.info("view-cart URL is opened");
		logger.info("showCartById() is initiated");
		CartDTO cartDTO = cartService.showCartById(cartId);
		logger.info("showCartById() has executed");
		return cartDTO;
	}
	
	
	
	
	
	
	/************************************************************************************
	 * Method         : showAllCarts
	 * Description    : It is used to view all cart details present in cart table
	 * @returns cart  : It returns all List<CartDTO> Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By     : UJJWAL SINGH A
     * Created Date   : 04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/show-all-carts")
	public List<CartDTO> showAllCarts() {
		logger.info("showAllCarts URL is opened");
		logger.info("showAllCarts() is initiated");
		return cartService.showAllCarts();

	}

}
