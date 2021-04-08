package com.cg.osm.model;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Category;
/***********************************************************
 * @author              T Kanaka Sai
 * Description          It is a DTO(Data Transfer Object) class              
 * Version              1.0
 * created date         03-04-2021
 ***********************************************************/
@Component
public class ProductDTO {
	
	private int productid;
	private String name;
	private double price;
	private String description;
	private boolean available;
	private String photopath;
	private Category category;
	
	public ProductDTO() {
		super();
		
	}

	public ProductDTO(int productid, String name, double price, String description, boolean available, String photopath,
			Category category) {
		super();
		this.productid = productid;
		this.name = name;
		this.price = price;
		this.description = description;
		this.available = available;
		this.photopath = photopath;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductDTO [productid=" + productid + ", name=" + name + ", price=" + price + ", description="
				+ description + ", available=" + available + ", photopath=" + photopath + ", category=" + category
				+ "]";
	}
	
	


}