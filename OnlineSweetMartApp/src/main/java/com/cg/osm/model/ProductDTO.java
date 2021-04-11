package com.cg.osm.model;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Category;

@Component
public class ProductDTO {

	private int productId;
	private String name;
	private double price;
	private String description;
	private boolean available;
	private String photopath;
	private Category category;

	public ProductDTO() {
		super();

	}

	public ProductDTO(int productId, String name, double price, String description, boolean available, String photopath,
			Category category) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.available = available;
		this.photopath = photopath;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
		return "ProductDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
				+ description + ", available=" + available + ", photopath=" + photopath + ", category=" + category
				+ "]";
	}

}