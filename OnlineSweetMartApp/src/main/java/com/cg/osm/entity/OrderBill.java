package com.cg.osm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class OrderBill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderBillId;
	private LocalDate createdDate;
	private float totalCost;
	
	@OneToMany
	//@JoinColumn(name = "order_bill_order_id")
	private List<SweetOrder> listSweetOrder;
	
	
	public OrderBill() {
		
	}
	public OrderBill(Integer orderBillId, LocalDate createdDate, float totalCost, List<SweetOrder> listSweetOrder) {
		super();
		this.orderBillId = orderBillId;
		this.createdDate = createdDate;
		this.totalCost = totalCost;
		this.listSweetOrder = listSweetOrder;
	}
	public Integer getOrderBillId() {
		return orderBillId;
	}
	public void setOrderBillId(Integer orderBillId) {
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
	public List<SweetOrder> getListSweetOrder() {
		return listSweetOrder;
	}
	public void setListSweetOrder(List<SweetOrder> listSweetOrder) {
		this.listSweetOrder = listSweetOrder;
	}
	@Override
	public String toString() {
		return "OrderBill [orderBillId=" + orderBillId + ", createdDate=" + createdDate + ", totalCost=" + totalCost
				+ ", listSweetOrder=" + listSweetOrder + "]";
	}
	
	
	
	
}
