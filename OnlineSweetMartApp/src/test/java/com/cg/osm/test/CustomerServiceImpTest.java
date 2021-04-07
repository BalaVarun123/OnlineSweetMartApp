package com.cg.osm.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.Customer;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.repository.ICustomerRepository;
import com.cg.osm.service.ICustomerService;
import com.cg.osm.util.CustomerUtils;
import com.cg.osm.service.CustomerServiceImp;


@SpringBootTest
public class CustomerServiceImpTest {
	
	@Autowired
	ICustomerService service;
	Customer customer;
	void testAddustomer() {
		Customer customer= new Customer(13,"Jeevee1_ ");
		assertNotNull(service.addCustomer(customer));
		assertNull(service.addCustomer(null));
	}

	@Test
	void testUpdateCustomer() throws CustomerNotFoundException {
		Customer customer = new Customer(12,"Raksha_2");
		assertThrows(CustomerNotFoundException.class, () -> service.updateCustomer(customer));
		assertNotNull(service.addCustomer(customer));
		assertNotNull(service.updateCustomer(customer));
		assertNull(service.updateCustomer(null));
	}

	@Test
	void testCancelCustomer() throws CustomerNotFoundException {
		Customer customer = new Customer(11,"Bala_3");
		assertThrows(CustomerNotFoundException.class, () -> service.cancelCustomer(3));
		assertNotNull(service.addCustomer(customer));
				assertNotNull(service.cancelCustomer(3));
		assertEquals(0,service.showAllCustomers(3).size());
	}

	@Test
	void testShowAllCustomers() {
		Customer customer = new Customer(10,"Ujjwal_4");
		assertNotNull(service.addCustomer(customer));
		List<CustomerDTO> customerDTOList = service.showAllCustomers();
		assertNotNull(customerDTOList);
		assertTrue(customerDTOList.contains(CustomerUtils.convertToCustomerDto(customer)));
	}

	@Test
	void testShowAllCustomersInt() {
		Customer customer = new Customer(14,"Sai_5");
		assertNotNull(service.addCustomer(customer));
		List<CustomerDTO> customerDTOList = service.showAllCustomers(5);
		assertNotNull(customerDTOList);
		assertEquals(1,customerDTOList.size());
		assertTrue(customerDTOList.contains(CustomerUtils.convertToCustomerDto(customer)));
	}

	@Test
	void testValidateCustomerListSweetOrder() throws CustomerNotFoundException {
		Customer customer = new Customer(16,"Annie_6");
		assertFalse(CustomerServiceImp.validateCustomerUserId(customer));
		List<SweetItem> sweetItemList = new ArrayList<SweetItem>();
		customer.setSweetItems(sweetItemList);
		assertFalse(CustomerServiceImp.validateCustomerUsername(customer));
		sweetItemList.addAll((Collection<? extends SweetItem>) new SweetOrder());
		assertTrue(CustomerServiceImp.validateCustomerSweetItem(customer));
	}

	

}
