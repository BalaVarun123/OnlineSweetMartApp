package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	IProductService repo;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return repo.updateProduct(product);
	}

	@Override
	public Product cancelProduct(int productId) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return repo.cancelProduct(productId);
	}

	@Override
	public List<Product> showAllProducts(int productId) {
		// TODO Auto-generated method stub
		return repo.showAllProducts(productId);
	}

	@Override
	public List<Product> showAllProducts() {
		// TODO Auto-generated method stub
		return repo.showAllProducts();
	}
}
