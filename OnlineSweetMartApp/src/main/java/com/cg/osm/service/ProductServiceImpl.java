package com.cg.osm.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.repository.IProductRepository;
import com.cg.osm.util.ProductUtils;
/*
 * Author      : KANAKASAI T
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : Implementation for IProductService
*/
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository repo;

	/*
	 * Description : To add product to the database 
	 * Input Params : Product details
	 * Return Values : ProductDTO object of the product been added
	 */
	@Override
	public ProductDTO addProduct(Product product) {
		Product product1 = repo.save(product);
		return ProductUtils.convertToProductDto(product1);
	}

	/*
	 * Description : To update product on the database 
	 * Input Params : Product details to be updated 
	 * Return Values : ProductDTO object of the product been updated 
	 * Exception : ProductNotFoundException -it is raised when productid doesn't exist
	 */
	@Override
	public ProductDTO updateProduct(int productId, Product product) throws ProductNotFoundException {

		Product productCheck = repo.findById(productId).orElse(null);
		if (productCheck == null) {
			throw new ProductNotFoundException("Cannot find the product with the given id.");
		} else {
			productCheck.setName(product.getName());
			productCheck.setDescription(product.getDescription());
			productCheck.setPrice(product.getPrice());
			productCheck.setAvailable(product.isAvailable());
			productCheck.setPhotopath(product.getPhotopath());
			return ProductUtils.convertToProductDto(repo.save(productCheck));
		}
	}

	/*
	 * Description : To cancel product on the database 
	 * Input Params : Productid to be deleted 
	 * Exception : ProductNotFoundException -it is raised when productid doesn't exist
	 */
	@Override
	public void cancelProduct(int productId) throws ProductNotFoundException {

		try {
			repo.deleteById(productId);
		} catch (Exception e) {
			throw new ProductNotFoundException("please enter valid productid");
		}
	}

	/*
	 * Description : To show product in the database for the given id 
	 * Input Params : productid of the product to be displayed
	 *  Return Values : ProductDTO object of the product 
	 * Exception : ProductNotFoundException -it is raised when productid doesn't exist
	 */
	@Override
	public ProductDTO showAllProducts(int productId) throws ProductNotFoundException {

		try {
			return ProductUtils.convertToProductDto(repo.findById(productId).orElse(null));
		} catch (Exception e) {
			throw new ProductNotFoundException("Cannot find the product with the given id");
		}

	}

	/*
	 * Description : To show products in the database
	 *  Return Values : ProductDTO object of all the products 
	 *  Exception : ProductNotFoundException -it is raised when productid doesn't exist
	 */
	@Override
	public List<ProductDTO> showAllProducts() {

		return ProductUtils.convertToProductDtoList(repo.findAll());
	}
	/*
	 * Description : To validate product details 
	 * Return Values : boolean 
	 * Exception : ProductNotFoundException -it is raised when product details are incorrect
	 */
	
	public static boolean validateProduct(Product product) throws ProductNotFoundException {
		boolean flag = false;
		if (product == null)
			throw new ProductNotFoundException("Product details cannot be blank");
		else if (!(validateName(product.getName()) && validateProductPrice(product.getPrice())
				&& validatePhotoPath(product.getPhotopath()) && validateAvailable(product.isAvailable())))

			throw new ProductNotFoundException("Invalid Data");
		else
			flag = true;
		return flag;
	}

	/*
	 * Description : To validate product id 
	 * Return Values : boolean 
	 */
	public static boolean validateProductId(int productId) {
		boolean flag = true;
		Integer pid = productId;
		if (pid == null)
			flag = false;
		return flag;
	}

	/*
	 * Description : To validate product name
	 * Return Values : boolean 
	 * Exception : ProductNotFoundException -it is raised when product name is incorrect
	 */
	public static boolean validateName(String name) throws ProductNotFoundException {
		boolean flag = false;
		if (name.matches("^[a-zA-Z ]+$") && name.length() > 3 && name.length() < 30)
			flag = true;
		else
			throw new ProductNotFoundException("Enter a valid product name");
		return flag;
	}

	/*
	 * Description : To validate product price 
	 * Return Values : boolean 
	 * Exception : ProductNotFoundException -it is raised when product price is incorrect
	 */
	public static boolean validateProductPrice(double price) throws ProductNotFoundException {
		boolean flag = true;
		if (price <= 0 || Double.isNaN(price))
			throw new ProductNotFoundException("Enter a valid product price");
		return flag;
	}

	/*
	 * Description : To validate product availability 
	 * Return Values : boolean 
	 * Exception : ProductNotFoundException -it is raised when product availability is wrong
	 */
	public static boolean validateAvailable(boolean available) throws ProductNotFoundException {
		boolean flag = false;
		if (available)
			flag = true;
		else
			throw new ProductNotFoundException("product is not available");
		return flag;
	}

	/*
	 * Description : To validate product photopath
	 * Return Values : boolean 
	 * Exception : ProductNotFoundException -it is raised when product photopath is null
	 */
	public static boolean validatePhotoPath(String photopath) throws ProductNotFoundException {
		boolean flag = true;
		Path path = Paths.get(photopath);
		if (path == null)
			throw new ProductNotFoundException("Cannot find the photo");
		
		return flag;
	}

}