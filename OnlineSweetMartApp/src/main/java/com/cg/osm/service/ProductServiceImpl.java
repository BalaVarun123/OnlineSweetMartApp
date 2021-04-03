package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.Product;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.repository.IProductRepository;
import com.cg.osm.util.OrderBillUtils;
import com.cg.osm.util.ProductUtils;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	IProductRepository repo;

	@Override
	public ProductDTO addProduct(Product product) {
		Product product1=repo.save(product);
		return ProductUtils.convertToProductDto(product1);
	}

	@Override
	public ProductDTO updateProduct(Product product) throws ProductNotFoundException {
		
		Product productCheck = repo.findById(product.getProductid()).orElse(null);
		if ( productCheck== null) {
			throw new ProductNotFoundException("Invalid productid.");
		}
		else {
			return ProductUtils.convertToProductDto(repo.save(product));
		}
	}

	@Override
	public void cancelProduct(int productid) throws ProductNotFoundException {
		
		try {
			repo.deleteById(productid);
		} catch (Exception e) {
			throw new ProductNotFoundException("please enter valid productid");
		}
	}

	@Override
	public ProductDTO showAllProducts(int productid) throws ProductNotFoundException{
		
		try {
			return ProductUtils.convertToProductDto(repo.findById(productid).orElse(null)); 
		} catch (Exception e) {
			throw new ProductNotFoundException("Cannot find the product with id" +productid);
		}
		
	}

	@Override
	public List<ProductDTO> showAllProducts() {
		
		return ProductUtils.convertToProductDtoList(repo.findAll());
	}
	
	public static boolean validateProductId(Product product) {
		boolean flag = true;
		Integer pid = product.getProductid();
		ProductServiceImpl service1 = new ProductServiceImpl();
		if (pid == null|| pid < 0 || !service1.repo.existsById(pid))
			flag = false;
		return flag;
	}
	public static boolean validateProductPrice(Product product) {
		boolean flag = true;
		double price = product.getPrice();
		if ( price < 0 || Double.isNaN(price))
			flag = false;
		return flag;
	}
	
}
	
