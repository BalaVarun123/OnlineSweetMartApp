package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	IProductRepository repo;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public void cancelProduct(int productId) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		repo.removeByProductId(productId);
	}

	@Override
	public List<Product> showAllProducts(int productId) {
		// TODO Auto-generated method stub
		return repo.findAllById(productId);
	}

	@Override
	public List<Product> showAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
