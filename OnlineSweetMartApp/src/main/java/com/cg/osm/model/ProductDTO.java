package com.cg.osm.model;

import org.springframework.stereotype.Component;

@Component
public class ProductDTO {
	
	private int productid;
	private String name;
	private double price;
	private String description;
	private boolean available;
	private String photopath;
	
	public ProductDTO() {
		super();
		
	}
	public ProductDTO(int productid, String name, double price, String description, boolean available,
			String photopath) {
		super();
		this.productid = productid;
		this.name = name;
		this.price = price;
		this.description = description;
		this.available = available;
		this.photopath = photopath;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	


}