package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
/*
 * Author      : KANAKASAI T
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : This is IProductService interface
*/
public interface IProductService {

	public ProductDTO addProduct(Product product);

	public ProductDTO updateProduct(Product product) throws ProductNotFoundException;

	public void cancelProduct(int productId) throws ProductNotFoundException;

	public ProductDTO showAllProducts(int productId) throws ProductNotFoundException;

	public List<ProductDTO> showAllProducts();

}
