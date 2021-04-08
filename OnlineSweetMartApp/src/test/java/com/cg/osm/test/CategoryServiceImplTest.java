package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.service.ICategoryService;
@SpringBootTest
class CategoryServiceImplTest {
	
	@Autowired
	ICategoryService service;
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	Category category=null;
	
	@Test
	void testAddCategory() 
	{ 
		 category = new Category(1,"ChoclateCategory");
	     assertNotNull(service.addCategory(category));
	}
	
	
      @Test void testUpdateCategory() throws CategoryNotFoundException 
      {
	  category = new Category(2,"ChoclateCategory");
	  assertThrows(CategoryNotFoundException.class, () -> service.updateCategory(category)); 
	  CategoryDTO categoryDTO =service.addCategory(category); 
	  assertNotNull(categoryDTO);
	  category.setCategoryId(categoryDTO.getCategoryId());
	  category.setName("MilkSweet");
	  assertNotNull(service.updateCategory(category));
	  assertNull(service.updateCategory(null)); 
	  }
	
	@Test
	void testCancelCategory() throws CategoryNotFoundException {
		category= new Category (3,"ChoclateSweets");
		assertThrows(CategoryNotFoundException.class, () -> service.cancelCategory(3));
		CategoryDTO categoryDTO = service.addCategory(category);
		assertNotNull(categoryDTO);
		int id = categoryDTO.getCategoryId();
		LOGGER.info("category id = "+id);
		assertNotNull(service.cancelCategory(id));
	}

	@Test
	void testShowAllCategorys() 
	{
		category = new Category(4,"NutsSweet");
		assertNotNull(service.addCategory(category));
		List<CategoryDTO> categoryDTOList = service.showAllCategorys();
		assertNotNull(categoryDTOList);
		
	}
  @Test
  void testCalculateTotalCost() 
  { 
	  double cost=0;
	 assertEquals(cost, service.calculateTotalCost(1));
	
  }
	  
	 
  
  
  
	/*
	 * @Test void testValidateCategoryId() { fail("Not yet implemented"); }
	 * 
	 * @Test void testValidateName() { fail("Not yet implemented"); }
	 */
	 
	 

}