package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;

public interface CartService {
	public Cart addCart(Cart Cart);
	public Cart updateCart(Cart Cart) throws CartNotFoundException;
	public Cart cancelCart(int CartId) throws CartNotFoundException;
	public List<Cart> showAllCarts();
	public List<Cart> showAllCarts(int cartdId);

}
