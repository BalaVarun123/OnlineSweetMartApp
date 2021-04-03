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
		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		//return repo.save(product);
		try {
			return repo.save(product);
		} catch (Exception e) {
			throw new ProductNotFoundException("please enter valid product details.");
		}
	}

	@Override
	public void cancelProduct(int productid) throws ProductNotFoundException {
		//repo.deleteById(productId);
		try {
			repo.deleteById(productid);
		} catch (Exception e) {
			throw new ProductNotFoundException("please enter valid productid");
		}
	}

	@Override
	public Product showAllProducts(int productid) throws ProductNotFoundException{
		//return repo.findById(productId).orElse(null); 
		try {
			return repo.findById(productid).orElse(null); 
		} catch (Exception e) {
			throw new ProductNotFoundException("Cannot find the product with id" +productid);
		}
		
	}

	@Override
	public List<Product> showAllProducts() {
		
		return repo.findAll();
	}
	
}
	
