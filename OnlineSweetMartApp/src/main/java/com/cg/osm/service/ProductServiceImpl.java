package com.cg.osm.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.repository.IProductRepository;
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
			throw new ProductNotFoundException("Cannot find the product with the given id.");
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
			throw new ProductNotFoundException("Cannot find the product with the given id");
		}
		
	}

	@Override
	public List<ProductDTO> showAllProducts() {
		
		return ProductUtils.convertToProductDtoList(repo.findAll());
	}
	//VALIDATION
	
	public static boolean validateProduct(Product product) throws ProductNotFoundException {
		boolean flag = false;
		if (product == null)
			throw new ProductNotFoundException("Cart details cannot be blank");
		else if (!(validateName(product.getName()) && validateProductPrice(product.getPrice())
				&& validatePhotoPath(product.getPhotopath())&& validateAvailable(product.isAvailable())))

			throw new ProductNotFoundException("Invalid Data");
		else
			flag = true;
		return flag;
	}
	public static boolean validateProductId(Product product) {
		boolean flag = true;
		Integer pid = product.getProductid();
		ProductServiceImpl service1 = new ProductServiceImpl();
		if (pid == null || !service1.repo.existsById(pid))
			flag = false;
		return flag;
	}
	
	public static boolean validateName(String name) throws ProductNotFoundException
	{
		boolean flag=false;
		if(name.matches("^[a-zA-Z]+$") && name.length()>3)
			flag=true;
		else 
			throw new ProductNotFoundException("Enter a valid product name");
		return flag; 		
	}
	
	public static boolean validateProductPrice(double price) {
		boolean flag = true;
		if ( price <= 0 || Double.isNaN(price)==true)
			flag = false;
		return flag;
	}
	
	public static boolean validateAvailable(boolean available) throws ProductNotFoundException
	{
		boolean flag=false;
		if(available =true)
			flag=true;
		else 
			throw new ProductNotFoundException("product is not available");
		return flag; 		
	}
	
	public static boolean validatePhotoPath(String photopath) throws ProductNotFoundException{
		boolean flag=false;
		File path=new File(photopath);
		if(path.isFile() && path!=null)
			flag=true;
		else
			throw new ProductNotFoundException("Cannot find the photo");
		return flag;
	}
	
}