package com.cg.osm.entity;

import java.util.List;


public class CustomerInput {

	private long userId;
	private String username;	
	private List<Integer> sweetOrders;
	private List<Integer> sweetItems;
	private int cartId;

	public CustomerInput() {
		super();

	}

	public CustomerInput(long userId, String username, List<Integer> sweetOrders, List<Integer> sweetItems,
			int cartId) {
		super();
		this.userId = userId;
		this.username = username;
		this.sweetOrders = sweetOrders;
		this.sweetItems = sweetItems;
		this.cartId = cartId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Integer> getSweetOrders() {
		return sweetOrders;
	}

	public void setSweetOrders(List<Integer> sweetOrders) {
		this.sweetOrders = sweetOrders;
	}

	public List<Integer> getSweetItems() {
		return sweetItems;
	}

	public void setSweetItems(List<Integer> sweetItems) {
		this.sweetItems = sweetItems;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	
}