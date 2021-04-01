package com.cg.osm.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;

public interface ProductService{

	public Product addProduct(Product product);
	public Product updateProduct(Product product) throws ProductNotFoundException;
	public Product cancelProduct(int productId) throws ProductNotFoundException;
	public List<Product> showAllProducts(int productId);
	public List<Product> showAllProducts();
	
}
