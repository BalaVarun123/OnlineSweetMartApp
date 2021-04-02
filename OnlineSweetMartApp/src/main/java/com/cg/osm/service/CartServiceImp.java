package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.repository.ICartRepository;
import com.cg.osm.util.CartUtils;

public class CartServiceImp implements ICartService {

	
	@Autowired
	ICartRepository repo;
	
	

	public CartDTO addCart(Cart cart) {
		
		Cart cart1 = repo.save(cart) ;
		 
		return CartUtils.convertToCartDto(cart1);
	}

	@Override
	public CartDTO updateCart(Cart cart) throws CartNotFoundException {
		
		 Cart  cart1 = repo.save(cart);
		 
		 return CartUtils.convertToCartDto(cart1);
	}

	@Override
	public void cancelCart(int cartId) throws CartNotFoundException {
		
		 repo.deleteById(cartId);;
	}

	@Override
	public List<CartDTO> showAllCarts() {
		
		List<Cart> list = repo.findAll();
		
		return CartUtils.convertToCartDtoList(list);
	}

	@Override
	public CartDTO showAllCarts(int cartdId) {
		
		 Cart cart1 = repo.findById(cartdId).orElse(null);
		 
		 return CartUtils.convertToCartDto(cart1);
	}
	
	
	/*
	 * public static boolean validateProduct(Product product) {
	 * 
	 * boolean flag = false;
	 * 
	 * if (product.getProductName().length() > 5 && product.getPrice() > 0) {
	 * 
	 * flag = true;
	 * 
	 * }
	 * 
	 * return flag; }
	 */
}
