package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
import com.cg.osm.service.IOrderBillService;
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
	
	@Autowired
	ISweetItemService sweetItemervice;

		  @BeforeAll
		  public static void init() {
			}
		  {
		  
	      sweetItem = new SweetItem();
		  category = new Category(); 
		  product= new Product(84, "GulabJamun", 800, "SugarFree", true, "Ddrive", category); 
		  sweetOrder= new SweetOrder(5,user,new ArrayList<SweetItem>(),LocalDate.now());
	     product.setProductid(productService.addProduct(product).getProductid());
	     sweetOrder.setSweetOrderId(sweetOrderService.addSweetOrder(sweetOrder).getSweetOrderId());
}
		  @AfterAll
		  public static void init1() {
			}
		 
	 {
		try {
			
			productService.cancelProduct(product.getProductid());
			sweetOrderService.cancelSweetOrder(sweetOrder.getSweetOrderId());
			sweetItemService.cancelSweetItem(sweetItem.getOrderItemId());
			sweetItemService.cancelSweetItem(sweetItem1.getOrderItemId());
		}
		
		   catch (CommonException e) {
			e.printStackTrace();
		}
	 }
			
			
	
	@Test
	void testAddSweetItem() {
		logger.info("Testing AddSweetItem()");

		
		product=new Product(84, "GulabJamun", 800, "SugarFree", true, "Ddrive", category);
		sweetOrder = new SweetOrder(5,user,new ArrayList<SweetItem>(),LocalDate.now());
		SweetItem sweetItem= new SweetItem(1,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	@Test
	void testAddSweetItem2() 
	{ 
		logger.info("Testing AddSweetItem2()");
		 sweetItem = new SweetItem(10,null,null);
	     assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	

	@Test
	void testUpdateSweetItem() throws SweetItemNotFoundException {
		logger.info("Testing UpdateSweetItem()");
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
		 logger.info("Testing testUpdateSweetItem2()");
	  SweetItem sweetItem = new SweetItem(5,product,sweetOrder);
	  assertThrows(CategoryNotFoundException.class, () -> sweetItemService.updateSweetItem(sweetItem)); 
	 
	  assertNull(sweetItemService.updateSweetItem(null)); 
	  }
	 
	 @Test
     void testUpdateSweetItem3() throws SweetItemNotFoundException
     {
		logger.info("Testing testUpdateSweetItem3()");
   	  assertNull(sweetItemService.updateSweetItem(null)); 
     }
     
    
     

	@Test
	void testCancelSweetItem() throws SweetItemNotFoundException {
		 logger.info("Testing testCancelSweetItem()");
		Product product=new Product(5, "Gajar Halva", 700, "PureGheeSweets", true, "Ddrive", category);
		SweetOrder sweetOrder = new SweetOrder(5,user,new ArrayList<SweetItem>(),LocalDate.now());
		SweetItem sweetItem = new SweetItem(64,product,sweetOrder);
		SweetItemDTO sweetItemDTO = sweetItemService.addSweetItem(sweetItem);
		assertNotNull(sweetItemDTO);
		int orderItemId = sweetItemDTO.getOrderItemId();
		logger.info("OrderItemId = " +orderItemId);
		assertNotNull(sweetItemService.cancelSweetItem(orderItemId));
	}
	 @Test
     void testCancelSweetItem2() throws SweetItemNotFoundException
     {
		 logger.info("Testing testCancelSweetItem2()");
		 sweetItem = new SweetItem (4,product,sweetOrder);
 		assertThrows(SweetItemNotFoundException.class, () -> sweetItemService.cancelSweetItem(65));
     }


	@Test
	void testShowAllSweetItems() {
		logger.info("Testing testShowAllSweetItems()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
		List<SweetItemDTO> sweetItemDTOList = sweetItemService.showAllSweetItems();
		assertNotNull(sweetItemDTOList);
	}


	@Test
	void testValidateSweetItemSweetOrder() {
		logger.info("Testing testValidateSweetItemSweetOrder()");
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
		logger.info("Testing testValidateOrderItemId()");
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
		logger.info("Testing testValidateSweetItemProduct()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertFalse(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
		Product product=new Product();
		sweetItem.setProduct(product);
		assertTrue(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
		sweetItem.setProduct(null);
		assertFalse(SweetItemServiceImp.validateSweetItemProduct(sweetItem));
	}

}
