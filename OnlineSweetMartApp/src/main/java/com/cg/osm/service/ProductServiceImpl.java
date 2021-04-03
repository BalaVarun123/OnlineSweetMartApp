package com.cg.osm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	IProductRepository repo;

	@Override
	public ProductDTO addProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public ProductDTO updateProduct(Product product) throws ProductNotFoundException {
		return repo.save(product);
	}

	@Override
	public void cancelProduct(int productId) throws ProductNotFoundException {
		Optional<Product> productList = this.IProductRepository.findById(productId);
		
		if(productList.isPresent()) {
			this.IProductRepository.delete(productList.get());
		}
		else {
			throw new ProductNotFoundException();
		}
	}

	@Override
	public List<ProductDTO> showAllProducts(int productid) {
		// TODO Auto-generated method stub
		return repo.findById(productid);
	}

	@Override
	public List<ProductDTO> showAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
