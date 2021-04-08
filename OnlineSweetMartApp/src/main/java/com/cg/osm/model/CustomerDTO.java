package com.cg.osm.model;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;

@Component
public class CustomerDTO {

	private long userId;
	private String username;
	private Set<SweetOrder> sweetOrders;
	private List<SweetItem> sweetItems;
	private Cart cart;
	
	public CustomerDTO() {
		super();
		
	}

	public CustomerDTO(Long userId, String username, Set<SweetOrder> sweetOrders, List<SweetItem> sweetItems,
			Cart cart) {
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

	public String DTO() {
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

	
	
	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "CustomerDTO [userId=" + userId + ", username=" + username + ", sweetOrders=" + sweetOrders
				+ ", sweetItems=" + sweetItems + ", cart=" + cart + "]";
	}

	
	
	
	
	
	
	
}
