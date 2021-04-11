package com.cg.osm.test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.User;
import com.cg.osm.error.CommonException;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.error.UserNotFoundException;
import com.cg.osm.model.AdminDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.service.IProductService;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.ISweetOrderService;
import com.cg.osm.service.IUserService;
import com.cg.osm.service.ProductServiceImpl;
import com.cg.osm.service.SweetOrderServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SweetOrderServiceImplTest {

	@Autowired 
	ISweetOrderService orderService;
	@Autowired
	IUserService userService;
	@Autowired
	ISweetItemService sweetItemService;
	@Autowired
	IProductService productService;
	User user;
	SweetItem sweetItem;
	SweetOrder sweetOrder;
	List<SweetItem> sweetItemList; 
	User user1;
	SweetItem sweetItem1;
	SweetOrder sweetOrder1;
	List<SweetItem> sweetItemList1; 
	@BeforeAll
	public void beforeAll() {
		user1 = new User();
		user1.setUserId(userService.addUser(user1).getUserId());
		sweetItem1 = new SweetItem();
		sweetItem1.setOrderItemId(sweetItemService.addSweetItem(sweetItem1).getOrderItemId());
		sweetItemList1 = new ArrayList<SweetItem>();
		sweetItemList1.add(sweetItem1);
	}
	
	
	@AfterAll
	public void afterAll() throws CommonException {
		if (sweetOrder1 != null) {
			orderService.cancelSweetOrder(sweetOrder1.getSweetOrderId());
		}
		if (user1 != null) {
			userService.cancelUser(user1.getUserId());
		}
		if (sweetItem1!= null) {
			sweetItemService.cancelSweetItem(sweetItem1.getOrderItemId());
		}
	}
	
	
	
	@BeforeEach
	public void beforeEach() {
		user = new User();
		user.setUserId(userService.addUser(user).getUserId());
		sweetItem = new SweetItem();
		sweetItem.setOrderItemId(sweetItemService.addSweetItem(sweetItem).getOrderItemId());
		sweetItemList = new ArrayList<SweetItem>();
		sweetItemList.add(sweetItem);
		sweetOrder = new SweetOrder(1,user,sweetItemList,LocalDate.now().minusMonths(2));
		sweetOrder.setSweetOrderId(orderService.addSweetOrder(sweetOrder).getSweetOrderId());
	}
	
	@AfterEach
	public void afterEach() throws CommonException {
		if (sweetOrder!=null) {
			orderService.cancelSweetOrder(sweetOrder.getSweetOrderId());
		}
		if (user != null) {
			userService.cancelUser(user.getUserId());
		}
		if (sweetItem!= null) {
			sweetItemService.cancelSweetItem(sweetItem.getOrderItemId());
		}
	}
	
	
	@Test
	void testAddSweetOrder() {
		sweetOrder1 =  new SweetOrder(0,user1,sweetItemList1,LocalDate.now().minusMonths(3));
		SweetOrderDTO sweetOrderDTO = orderService.addSweetOrder(sweetOrder1);
		sweetOrder1.setSweetOrderId(sweetOrderDTO.getSweetOrderId());
		assertNotNull(sweetOrderDTO);
		assertNull(orderService.addSweetOrder(null));
	}

	@Test
	void testUpdateSweetOrder() throws SweetOrderNotFoundException {
		sweetOrder.setCreatedDate(LocalDate.now().minusWeeks(8));
		assertNotNull(orderService.updateSweetOrder(sweetOrder));
		assertNull(orderService.updateSweetOrder(null));
		assertThrows(SweetOrderNotFoundException.class , ()-> orderService.updateSweetOrder(new SweetOrder(-1,null,null,null)));
		
	}

	@Test
	void testCancelSweetOrder() throws SweetOrderNotFoundException {
		assertNotNull(orderService.cancelSweetOrder(sweetOrder.getSweetOrderId()));
		assertThrows(SweetOrderNotFoundException.class , () -> orderService.cancelSweetOrder(sweetOrder.getSweetOrderId()));
		sweetOrder = null;
	}

	@Test
	void testShowSweetOrder() throws SweetOrderNotFoundException {
		SweetOrderDTO sweetOrderDTO = orderService.showSweetOrder(sweetOrder.getSweetOrderId());
		assertNotNull(sweetOrderDTO);
		assertEquals(sweetOrder.getSweetOrderId(),sweetOrderDTO.getSweetOrderId());
		assertNotNull(orderService.cancelSweetOrder(sweetOrder.getSweetOrderId()));
		assertThrows(SweetOrderNotFoundException.class , () -> orderService.cancelSweetOrder(sweetOrder.getSweetOrderId()));
		sweetOrder = null;
	}

	@Test
	void testShowAllSweetOrders() {
		List<SweetOrderDTO> result = orderService.showAllSweetOrders();
		assertNotNull(result);
		assertTrue(result.size() > 0);
		int id = sweetOrder.getSweetOrderId();
		assertEquals(1L,result.stream().filter((SweetOrderDTO sweetOrderDTO) -> (sweetOrderDTO.getSweetOrderId() == id)).count());
	}

	@Transactional
	@Test
	void testCalculateTotalCost() throws CommonException {
		Product product1 = new Product();
		Product product2 = new Product();
		product1.setPrice(300);
		product2.setPrice(400);
		product1.setProductid(productService.addProduct(product1).getProductid());
		product2.setProductid(productService.addProduct(product2).getProductid());
		SweetItem item1 = new SweetItem();
		SweetItem item2 = new SweetItem();
		SweetItem item3 = new SweetItem();
		SweetItem item4 = new SweetItem();
		item1.setOrderItemId(sweetItemService.addSweetItem(item1).getOrderItemId());
		item2.setOrderItemId(sweetItemService.addSweetItem(item2).getOrderItemId());
		item3.setOrderItemId(sweetItemService.addSweetItem(item3).getOrderItemId());
		item4.setOrderItemId(sweetItemService.addSweetItem(item4).getOrderItemId());
		item1.setProduct(product1);
		item2.setProduct(product1);
		item3.setProduct(product2);
		item4.setProduct(product2);
		sweetItemList.clear();
		sweetItemList.add(item1);
		sweetItemList.add(item2);
		sweetItemList.add(item3);
		sweetItemList.add(item4);
		sweetOrder.setListItems(sweetItemList);
		orderService.updateSweetOrder(sweetOrder);
		
		
		assertEquals(1400,orderService.calculateTotalCost(sweetOrder.getSweetOrderId()));
		assertThrows(SweetOrderNotFoundException.class,() -> orderService.calculateTotalCost(-1));
		
		sweetItemList.clear();
		orderService.updateSweetOrder(sweetOrder);
		sweetItemService.cancelSweetItem(item1.getOrderItemId());
		sweetItemService.cancelSweetItem(item2.getOrderItemId());
		sweetItemService.cancelSweetItem(item3.getOrderItemId());
		sweetItemService.cancelSweetItem(item4.getOrderItemId());
		productService.cancelProduct(product1.getProductid());
		productService.cancelProduct(product2.getProductid());
	}

	@Test
	void testValidateSweetOrderId() {
		SweetOrder sweetOrder = new SweetOrder();
		sweetOrder.setSweetOrderId(21);
		assertTrue(SweetOrderServiceImpl.validateSweetOrderId(sweetOrder));
		sweetOrder.setSweetOrderId(-1);
		assertFalse(SweetOrderServiceImpl.validateSweetOrderId(sweetOrder));
		assertFalse(SweetOrderServiceImpl.validateSweetOrderId(null));
	}

	@Test
	void testValidateUser() {
		SweetOrder sweetOrder = new SweetOrder();
		sweetOrder.setUser(user);
		assertTrue(SweetOrderServiceImpl.validateUser(sweetOrder));
		sweetOrder.setUser(null);
		assertFalse(SweetOrderServiceImpl.validateUser(sweetOrder));
		assertFalse(SweetOrderServiceImpl.validateUser(null));
	}

	@Test
	void testValidateListItems() {
		SweetOrder sweetOrder = new SweetOrder();
		sweetOrder.setListItems(sweetItemList);
		assertTrue(SweetOrderServiceImpl.validateListItems(sweetOrder));
		sweetOrder.setListItems(null);
		assertFalse(SweetOrderServiceImpl.validateListItems(sweetOrder));
		assertFalse(SweetOrderServiceImpl.validateListItems(null));
	}

	@Test
	void testValidateCreatedDate() {
		SweetOrder sweetOrder = new SweetOrder();
		sweetOrder.setCreatedDate(LocalDate.now().minusDays(2));
		assertTrue(SweetOrderServiceImpl.validateCreatedDate(sweetOrder));
		sweetOrder.setCreatedDate(LocalDate.now().plusDays(2));
		assertFalse(SweetOrderServiceImpl.validateCreatedDate(sweetOrder));
		sweetOrder.setCreatedDate(null);
		assertFalse(SweetOrderServiceImpl.validateCreatedDate(sweetOrder));
		assertFalse(SweetOrderServiceImpl.validateCreatedDate(null));
	}

}
