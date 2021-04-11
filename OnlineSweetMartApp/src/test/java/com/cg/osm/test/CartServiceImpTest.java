package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.service.ICartService;

@SpringBootTest
class CartServiceImpTest {

	final static Logger logger = LoggerFactory.getLogger(CartServiceImpTest.class);

	@Autowired
	private ICartService service;

	Cart cart = null;

	Category category = null;

	@BeforeAll
	public static void init() {
		logger.info("Cart Testing Initiated");
	}

	// TEST CASES FOR ADD CART
	
	@Disabled
	@Test
	void testAddCart01() throws CartNotFoundException {
		logger.info("Testing testAddCart01()");

		category = new Category(2, "dryFruits");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(3, "jalebi", 900, "deepOilFried", true, "DDrive", category));
		cart = new Cart(4, product, 3, 900, 950);

		try {

			service.addCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals("Cart name cannot be empty", exception.getMessage());
		}

	}

	@Disabled
	@Test
	void testAddCart02() throws CartNotFoundException {
		logger.info("Testing testAddCart02()");

		category = new Category(3, "MilkDryFruitsSweets");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(2, "GulabJamun", 100, "SugarFree", true, "Ddrive", category));
		cart = new Cart(1, product, 1, 100, 150);

		try {

			service.addCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals(" Minimum purchase should be atleast for Rs 200 /-", exception.getMessage());
		}

	}

	@Disabled
	@Test
	void testAddCart03() throws CartNotFoundException {
		logger.info("Testing testAddCart03()");

		category = new Category(3, "MilkDryFruitsSweets");
		List<Product> product = new ArrayList<Product>();
		// product.add(new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive", category));
		cart = new Cart(1, product, 0, 0, 0);

		try {

			service.addCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals("Please add some Product to cart", exception.getMessage());
		}

	}
	

	//@Disabled
	@Test
	void testAddCart04() throws CartNotFoundException {
		logger.info("Testing testAddCart04()");

		category = new Category(3, "MilkDryFruitsSweets");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive", category));
		cart = new Cart(1, product, 1, 800, 875);

	
            assertEquals(800 , service.addCart(cart).getTotal());
		}

	

	
	
	
	// TEST CASES FOR UPDATE CART

	@Disabled
	@Test
	void testUpdateCart01() throws CartNotFoundException {
		logger.info("Testing testUpdateCart01()");

		category = new Category(3, "MilkSweets");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(2, "Rasmalai", 600, "FatFree", true, "Ddrive", category));
		cart = new Cart(6, product, 1, 600, 675);

		assertNotNull(service.updateCart(cart));
	}

	@Disabled
	@Test
	void testUpdateCart02() throws CartNotFoundException {
		logger.info("Testing testUpdateCart02()");

		category = new Category(3, "MilkSweets");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(2, "Rasmalai", 600, "FatFree", true, "Ddrive", category));
		cart = new Cart(12, product, 1, 600, 675);

		try {
			service.updateCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals("No Cart found with given ID", exception.getMessage());

		}
	}

	@Disabled
	@Test
	void testUpdateCart03() throws CartNotFoundException {
		logger.info("Testing testUpdateCart03()");

		category = new Category(3, "Fried");
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(2, "Jalebi", 150, "SugarBased", true, "Ddrive", category));
		cart = new Cart(9, product, 1, 150, 220);

		try {
			service.updateCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals(" Minimum purchase should be atleast for Rs 200 /-", exception.getMessage());

		}
	}

	@Disabled
	@Test
	void testUpdateCart04() throws CartNotFoundException {
		logger.info("Testing testUpdateCart04()");

		category = new Category(3, "Fried");
		List<Product> product = new ArrayList<Product>();
		// product.add(new Product(2, "Jalebi", 150, "SugarBased", true, "Ddrive", category));
		cart = new Cart(9, product, 0, 0, 0);

		try {
			service.updateCart(cart);
		} catch (CartNotFoundException exception) {

			assertEquals("Please add some Product to cart", exception.getMessage());

		}
	}
	
	
	

	// TEST CASES FOR CANCEL CART

	@Disabled
	@Test
	void testCancelCart01() throws CartNotFoundException {
		logger.info("Testing testUpdateCart01()");

		assertNotNull(service.cancelCart(9));

	}

	@Disabled
	@Test
	void testCancelCart02() throws CartNotFoundException {
		logger.info("Testing testUpdateCart02()");

		try {
			service.cancelCart(6);

		} catch (CartNotFoundException exception) {

			assertEquals("No Cart found with given ID", exception.getMessage());

		}

	}

	
	
	
	
	//TEST CASES FOR SHOW BY ID

	@Disabled
	@Test
	void testshowCartById01() throws CartNotFoundException {
		logger.info("Testing testshowCartById01()");

		assertEquals(875, service.showCartById(15).getGrandTotal());

	}

	@Disabled
	@Test
	void testshowCartById02() throws CartNotFoundException {
		logger.info("Testing testshowCartById02()");

		assertEquals(800, service.showCartById(15).getTotal());

	}

	@Disabled
	@Test
	void testshowCartById03() throws CartNotFoundException {
		logger.info("Testing testshowCartById03()");

		assertEquals(1, service.showCartById(15).getProductCount());

	}

	@Disabled
	@Test
	void testshowCartById04() throws CartNotFoundException {
		logger.info("Testing testshowCartById04()");
		try {
			service.showCartById(6);
		} catch (CartNotFoundException exception) {

			assertEquals("No Cart found with given ID", exception.getMessage());

		}

	}
	
	
	
	

	// TEST CASE FOR SHOW ALL CARTS
	
	
	@Disabled
	@Test
	void testShowAllCarts01() {
		logger.info("Testing testshowCartById01()");

		assertNotNull(service.showAllCarts());

	}

	@Disabled
	@Test
	void testShowAllCarts02() {
		logger.info("Testing testshowCartById02()");
		try {
			assertNull(service.showAllCarts());
		}

		catch (AssertionFailedError exception) {
			assertNotNull(service.showAllCarts());
		}

	}

	@AfterAll
	public static void end() {
		logger.info("Cart Testing Terminated");
	}

}
