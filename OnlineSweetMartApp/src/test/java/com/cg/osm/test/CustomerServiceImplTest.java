package com.cg.osm.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Category;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.entity.User;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.service.ICustomerService;
import com.cg.osm.service.CustomerServiceImp;


@SpringBootTest
public class CustomerServiceImplTest {
	
	final static  Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImp.class);
	
	@Autowired
	ICustomerService service;
	Customer customer = null;
	Set<SweetOrder> setsweetOrder = new HashSet<SweetOrder>();
    List<SweetItem> listsweetItem = new ArrayList<SweetItem>();;
	User user=null;
	Product product = null;
	Category category = null;
	Cart cart = null;
	@BeforeAll
	public static void init() {
		LOGGER.info("Testing Initialized");
	}
	
	// TEST CASES FOR ADD CUSTOMER
	
	@Test
	void testAddustomer() throws CustomerNotFoundException , SweetItemNotFoundException {
	     Customer customer = new Customer(1,"Jeevee_1",new HashSet<SweetOrder>(), new ArrayList<SweetItem>(), cart);
		assertNotNull(service.addCustomer(customer));
		assertNull(service.addCustomer(null));
	}
	
	// TEST CASES FOR UPDATE CUSTOMER
	@Test
	void testUpdateCustomer() throws CustomerNotFoundException {
		Customer customer = new Customer(2,"Bala_2",new HashSet<SweetOrder>(), new ArrayList<SweetItem>(), cart);
		assertThrows(CustomerNotFoundException.class,() -> service.updateCustomer(customer));
		CustomerDTO customerDTO = service.updateCustomer(customer);
		assertNotNull(customerDTO);
		customer.setUserId(customerDTO.getUserId());
		customer.setUsername(customerDTO.getUsername());
		customer.setSweetOrders(customerDTO.getSweetOrders());
		customer.setSweetItems(customerDTO.getSweetItems());
		assertNotNull(service.updateCustomer(customer));
		assertNotNull(service.updateCustomer(customer));
	}
    
	// TEST CASES FOR CANCEL CUSTOMER
	
	@Test
	void testCancelCustomer() throws CustomerNotFoundException {
		Customer customer = new Customer(3,"Raksha_3",new HashSet<SweetOrder>(), new ArrayList<SweetItem>(), cart);
		assertThrows(CustomerNotFoundException.class,() -> service.updateCustomer(customer));
		CustomerDTO customerDTO = service.cancelCustomer(3);
		assertNotNull(customerDTO);
		Long id = customerDTO.getUserId();
		LOGGER.info("User Id = "+id);
		assertNotNull(service.cancelCustomer(id));
		assertEquals(0,service.cancelCustomer(id));
	}
	
   // TEST CASES FOR DISPLAYING CUSTOMER
	
	@Test
	void testShowAllCustomers() {
		Customer customer = new Customer(3,"Raksha_3",new HashSet<SweetOrder>(), new ArrayList<SweetItem>(), cart);
		assertNotNull(service.addCustomer(customer));
		List<CustomerDTO> customerDTOList = service.showAllCustomers();
		assertNotNull(customerDTOList);
	}
	
    // TEST CASES FOR DISPLAYING CUSTOMER USING USER/CUSTOMER ID
	
	@Test
	void testShowAllCustomersInt() throws CustomerNotFoundException {
		
		Customer customer = new Customer(4, "Ujjwal_4", new HashSet<SweetOrder>(),new ArrayList<SweetItem>(), cart);
		CustomerDTO customerDTO = service.addCustomer(customer);
		assertNotNull(customerDTO);
		List<CustomerDTO> customerDTOList =service.showAllCustomers();
		assertNotNull(customerDTOList);
		assertEquals(1,customerDTOList.size());
		assertTrue(customerDTOList.get(0).getUserId().equals(customerDTO.getUserId()));
		
	}
	
	// TEST CASES FOR USERID OF CUSTOMER
	
	@Test
	void testValidateCustomerUserId() throws CustomerNotFoundException {
		User userid1 = new User();
		long userid2 = userid1.setUserId(2);
		Customer customer = new Customer(5,"Annie_5", new HashSet<SweetOrder>(),new ArrayList<SweetItem>(), cart);
		assertTrue(CustomerServiceImp.validateCustomerUserId(customer));
		customer.setUserId(userid2);
		assertTrue(CustomerServiceImp.validateCustomerUserId(customer));
	}
	
	// TEST CASES FOR CUSTOMER SWEET ORDER
	
	@Test 
	void testValidateCustomerSweetOrder() {
		Customer customer = new Customer(1,"Jeevee_1",null,new ArrayList<SweetItem>(), cart);
		assertFalse(CustomerServiceImp.validateCustomerSetSweetOrders(customer));
		HashSet<SweetOrder> sweetorderSet = new HashSet<SweetOrder>();
		customer.setSweetOrders(sweetorderSet);
		assertFalse(CustomerServiceImp.validateCustomerSetSweetOrders(customer));
		sweetorderSet.add(new SweetOrder());
		assertTrue(CustomerServiceImp.validateCustomerSetSweetOrders(customer));
	}
	
	// TEST CASES FOR CUSTOMER SWEET ITEMS
	@Test
	void testValidateCustomerSweetItem() {
		Customer customer = new Customer(2,"Raksha_2",new HashSet<SweetOrder>(),null,cart);
		assertFalse(CustomerServiceImp.validateCustomerSweetItem(customer));
		List<SweetItem> sweetItemList =  new ArrayList<SweetItem>();
		customer.setSweetItems(sweetItemList);
		assertFalse(CustomerServiceImp.validateCustomerSweetItem(customer));
		sweetItemList.add(new SweetItem());
		assertTrue(CustomerServiceImp.validateCustomerSweetItem(customer));
	}
}
