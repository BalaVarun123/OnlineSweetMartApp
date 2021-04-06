package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.service.ICategoryService;

class CategoryServiceImplTest {

	@Autowired
	ICategoryService service;
	
	@Test
	void testAddCategory()
	{
		Category category = new Category(1,"sweet1");
		assertNotNull(service.addCategory(category));
		assertNull(service.addCategory(null));
	}

	/*
	 * @Test void testUpdateCategory() { fail("Not yet implemented"); }
	 * 
	 * @Test void testCancelCategory() { fail("Not yet implemented"); }
	 * 
	 * @Test void testShowAllCategorys() { fail("Not yet implemented"); }
	 * 
	 * @Test void testCalculateTotalCost() { fail("Not yet implemented"); }
	 * 
	 * @Test void testValidateCategoryId() { fail("Not yet implemented"); }
	 * 
	 * @Test void testValidateName() { fail("Not yet implemented"); }
	 */

}
