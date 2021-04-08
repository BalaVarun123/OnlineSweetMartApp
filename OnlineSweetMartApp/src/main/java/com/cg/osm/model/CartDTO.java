package com.cg.osm.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Product;

@Component
public class CartDTO {

	private int cartId;
	private List<Product> listProduct;
	private int productCount;
	private double total;
	private double grandTotal;

	public CartDTO() {
		super();

	}

	public CartDTO(int cartId, List<Product> listProduct, int productCount, double total, double grandTotal) {
		super();
		this.cartId = cartId;
		this.listProduct = listProduct;
		this.productCount = productCount;
		this.total = total;
		this.grandTotal = grandTotal;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public void cart(double total) {
		this.total = total;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", listProduct=" + listProduct + ", productCount=" + productCount
				+ ", total=" + total + ", grandTotal=" + grandTotal + "]";
	}

}
