package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.service.ICategoryService;

class CategoryServiceImplTest {
	
	@Autowired
	ICategoryService service;
	
	@Test
	void testAddCategory() throws CategoryNotFoundException
	{ 
		Category category = new Category(1,"sweet1");
	     assertNotNull(service.addCategory(category));
		  assertNull(service.addCategory(null));
		 
	}
	
	@Test
	void testUpdateCategory1() throws CategoryNotFoundException 
	{
		Category category = new Category(1,"sweet1");
		assertNotNull(service.updateCategory(category));
	}
 
	@Test
	void testUpdateCategory2() throws CategoryNotFoundException
	{
		Category category = new Category(2,"sweet1");
		try
		{
			service.updateCategory(category);
		}
		catch (CategoryNotFoundException exception)
		{
			assertEquals("No sweets/sweet category in the given ID",exception.getMessage());
		}
	}
	
	@Test
	void testUpdateCategory3() throws CategoryNotFoundException
	{
		Category category = new Category (1,null);
		try
		{
			service.updateCategory(category);
		}
		catch(CategoryNotFoundException exception)
		{
			assertEquals("Category name cannot be empty",exception.getMessage());
		}
	}

	@Test
	void testCancelCategory() throws CategoryNotFoundException
	{ 
		try {
			service.cancelCategory(2);
		} catch (CategoryNotFoundException exception) {
			assertEquals("No category found in the given Id", exception.getMessage());
		}
	}

	@Test 
	void testShowAllCategorys() 
	{ 
		assertNotNull(service.showAllCategorys());
	}

 
 
 
 
 
 
 
 /* 
	 * @Test void testCalculateTotalCost() { fail("Not yet implemented"); }
	 * 
	 * @Test void testValidateCategoryId() { fail("Not yet implemented"); }
	 * 
	 * @Test void testValidateName() { fail("Not yet implemented"); }
	 */
	 
	 

}