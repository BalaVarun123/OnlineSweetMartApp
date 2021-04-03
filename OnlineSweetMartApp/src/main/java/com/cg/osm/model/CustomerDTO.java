package com.cg.osm.model;
import java.util.List;
import java.util.Set;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;

public class CustomerDTO {
	
	public CustomerDTO(String username, Set<SweetOrder> sweetOrders, List<SweetItem> sweetItems, Cart cart,
			Long userId) {
		super();
		this.username = username;
		this.sweetOrders = sweetOrders;
		this.sweetItems = sweetItems;
		this.cart = cart;
		this.userId = userId;
	}
	private String username;
    private Set<SweetOrder> sweetOrders;
    private List<SweetItem> sweetItems;
	private Cart cart;

    private Long userId;
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
		return "CustomerDTO [username=" + username + ", sweetOrders=" + sweetOrders + ", sweetItems=" + sweetItems
				+ ", cart=" + cart + ", userId=" + userId + "]";
	}
}


