package com.cg.osm.model;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.User;

@Component
public class SweetOrderDTO {


	private Integer sweetOrderId;
	private User user;

	private List<SweetItem> listItems;
	private LocalDate createdDate;
	private Map<Product, Long> groupedProducts;
	
	public SweetOrderDTO() {
		
	}
	
	public SweetOrderDTO(Integer sweetOrderId, User user, List<SweetItem> listItems, LocalDate createdDate,  Map<Product, Long> groupedProducts)
	{
	super();

	this.sweetOrderId = sweetOrderId;
	this.user = user;
	this.listItems = listItems;
    this.createdDate = createdDate;
    if (groupedProducts == null)
    	this.groupedProducts = initiateGroupedProducts();
    else
    	this.groupedProducts = groupedProducts;
	}

	public Integer getSweetOrderId() {
		return sweetOrderId;
	}

	public void setSweetOrderId(Integer sweetOrderId) {
		this.sweetOrderId = sweetOrderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SweetItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<SweetItem> listItems) {
		this.listItems = listItems;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Map<Product, Long> getGroupedProducts() {
		return groupedProducts;
	}

	public void setGroupedProducts(Map<Product, Long> groupedProducts) {
		this.groupedProducts = groupedProducts;
	}
	
	@Override
	public String toString() {
		   return "SweetOrderDTO[sweetOrderId=" +sweetOrderId + ", user=" + user + ",  listItems="+ listItems +", createdDate=" + createdDate + ", groupedProducts=" + groupedProducts +"]";
	}
	public Map<Product,Long> initiateGroupedProducts() {
		
		Map<Product,Long> groupedProducts = new HashMap<Product, Long>();
		if (listItems != null) {
			Product product;
			for (SweetItem item : listItems) {
				product = item.getProduct();
				if (groupedProducts.containsKey(product)) {
					groupedProducts.put(product, groupedProducts.get(product) + 1);
				}else {
					groupedProducts.put(product, 1L);
				}
			}
		}
		return groupedProducts;
	}
}
	

	
	
	
	
