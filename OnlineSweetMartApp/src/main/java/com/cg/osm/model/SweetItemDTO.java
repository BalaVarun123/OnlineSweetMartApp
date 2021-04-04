package com.cg.osm.model;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.*;
@Component
public class SweetItemDTO  {
	    private Integer orderItemId;
	    private Product product;
	    private SweetOrder sweetOrder;
	    public SweetItemDTO()
	    {
	    	super();
	    }
	    public SweetItemDTO(Integer orderItemId,Product product,SweetOrder sweetOrder) {
	    	super();
	    	this.orderItemId=orderItemId;
	    	this.product=product;
	    	this.sweetOrder=sweetOrder;
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
			return "SweetItemDTO [orderItemId=" + orderItemId + ", product=" + product + ", sweetOrder=" + sweetOrder + "]";
		}
		
		
	}
