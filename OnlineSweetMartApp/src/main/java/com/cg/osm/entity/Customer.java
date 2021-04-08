package com.cg.osm.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String username;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<SweetOrder> sweetOrders;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SweetItem> sweetItems;
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	public Customer() {
		super();

	}

	public Customer(Long userId, String username, Set<SweetOrder> sweetOrders, List<SweetItem> sweetItems, Cart cart) {
		super();
		this.userId = userId;
		this.username = username;
		this.sweetOrders = sweetOrders;
		this.sweetItems = sweetItems;
		this.cart = cart;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<SweetOrder> getSweetOrders() {
		return sweetOrders;
	}

	public void setSweetOrders(Set<SweetOrder> sweetOrders) {
		this.sweetOrders = sweetOrders;
	}

	public List<SweetItem> getSweetItems() {
		return sweetItems;
	}

	public void setSweetItems(List<SweetItem> sweetItems) {
		this.sweetItems = sweetItems;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", username=" + username + ", sweetOrders=" + sweetOrders
				+ ", sweetItems=" + sweetItems + ", cart=" + cart + "]";
	}

}