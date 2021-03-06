package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;

public interface IProductService{

	public ProductDTO addProduct(Product product);
	public ProductDTO updateProduct(int productid,Product product) throws ProductNotFoundException;
	public void cancelProduct(int productid) throws ProductNotFoundException;
	public ProductDTO showAllProducts(int productid) throws ProductNotFoundException;
	public List<ProductDTO> showAllProducts();
	
}
