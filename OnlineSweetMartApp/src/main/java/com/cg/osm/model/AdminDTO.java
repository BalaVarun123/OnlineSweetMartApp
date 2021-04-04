package com.cg.osm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.User;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Product;


@Component
public class AdminDTO {

	
	private int id;
	private String password;
	@OneToOne
	private Customer customer;
	@OneToOne
	private User user;
	@OneToOne// 1..1 not mentioned in class diagram//I'm just adding for safety
	private SweetItem item;
	@OneToOne
	private Category category;
	@OneToOne
	private Cart cart;
	@OneToOne
	private Product product;
	
	
	public AdminDTO() {
		
	}
	public AdminDTO(int id, String password, Customer customer, User user, SweetItem item, Category category,
			Cart cart,Product product) {
		super();
		this.id = id;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return "AdminDTO [id=" + id + ", password=" + password + ", customer=" + customer + ", user=" + user + ", item="
				+ item + ", category=" + category + ", cart=" + cart + ", product=" + product + "]";
	}

	
	
}
