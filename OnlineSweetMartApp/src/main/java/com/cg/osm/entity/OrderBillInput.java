package com.cg.osm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


public class OrderBillInput {

	private int orderBillId;
	private LocalDate createdDate;
	private float totalCost;
	
	
	private List<Integer> listSweetOrder;
	
	
	public OrderBillInput() {
		
	}


	public OrderBillInput(int orderBillId, LocalDate createdDate, float totalCost, List<Integer> listSweetOrder) {
		super();
		this.orderBillId = orderBillId;
		this.createdDate = createdDate;
		this.totalCost = totalCost;
		this.listSweetOrder = listSweetOrder;
	}


	public int getOrderBillId() {
		return orderBillId;
	}


	public void setOrderBillId(int orderBillId) {
		this.orderBillId = orderBillId;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public float getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	public List<Integer> getListSweetOrder() {
		return listSweetOrder;
	}


	public void setListSweetOrder(List<Integer> listSweetOrder) {
		this.listSweetOrder = listSweetOrder;
	}
	
	
	
	
	
	
}
