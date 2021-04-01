package com.cg.osm.model;

import java.time.LocalDate;
import java.util.List;


import com.cg.osm.entity.SweetOrder;

public class OrderBillDTO {
	private Integer orderBillId;
	private LocalDate createdDate;
	private float totalCost;
	private List<SweetOrder> listSweetOrder;
	
	
	
	
	
	public OrderBillDTO(Integer orderBillId, LocalDate createdDate, float totalCost, List<SweetOrder> listSweetOrder) {
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
		return "OrderBillDTO [orderBillId=" + orderBillId + ", createdDate=" + createdDate + ", totalCost=" + totalCost
				+ ", listSweetOrder=" + listSweetOrder + "]";
	}

	
}
