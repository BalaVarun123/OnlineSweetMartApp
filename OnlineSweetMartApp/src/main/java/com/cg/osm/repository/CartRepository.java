package com.cg.osm.repository;


import java.util.List;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;

public interface CartRepository {

	public Cart addCart(Cart Cart);
	public Cart updateCart(Cart Cart) throws CartNotFoundException;
	public Cart cancelCart(int CartId) throws CartNotFoundException;
	public List<Cart> showAllCarts();
	public List<Cart> showAllCarts(int cartdId);
}
