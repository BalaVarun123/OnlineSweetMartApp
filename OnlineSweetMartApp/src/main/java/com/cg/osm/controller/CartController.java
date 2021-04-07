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

@RestController
@RequestMapping("/api/osm")
public class CartController {

	@Autowired
	private ICartService cartService;

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// 1. ADD-CART
	@PostMapping(value = "/add-cart", consumes = "application/json")
	public ResponseEntity<CartDTO> addCart(@RequestBody Cart cart) throws CartNotFoundException {

		CartDTO cartdto = null;
		ResponseEntity<CartDTO> cartResponse = null;

		if (CartServiceImp.validateCart(cart)) {

			cartdto = cartService.addCart(cart);
			cartResponse = new ResponseEntity<CartDTO>(cartdto, HttpStatus.ACCEPTED);
			LOGGER.info("Cart added");

		} else

			throw new CartNotFoundException("Invalid Cart Details");

		return cartResponse;

	}

	// 2. UPDATE-CART
	@PutMapping("/update-cart")
	public ResponseEntity<Object> updateCart(@RequestBody Cart cart) throws CartNotFoundException {

		CartDTO cartdto = null;
		ResponseEntity<Object> cartResponse = null;

		if (CartServiceImp.validateCart(cart) && CartServiceImp.validateCartId(cart)) {

			cartdto = cartService.updateCart(cart);

			cartResponse = new ResponseEntity<Object>(cartdto, HttpStatus.ACCEPTED);

			LOGGER.info("Cart updated");

		} else {

			throw new CartNotFoundException("No Cart available in given ID");

		}
		return cartResponse;

	}

	// 3. DELETE - CART
	@DeleteMapping("delete-cart/{id}")
	public ResponseEntity<Object> cancelCart(int cartId) throws CartNotFoundException
	{
		CartDTO cartDTO = cartService.cancelCart(cartId);
		LOGGER.info("Cart deleted");
		return new ResponseEntity<Object>(cartDTO, HttpStatus.ACCEPTED);
		
	}

	// 4. SHOW-CART BY ID
	@GetMapping("/show-cart/{id}")
	public CartDTO showCart(@PathVariable("id") int cartId) throws CartNotFoundException {

		CartDTO cartdto = null;

		if (!(cartId <= 0)) {
			cartdto = cartService.showCart(cartId);
		}

		return cartdto;

	}

	// 5. SHOW ALL CARTS
	@GetMapping("/show-all-carts")
	public List<CartDTO> showAllCarts() {
		return cartService.showAllCarts();

	}

}
