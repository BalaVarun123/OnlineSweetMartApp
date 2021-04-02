package com.cg.osm.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Product;

@Component
public class CartDTO {

	private int cartId;
	private double grandTotal;
	private List<Product> listProduct;
	private int productCount;
	private double total;

	public CartDTO() {
		super();
		
	}

	public CartDTO(int cartId, double grandTotal, List<Product> listProduct, int productCount, double total) {
		super();
		this.cartId = cartId;
		this.grandTotal = grandTotal;
		this.listProduct = listProduct;
		this.productCount = productCount;
		this.total = total;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", grandTotal=" + grandTotal + ", listProduct=" + listProduct
				+ ", productCount=" + productCount + ", total=" + total + "]";
	}
	
	
	


}
