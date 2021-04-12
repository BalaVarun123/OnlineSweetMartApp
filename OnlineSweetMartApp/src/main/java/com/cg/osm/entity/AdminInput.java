package com.cg.osm.entity;




public class AdminInput {


	private int id;

	private long customerId;

	private long userId;

	private int itemId;

	private int category;

	private int cart;

	private int product;
	
	
	public AdminInput() {
		
	}
	
	public AdminInput(int id, long customerId, long userId, int itemId, int category, int cart, int product) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.userId = userId;
		this.itemId = itemId;
		this.category = category;
		this.cart = cart;
		this.product = product;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public int getCart() {
		return cart;
	}


	public void setCart(int cart) {
		this.cart = cart;
	}


	public int getProduct() {
		return product;
	}


	public void setProduct(int product) {
		this.product = product;
	}
	
	

	
	
}
