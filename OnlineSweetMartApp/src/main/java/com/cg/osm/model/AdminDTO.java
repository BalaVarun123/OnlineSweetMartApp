package com.cg.osm.model;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.User;


@Component
public class AdminDTO {

	
	private int id;
	private Customer customer;
	private User user;
	private SweetItem item;
	private Category category;
	private Cart cart;
	private Product product;
	
	
	public AdminDTO() {
		
	}
	public AdminDTO(int id,  Customer customer, User user, SweetItem item, Category category,
			Cart cart,Product product) {
		super();
		this.id = id;
		this.customer = customer;
		this.user = user;
		this.item = item;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SweetItem getItem() {
		return item;
	}
	public void setItem(SweetItem item) {
		this.item = item;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "AdminDTO [id=" + id + ", customer=" + customer + ", user=" + user + ", item="
				+ item + ", category=" + category + ", cart=" + cart + ", product=" + product + "]";
	}

	
	
}
