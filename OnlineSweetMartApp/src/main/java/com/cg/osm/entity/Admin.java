package com.cg.osm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity 
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
	
	
	public Admin() {
		
	}
	public Admin(int id, Customer customer, User user, SweetItem item, Category category,
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
		return "Admin [id=" + id +  ", customer=" + customer + ", user=" + user + ", item="
				+ item + ", category=" + category + ", cart=" + cart + ", product=" + product + "]";
	}

	
	
}
