package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.User;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.CommonException;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.ISweetOrderService;
import com.cg.osm.service.SweetItemServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.cg.osm.model.SweetItemDTO;


@SpringBootTest
class SweetItemServiceImpTest {
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());

	@Autowired
	ISweetItemService sweetItemService;
	@Autowired
	IProductService productService;
	@Autowired
	ISweetOrderService sweetOrderService;
	SweetItem sweetItem;
	SweetItem sweetItem1;
	Product product;
	Product product1;
	Product product2;
	SweetOrder sweetOrder;
	SweetOrder sweetOrder1;
	SweetOrder sweetOrder2;
	Category category;
	User user;
	
	  @BeforeAll
	  void beforeAll()
	  { 
		  sweetItem = new SweetItem();
		  category = new Category(); 
		  product= new Product(); 
		  product1 = new Product();
		  product2 = new Product();
		  sweetOrder= new SweetOrder();
		  sweetOrder1= new SweetOrder();
		  sweetOrder2= new SweetOrder();
	     product.setProductid(productService.addProduct(product).getProductid());
	     sweetOrder.setSweetOrderId(sweetOrderService.addSweetOrder(sweetOrder).getSweetOrderId());
	   }
	 @AfterAll
	 void afterAll() 
	 {
		try {
			
			productService.cancelProduct(product.getProductid());
			productService.cancelProduct(product1.getProductid());
			productService.cancelProduct(product2.getProductid());
			
			sweetOrderService.cancelSweetOrder(sweetOrder.getSweetOrderId());
			sweetOrderService.cancelSweetOrder(sweetOrder1.getSweetOrderId());
			sweetOrderService.cancelSweetOrder(sweetOrder2.getSweetOrderId());
			
			sweetItemService.cancelSweetItem(sweetItem.getOrderItemId());
			sweetItemService.cancelSweetItem(sweetItem1.getOrderItemId());
		}
		
		   catch (CommonException e) {
			e.printStackTrace();
		}
	 }
			
			
	
	@Test
	void testAddSweetItem() {
		LOGGER.info("Testing testAddSweetItem()");
		
		Product product=new Product(2, "GulabJamun", 800, "SugarFree", true, "Ddrive", category);
		SweetOrder sweetOrder = new SweetOrder(5,user,new ArrayList<SweetItem>(),LocalDate.now());
		SweetItem sweetItem= new SweetItem(1,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	@Test
	void testAddSweetItem2() 
	{ 
		LOGGER.info("Testing testAddSweetItem2()");
		 sweetItem = new SweetItem(10,null,null);
	     assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	

	@Test
	void testUpdateSweetItem() throws SweetItemNotFoundException {
		LOGGER.info("Testing testUpdateSweetItem()");
		SweetOrder sweetOrder = new SweetOrder(4,user,new ArrayList<SweetItem>(),LocalDate.now());
		SweetItem sweetItem = new SweetItem(5,product,sweetOrder);
		SweetItemDTO sweetItemDTO = sweetItemService.addSweetItem(sweetItem);
		assertNotNull(sweetItemDTO);
		sweetItem.setOrderItemId(sweetItemDTO.getOrderItemId());
		assertNotNull(sweetItemService.updateSweetItem(sweetItem));
		}
	
	 @Test
     void testUpdateSweetItem2() throws SweetItemNotFoundException 
      {
		 LOGGER.info("Testing testUpdateSweetItem2()");
	  SweetItem sweetItem = new SweetItem(5,product,sweetOrder);
	  assertThrows(CategoryNotFoundException.class, () -> sweetItemService.updateSweetItem(sweetItem)); 
	 
	  assertNull(sweetItemService.updateSweetItem(null)); 
	  }
	 
	 @Test
     void testUpdateSweetItem3() throws SweetItemNotFoundException
     {
		 LOGGER.info("Testing testUpdateSweetItem3()");
   	  assertNull(sweetItemService.updateSweetItem(null)); 
     }
     
    
     

	@Test
	void testCancelSweetItem() throws SweetItemNotFoundException {
		 LOGGER.info("Testing testCancelSweetItem()");
		Product product=new Product(5, "Gajar Halva", 700, "PureGheeSweets", true, "Ddrive", category);
		SweetItem sweetItem = new SweetItem(64,product,sweetOrder);
		SweetItemDTO sweetItemDTO = sweetItemService.addSweetItem(sweetItem);
		assertNotNull(sweetItemDTO);
		int orderItemId = sweetItemDTO.getOrderItemId();
		LOGGER.info("OrderItemId = " +orderItemId);
		assertNotNull(sweetItemService.cancelSweetItem(orderItemId));
	}
	 @Test
     void testCancelSweetItem2() throws SweetItemNotFoundException
     {
		 LOGGER.info("Testing testCancelSweetItem2()");
		 SweetItem sweetItem = new SweetItem (4,product,sweetOrder);
 		assertThrows(SweetItemNotFoundException.class, () -> sweetItemService.cancelSweetItem(65));
     }


	@Test
	void testShowAllSweetItems() {
		LOGGER.info("Testing testShowAllSweetItems()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
		List<SweetItemDTO> sweetItemDTOList = sweetItemService.showAllSweetItems();
		assertNotNull(sweetItemDTOList);
	}


	@Test
	void testValidateSweetItemSweetOrder() {
		LOGGER.info("Testing testValidateSweetItemSweetOrder()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
		SweetOrder sweetOrder = new SweetOrder();
		sweetItem.setSweetOrder(null);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
		sweetItem.setSweetOrder(sweetOrder);
		assertTrue(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
	}

	@Test
	void testValidateOrderItemId() {
		LOGGER.info("Testing testValidateOrderItemId()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
		assertTrue(SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem));
		sweetItem.setOrderItemId(null);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
		sweetItem.setOrderItemId(777);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
	}
	@Test
	void testValidateSweetItemProduct() {
		LOGGER.info("Testing testValidateSweetItemProduct()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertFalse(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
		Product product=new Product();
		sweetItem.setProduct(product);
		assertTrue(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
		sweetItem.setProduct(null);
		assertFalse(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
	}

}
