package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product,Integer>{

	/*
	 * public Product addProduct(Product product); public Product
	 * updateProduct(Product product) throws ProductNotFoundException; public
	 * Product cancelProduct(int productId) throws ProductNotFoundException; public
	 * List<Product> showAllProducts(int productId); public List<Product>
	 * showAllProducts();
	 */
	
}
