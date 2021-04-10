package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.service.ICartService;

@SpringBootTest
class CartServiceImpTest {

	@Autowired
	ICartService service;

	Cart cart = null;
	
	

	Category category = null;

	//@Disabled
	@Test
	void testAddCart() throws CartNotFoundException {

		category = new Category(3, "MilkDryFruitsSweets");

		List<Product> product = new ArrayList<Product>();

		product.add(new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive", category));

		cart = new Cart(1, product, 1, 800, 875);

		assertNotNull(service.addCart(cart));
		// assertNull(service.addCart(null));

	}

	//@Disabled
	@Test
	void testUpdateCart() throws CartNotFoundException {
		category = new Category(3, "MilkDryFruitsSweets");

		List<Product> product = new ArrayList<Product>();

		product.add(new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive", category));

		cart = new Cart(1, product, 1, 800, 875);

		assertNotNull(service.updateCart(cart));
	}

	//@Disabled
	@Test
	void testCancelCart() throws CartNotFoundException {

		try {
			service.cancelCart(1);

		} catch (CartNotFoundException exception) {

			assertEquals("No Cart found with given ID", exception.getMessage());

		}

	}
	
	@Test
	void testShowCart() throws CartNotFoundException {
		
		/*
		 * category = new Category(3, "MilkDryFruitsSweets");
		 * 
		 * List<Product> product = new ArrayList<Product>();
		 * 
		 * product.add(new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive",
		 * category));
		 * 
		 * cart = new Cart(1, product, 1, 800, 875);
		 */

		assertEquals(750, service.showCartById(9).getGrandTotal());

	}

	//@Disabled
	@Test
	void testShowAllCarts() {

		assertNotNull(service.showAllCarts());

	}

}
