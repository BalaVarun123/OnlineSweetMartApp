package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import com.cg.osm.entity.Cart;
import com.cg.osm.model.CartDTO;

public class CartUtils {
	
	
	
	public static List<CartDTO> convertToCartDtoList(List<Cart> list) {
		List<CartDTO> dtolist = new ArrayList<CartDTO>();
		for (Cart cart : list)
			dtolist.add(convertToCartDto(cart));
		return dtolist;
	}
	
	

	public static CartDTO convertToCartDto(Cart cart) {
		CartDTO dto = new CartDTO();
		
		dto.setCartId(cart.getCartId());
		dto.setGrandTotal(cart.getGrandTotal());
		 dto.setProductCount(cart.getProductCount());
	    dto.setListProduct(cart.getListProduct());
	    dto.setTotal(cart.getTotal());
		return dto;
	}
	
	
	public static Cart convertToCart(CartDTO dto) {
		Cart cart = new Cart();
		
		cart.setCartId(dto.getCartId());
		cart.setGrandTotal(dto.getGrandTotal());
		cart.setProductCount(dto.getProductCount());
		cart.setListProduct(dto.getListProduct());
		cart.setTotal(dto.getTotal());
		return cart;
	}

	
	public static List<Cart> convertToCartList(List<CartDTO> dtolist) {
		List<Cart> list = new ArrayList<Cart>();
		for (CartDTO cartdto : dtolist)
			list.add(convertToCart(cartdto));
		return list;
	}
	

}
