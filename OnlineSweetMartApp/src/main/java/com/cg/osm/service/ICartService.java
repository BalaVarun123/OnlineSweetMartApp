package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;

public interface ICartService {
	public CartDTO addCart(Cart cart);
	public  CartDTO updateCart(Cart cart) throws CartNotFoundException;
	public CartDTO cancelCart(int cartId)throws CartNotFoundException;
	public CartDTO showCart(int cartId) throws CartNotFoundException ;
	public List<CartDTO> showAllCarts();
	

}
