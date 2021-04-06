package com.cg.osm.entity;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.Cart;
@Entity
public class Customer {
		private String username;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
   	 @OneToMany
    private Set<SweetOrder> sweetOrders;
	 @OneToMany 
    private List<SweetItem> sweetItems;
    @OneToOne
	private Cart cart;


    public Customer() {
		super();
		this.userId = userId;
		this.username = username;
		this.sweetOrders = sweetOrders;
		this.sweetItems = sweetItems;
		this.cart = cart;
	}
	

	private Long userId;
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
		}}