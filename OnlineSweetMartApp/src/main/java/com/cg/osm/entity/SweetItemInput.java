package com.cg.osm.entity;



public class SweetItemInput {
    private int orderItemId;

    private int productId;

    private int sweetOrderId;

    
    public SweetItemInput(){
    	
    }
    
    
    
	public SweetItemInput(int orderItemId, int productId, int sweetOrderId) {
		super();
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.sweetOrderId = sweetOrderId;
	}



	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSweetOrderId() {
		return sweetOrderId;
	}

	public void setSweetOrderId(int sweetOrderId) {
		this.sweetOrderId = sweetOrderId;
	}

	
   
	
	
}
