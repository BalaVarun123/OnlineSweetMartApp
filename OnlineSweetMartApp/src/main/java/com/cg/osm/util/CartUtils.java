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

	
	/*
	 * public static Players convertToPlayers(PlayersDTO dto) { Players ply = new
	 * Players();
	 * 
	 * ply.setJerseyNo(dto.getJerseyNo()); ply.setpName(dto.getpName());
	 * ply.setRuns(dto.getRuns());
	 * 
	 * return ply;
	 * 
	 * }
	 */

}
