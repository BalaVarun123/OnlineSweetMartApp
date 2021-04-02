package com.cg.osm.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class SweetOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sweetOrderId;
	private User user;
	@OneToMany
	private List<SweetItem> listItems;
	private LocalDate createdDate;
	private Map<Product, Long> groupedProducts;
	
	public SweetOrder() {
		
	}
	
	public SweetOrder(Integer sweetOrderId, User user, List<SweetItem> listItems, LocalDate createdDate,  Map<Product, Long> groupedProducts)
	{
	super();

	this.sweetOrderId = sweetOrderId;
	this.user = user;
	this.listItems = listItems;
    this.createdDate = createdDate;
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
		   return "SweetOrder[sweetOrderId=" +sweetOrderId + ", user=" + user + ",  listItems="+ listItems +", createdDate=" + createdDate + ", groupedProducts=" + groupedProducts +"]";
	}
	
}
	

	
	
	
	
