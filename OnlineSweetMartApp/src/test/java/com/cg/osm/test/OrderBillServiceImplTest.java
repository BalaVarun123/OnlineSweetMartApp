package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.repository.IOrderBillRepository;
import com.cg.osm.service.IOrderBillService;
import com.cg.osm.service.OrderBillServiceImpl;
import com.cg.osm.util.OrderBillUtils;

import java.util.List;
import com.cg.osm.model.OrderBillDTO;


@SpringBootTest
class OrderBillServiceImplTest {

	@Autowired
	IOrderBillService service;
	
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	void beforeAll() {
		LOGGER.info("JUnit testing for OrderBillServiceImpl is initiated.");
	}
	
	void afterAll() {
		LOGGER.info("JUnit testing for OrderBillServiceImpl is terminated.");
	}
	
	@Test
	void testAddOrderBill() {
		LOGGER.info("testAddOrderBill is initiated.");
		OrderBill orderBill = new OrderBill(1,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		assertNull(service.addOrderBill(null));
		LOGGER.info("testAddOrderBill is terminated.");
	}

	@Test
	void testUpdateOrderBill() throws OrderBillNotFoundException {
		LOGGER.info("testUpdateOrderBill is initiated.");
		OrderBill orderBill = new OrderBill(2,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertThrows(OrderBillNotFoundException.class, () -> service.updateOrderBill(orderBill));
		OrderBillDTO orderBillDTO = service.addOrderBill(orderBill);
		assertNotNull(orderBillDTO);
		orderBill.setOrderBillId(orderBillDTO.getOrderBillId());
		orderBill.setTotalCost(11331.0f);
		assertNotNull(service.updateOrderBill(orderBill));
		assertNull(service.updateOrderBill(null));
		LOGGER.info("testUpdateOrderBill is terminated.");
	}

	@Test
	void testCancelOrderBill() throws OrderBillNotFoundException {
		LOGGER.info("testCancelOrderBill is initiated.");
		OrderBill orderBill = new OrderBill(3,LocalDate.now(),23,new ArrayList<SweetOrder>());
		
		assertThrows(OrderBillNotFoundException.class, () -> service.cancelOrderBill(3));
		OrderBillDTO orderBillDTO = service.addOrderBill(orderBill);
		assertNotNull(orderBillDTO);
		int id = orderBillDTO.getOrderBillId();
		//orderBill.setTotalCost(11331.0f);
		LOGGER.info("orderBIll id = "+id);
		assertNotNull(service.cancelOrderBill(id));
		assertEquals(0,service.showAllOrderBills(id).size());
		LOGGER.info("testCancelOrderBill is terminated.");
	}

	@Test
	void testShowAllOrderBills() {
		LOGGER.info("testShowAllOrderBills is initiated.");
		OrderBill orderBill = new OrderBill(4,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		List<OrderBillDTO> orderbillDTOList = service.showAllOrderBills();
		assertNotNull(orderbillDTOList);
		LOGGER.info("testShowAllOrderBills is terminated.");
	}

	@Test
	void testShowAllOrderBillsInt() {
		LOGGER.info("testShowAllOrderBillsInt is initiated.");
		OrderBill orderBill = new OrderBill(5,LocalDate.now(),0,new ArrayList<SweetOrder>());
		OrderBillDTO orderBillDTO = service.addOrderBill(orderBill);
		assertNotNull(orderBillDTO);
		List<OrderBillDTO> orderbillDTOList = service.showAllOrderBills(orderBillDTO.getOrderBillId());
		assertNotNull(orderbillDTOList);
		assertEquals(1,orderbillDTOList.size());
		assertTrue(orderbillDTOList.get(0).getOrderBillId().equals(orderBillDTO.getOrderBillId()));
		LOGGER.info("testShowAllOrderBillsInt is terminated.");
	}



	@Test
	void testValidateOrderBillCreatedDate() {
		LOGGER.info("testValidateOrderBillCreatedDate is initiated.");
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = date1.withYear(1990);
		LocalDate date3 = date1.withYear(2030);
		OrderBill orderBill = new OrderBill(5,date1,0,new ArrayList<SweetOrder>());
		assertTrue(OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill));
		orderBill.setCreatedDate(date2);
		assertTrue(OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill));
		orderBill.setCreatedDate(date3);
		assertFalse(OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill));
		assertFalse(OrderBillServiceImpl.validateOrderBillCreatedDate(null));
		orderBill.setCreatedDate(null);
		assertFalse(OrderBillServiceImpl.validateOrderBillCreatedDate(orderBill));
		LOGGER.info("testValidateOrderBillCreatedDate is terminated.");
	}

	@Test
	void testValidateOrderBillListSweetOrder() {
		LOGGER.info("testValidateOrderBillListSweetOrder is initiated.");
		OrderBill orderBill = new OrderBill(5,LocalDate.now(),0,null);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		List<SweetOrder> sweetOrderList = new ArrayList<SweetOrder>();
		orderBill.setListSweetOrder(sweetOrderList);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		sweetOrderList.add(new SweetOrder());
		assertTrue(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		LOGGER.info("testValidateOrderBillListSweetOrder is terminated.");
	}

	@Test
	void testValidateOrderBillId() {
		LOGGER.info("testValidateOrderBillId is initiated.");
		OrderBill orderBill = new OrderBill(5,LocalDate.now(),0,null);
		assertNotNull(service.addOrderBill(orderBill));
		assertTrue(OrderBillServiceImpl.validateOrderBillId(orderBill));
		orderBill.setOrderBillId(-5);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		orderBill.setOrderBillId(2342434);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		LOGGER.info("testValidateOrderBillId is terminated.");
	}

	@Test
	void testValidateOrderBillTotalCost() {
		LOGGER.info("testValidateOrderBillTotalCost is initiated.");
		OrderBill orderBill = new OrderBill(-5,LocalDate.now(),-1,null);
		assertFalse(OrderBillServiceImpl.validateOrderBillTotalCost(orderBill));
		orderBill.setTotalCost(200);
		assertTrue(OrderBillServiceImpl.validateOrderBillTotalCost(orderBill));
		LOGGER.info("testValidateOrderBillTotalCost is terminated.");
	}

}
