package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{
	/*
	public ProductDTO addProduct(Product product);
	public ProductDTO updateProduct(Product product) throws ProductNotFoundException;
	public void cancelProduct(int productid) throws ProductNotFoundException;
	public ProductDTO showAllProducts(int productid);
	public List<ProductDTO> showAllProducts();
	*/
}
