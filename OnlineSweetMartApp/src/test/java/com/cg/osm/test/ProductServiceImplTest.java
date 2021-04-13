package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.repository.IProductRepository;
import com.cg.osm.service.ProductServiceImpl;
/*
 * Author      : KANAKASAI T
 * Version     : 1.0
 * Date        : 07-04-2021
 * Description : This is a testing class for Product
*/
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	@Mock
	IProductRepository repo;

	@InjectMocks
	ProductServiceImpl service;

	@Test
	public void addProductTest() throws ProductNotFoundException {

		boolean bool = false;
		Category category = new Category(101, "firstcat");
		Product product = new Product(1, "laddu", 450.02, "this is my first sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		when(repo.save(product)).thenReturn(product);

		ProductDTO product_values = service.addProduct(product);
		try {
			bool = service.validateProduct(product);
		} catch (Exception e) {
			throw new ProductNotFoundException("Invalid product details");
		}
		assertEquals(true, bool);
		assertEquals("laddu", product_values.getName());
		assertEquals(450.02, product_values.getPrice());
		assertEquals("this is my first sweet", product_values.getDescription());
		assertEquals(true, product_values.isAvailable());
		assertEquals("C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", product_values.getPhotopath());

	}

	@Test
	public void getAllProductTest() throws ProductNotFoundException {

		boolean bool1 = false;
		boolean bool2 = false;
		List<Product> product_values = new ArrayList<Product>();
		Category category = new Category(101, "firstcat");
		Product product1 = new Product(1, "laddu", 450.02, "this is my first sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		Product product2 = new Product(2, "burfi", 678.39, "this is my second sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		product_values.add(product1);
		product_values.add(product2);
		when(repo.findAll()).thenReturn(product_values);

		List<ProductDTO> productdto = new ArrayList<ProductDTO>();
		productdto = service.showAllProducts();
		try {
			bool1 = service.validateProduct(product1);
			bool2 = service.validateProduct(product1);
		} catch (Exception e) {
			throw new ProductNotFoundException("Invalid product details");
		}
		assertEquals(true, bool1);
		assertEquals(true, bool2);
		assertEquals(2, productdto.size());
		verify(repo, times(1)).findAll();
	}

	@Test
	public void getByIdTest() throws ProductNotFoundException {
		boolean bool = false;
		Category category = new Category(101, "firstcat");
		Product product = new Product(1, "laddu", 450.02, "this is my first sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		when(repo.findById(1)).thenReturn(Optional.of(product));
		ProductDTO product_values = service.showAllProducts(1);
		try {
			bool = service.validateProduct(product);
		} catch (Exception e) {
			throw new ProductNotFoundException("Invalid product details");
		}
		assertEquals(true, bool);
		assertEquals("laddu", product_values.getName());
		assertEquals(450.02, product_values.getPrice());
		assertEquals("this is my first sweet", product_values.getDescription());
		assertEquals(true, product_values.isAvailable());
		assertEquals("C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", product_values.getPhotopath());

	}

	@Test
	public void removeProduct() throws ProductNotFoundException {
		verify(repo, never()).deleteById(1);

	}

	@Test
	public void updateProductTest() throws ProductNotFoundException {
		boolean bool = false;
		Category category = new Category(101, "firstcat");
		Product product = new Product(1, "laddu", 450.02, "this is my first sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		Product updated_product = new Product(1, "burfi", 672.8, "this is my updated sweet", true,
				"C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", category);
		when(repo.findById(1)).thenReturn(Optional.of(updated_product));
		ProductDTO product_values = service.showAllProducts(1);
		try {
			bool = service.validateProduct(product);
		} catch (Exception e) {
			throw new ProductNotFoundException("Invalid product details");
		}
		assertEquals(true, bool);
		assertEquals("burfi", product_values.getName());
		assertEquals(672.8, product_values.getPrice());
		assertEquals("this is my updated sweet", product_values.getDescription());
		assertEquals(true, product_values.isAvailable());
		assertEquals("C:\\Users\\Win10\\Pictures\\Screenshots\\killua.png", product_values.getPhotopath());
	}

}
