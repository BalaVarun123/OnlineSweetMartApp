package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.SweetItemServiceImp;
import java.util.List;
import com.cg.osm.model.SweetItemDTO;


@SpringBootTest
class SweetItemServiceImpTests {

	@Autowired
	ISweetItemService sweetItemService;
	SweetItem sweetItem;
	Product product;
	SweetOrder sweetOrder;
	
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
		
			
	//TESTCASES FOR ADD-SWEETITEM
	@Test
	void testAddSweetItem() {
		LOGGER.info("Testing AddSweetItem()");

		
		SweetItem sweetItem= new SweetItem(1,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	@Test
	void testAddSweetItem2() 
	{ 
		LOGGER.info("Testing AddSweetItem2()");
		 sweetItem = new SweetItem(10,null,null);
	     assertNotNull(sweetItemService.addSweetItem(sweetItem));
	}
	
	//TESTCASES FOR UPDATE-SWEETITEM
	@Test
	void testUpdateSweetItem() throws SweetItemNotFoundException {
		LOGGER.info("Testing UpdateSweetItem()");
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
	  assertThrows(SweetItemNotFoundException.class, () -> sweetItemService.updateSweetItem(sweetItem)); 
	 
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
		 sweetItem = new SweetItem (4,product,sweetOrder);
 		assertThrows(SweetItemNotFoundException.class, () -> sweetItemService.cancelSweetItem(65));
     }

    //TESTCASES FOR SHOWALLSWEETITEMS
	@Test
	void testShowAllSweetItems() {
		LOGGER.info("Testing testShowAllSweetItems()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
		List<SweetItemDTO> sweetItemDTOList = sweetItemService.showAllSweetItems();
		assertNotNull(sweetItemDTOList);
	}
	//TESTCASES FOR SHOWSWEETITEMBYORDERITEMID
		 @Test
	void testShowSweetItem() {
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		SweetItemDTO sweetItemDTO = sweetItemService.addSweetItem(sweetItem);
		assertNotNull(sweetItemDTO);
		try {
		SweetItemDTO sweetItemDTO1 = sweetItemService.showSweetItem(sweetItemDTO.getOrderItemId());
		assertEquals(sweetItemDTO.getOrderItemId(),sweetItemDTO1.getOrderItemId());
		} catch (SweetItemNotFoundException e) {
		LOGGER.info("Invalid Id");}
	}
				

   //TESTCASES FOR VALIDATING SWEETORDER
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
	


    //TESTCASES FOR VALIDATING ORDERITEMID
	@Test
	void testValidateOrderItemId() {
		LOGGER.info("Testing testValidateOrderItemId()");
		SweetItem sweetItem = new SweetItem(3,product,sweetOrder);
		assertNotNull(sweetItemService.addSweetItem(sweetItem));
		assertTrue(SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem));
		sweetItem.setOrderItemId(88);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
		sweetItem.setOrderItemId(-7);
		assertFalse(SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem));
	}
	//TESTCASES FOR VALIDATING PRODUCT
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
