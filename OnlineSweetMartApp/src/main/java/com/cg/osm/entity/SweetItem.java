package com.cg.osm.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;

@Entity
public class SweetItem {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    private int orderItemId;
	@OneToOne
    private Product product;
	@JsonIgnore
    @ManyToOne
    private SweetOrder sweetOrder;
    public SweetItem(Integer orderItemId,Product product,SweetOrder sweetOrder) {
    	super();
    	this.orderItemId=orderItemId;
    	this.product=product;
    	this.sweetOrder=sweetOrder;
    }
   public SweetItem() {
	   super();		
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public SweetOrder getSweetOrder() {
		return sweetOrder;
	}
	public void setSweetOrder(SweetOrder sweetOrder) {
		this.sweetOrder = sweetOrder;
	}
	@Override
	public String toString() {
		return "SweetItem [orderItemId=" + orderItemId + ", product=" + product + ", sweetOrder=" + sweetOrder + "]";
	}
	
	
}
