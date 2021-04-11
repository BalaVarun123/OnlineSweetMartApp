package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.CommonException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.service.CategoryServiceImpl;
import com.cg.osm.service.ICategoryService;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ISweetItemService;
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class CategoryServiceImplTest {
	
	@Autowired
    ICategoryService categoryService;
	@Autowired
    ISweetItemService sweetItemService;
	@Autowired
	IProductService productService;
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	Category category;
	Category category1;
	Product product;
	Product product1;
	Product product2;
	SweetItem sweetItem;
	SweetItem sweetItem1;
	SweetItem sweetItem2;
	
	  @BeforeAll
	  void beforeAll()
	  { 
		  category = new Category(); 
		  category1 = new Category();
		  product= new Product(); 
		  product1 = new Product();
		  product2 = new Product();
		  sweetItem = new SweetItem();
		  sweetItem1 = new SweetItem();
		  sweetItem2 = new SweetItem();
	     sweetItem.setOrderItemId(sweetItemService.addSweetItem(sweetItem).getOrderItemId());
	     product.setProductid(productService.addProduct(product).getProductid());
	   }
	 @AfterAll
	 void afterAll() 
	 {
		try {
			sweetItemService.cancelSweetItem(sweetItem.getOrderItemId());
			sweetItemService.cancelSweetItem(sweetItem1.getOrderItemId());
			sweetItemService.cancelSweetItem(sweetItem2.getOrderItemId());
			
			productService.cancelProduct(product.getProductid());
			productService.cancelProduct(product1.getProductid());
			productService.cancelProduct(product2.getProductid());
			
			categoryService.cancelCategory(category.getCategoryId());
			categoryService.cancelCategory(category1.getCategoryId());
			
			
			
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	// TEST CASE FOR ADD CATEGORY
	@Test
	void testAddCategory1() throws CategoryNotFoundException
	{ 
		LOGGER.info("Testing testAddCategory1()");
		 category = new Category(1,"ChoclateCategory");
		 assertNotNull(categoryService.addCategory(category));
		
	}
	
	
	//TEST CASES FOR UPDATE CATEGORY
	 @Test
     void testUpdateCategory1() throws CategoryNotFoundException
     {
	  LOGGER.info("Testing testUpdateCategory1()");
	  category = new Category(1,"ChoclateCategory");
   	  CategoryDTO categoryDTO =categoryService.addCategory(category); 
   	  assertNotNull(categoryDTO);
   	  category.setCategoryId(categoryDTO.getCategoryId());
   	  category.setName("MilkSweet");
   	  assertNotNull(categoryService.updateCategory(category));
     }
	
	
	 @Test
     void testUpdateCategory2() throws CategoryNotFoundException 
      {
		 LOGGER.info("Testing testUpdateCategory2()");
	  category = new Category(2,"ChoclateCategory");
	  assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(category)); 
	 
	  assertNull(categoryService.updateCategory(null)); 
	  }
    
     
      @Test
      void testUpdateCategory3() throws CategoryNotFoundException
      {
    	  LOGGER.info("Testing testUpdateCategory3()");
    	  assertNull(categoryService.updateCategory(null)); 
      }
      
	

        @Test
	    void testCancelCategory1() throws CategoryNotFoundException
        { 
          LOGGER.info("Testing testCancelCategory1()");
          category = new Category(1,"ChoclateCategory");
		  CategoryDTO categoryDTO = categoryService.addCategory(category);
		  assertNotNull(categoryDTO);
		  int id = categoryDTO.getCategoryId();
		  LOGGER.info("category id = "+id);
		  assertNotNull(categoryService.cancelCategory(id));
		  
	    }
        @Test
        void testCancelCategory2() throws CategoryNotFoundException
        {
        	 LOGGER.info("Testing testCancelCategory2()");
        	category= new Category (3,"ChoclateSweets");
    		assertThrows(CategoryNotFoundException.class, () -> categoryService.cancelCategory(-1));
        }

     @Test
	void testShowAllCategories() 
	{
    	LOGGER.info("Testing testShowAllCategories()");
		category = new Category(4,"NutsSweet");
		assertNotNull(categoryService.addCategory(category));
		List<CategoryDTO> categoryDTOList = categoryService.showAllCategories();
		assertNotNull(categoryDTOList);
		
	}
  @Test
  void testCalculateTotalCost() 
  { 
	  LOGGER.info("Testing testCalculateTotalCost()");
	 category = new Category(5,"Nuts");
	 category1 = new Category(6,"fruits");
	
	 product=new Product (1,"productOne",240,"description",true,"photo",category);
	 product1 = new Product(2,"productTwo",350,"descriptionTwo",true,"photoOne",category1);
	 product2 = new Product(3,"ProductThree",500,"descriptionThree",true,"photoTwo",category);
	 
	 sweetItem = new SweetItem(1,product,null);
	 sweetItem1 = new SweetItem(2,product1,null);
	 sweetItem2 = new SweetItem(3,product2,null);

	 category.setCategoryId(categoryService.addCategory(category).getCategoryId());
	 category1.setCategoryId(categoryService.addCategory(category1).getCategoryId());
	 product.setProductid(productService.addProduct(product).getProductid());
	 product1.setProductid(productService.addProduct(product1).getProductid());
	 product2.setProductid(productService.addProduct(product2).getProductid());
	 sweetItem.setOrderItemId(sweetItemService.addSweetItem(sweetItem).getOrderItemId());
	 sweetItem1.setOrderItemId(sweetItemService.addSweetItem(sweetItem1).getOrderItemId());
	 sweetItem2.setOrderItemId(sweetItemService.addSweetItem(sweetItem2).getOrderItemId());
	
	 assertEquals(740,categoryService.calculateTotalCost(category.getCategoryId()));
	 assertEquals(350,categoryService.calculateTotalCost(category1.getCategoryId()));
	 assertEquals(0,categoryService.calculateTotalCost(-1));
	 
  }
	  
  @Test
	void testShowCategory() 
  {
	    LOGGER.info("Testing testShowCategory()");
		Category category = new Category(5,"MilkSweets");
		CategoryDTO categoryDTO = categoryService.addCategory(category);
		assertNotNull(categoryDTO);
		try {
			CategoryDTO categoryDTO1 = categoryService.showCategory(categoryDTO.getCategoryId());
			assertEquals(categoryDTO.getCategoryId(),categoryDTO1.getCategoryId());
		} catch (CategoryNotFoundException e) {
			LOGGER.error("Not a proper category id");
		}
		
		
	} 
  
  
  
	
	  @Test
	  void testValidateCategoryId() 
	  { 
		  LOGGER.info("Testing testValidateCategoryId()");
		    category = new Category(6,"gheeSweets");
			assertNotNull(categoryService.addCategory(category));
			assertTrue(CategoryServiceImpl.validateCategoryId(category));
			category.setCategoryId(-5);
			assertFalse(CategoryServiceImpl.validateCategoryId(category)); 
      }
	  
	  @Test
	  void testValidateName() 
	  { 
		  LOGGER.info("Testing testValidateName()");
		 category=new Category(7,"fruitSweets");
		 assertNotNull(categoryService.addCategory(category));
		 assertEquals(true,CategoryServiceImpl.validateName(category));
	  }
	 
	 
	 

}
