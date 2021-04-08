package com.cg.osm.entity;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


public class SweetOrderInput {

	private int sweetOrderId;
	private int userId;
	private List<Integer> listItems;
	private LocalDate createdDate;
	//@ElementCollection
	//private Map<Product, Long> groupedProducts;
	
	public SweetOrderInput() {
		
	}
	
	
	
	

	public SweetOrderInput(int sweetOrderId, int userId, List<Integer> listItems, LocalDate createdDate) {
		super();
		this.sweetOrderId = sweetOrderId;
		this.userId = userId;
		this.listItems = listItems;
		this.createdDate = createdDate;
	}





	public int getSweetOrderId() {
		return sweetOrderId;
	}

	public void setSweetOrderId(int sweetOrderId) {
		this.sweetOrderId = sweetOrderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getListItems() {
		return listItems;
	}

	public void setListItems(List<Integer> listItems) {
		this.listItems = listItems;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
	

	
	
	
	
