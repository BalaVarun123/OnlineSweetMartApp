package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Product;
import com.cg.osm.service.ICartService;
@SpringBootTest
class CartServiceImpTest {
	
	@Autowired
	ICartService cartService;
	
	Cart cart = null;
	
	List<Product> product = null;
	
	@Test
	void testAddCart() {
		
		product.add(new Product(101 , "Laptop" , 5000.0, "Acer" , true));
		product.add(new Product(102 , "Mobile" , 3000.0, "Redmi" , true));
		
		cart = new Cart( 11, product , 2,  8000,  8025);
	    
		assertNotNull(cartService.addCart(cart));
		
	}

	/*
	 * @Test void testUpdateCart() { fail("Not yet implemented"); }
	 * 
	 * @Test void testCancelCart() { fail("Not yet implemented"); }
	 * 
	 * @Test void testShowCart() { fail("Not yet implemented"); }
	 * 
	 * @Test void testShowAllCarts() { fail("Not yet implemented"); }
	 */
}
