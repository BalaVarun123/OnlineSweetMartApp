package com.cg.osm.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.repository.IOrderBillRepository;
import com.cg.osm.service.IOrderBillService;

import java.util.List;
import com.cg.osm.model.OrderBillDTO;
class OrderBillServiceImplTest {

	@Autowired
	IOrderBillService service;
	@Autowired 
	IOrderBillRepository repo;
	
	
	@Test
	void testAddOrderBill() {
		OrderBill orderBill = new OrderBill(1,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		assertNull(service.addOrderBill(null));
	}

	@Test
	void testUpdateOrderBill() throws OrderBillNotFoundException {
		OrderBill orderBill = new OrderBill(2,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertThrows(OrderBillNotFoundException.class, () -> service.updateOrderBill(orderBill));
		assertNotNull(service.addOrderBill(orderBill));
		orderBill.setTotalCost(11331.0f);
		assertNotNull(service.updateOrderBill(orderBill));
		assertNull(service.updateOrderBill(null));
	}

	@Test
	void testCancelOrderBill() throws OrderBillNotFoundException {
		OrderBill orderBill = new OrderBill(3,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertThrows(OrderBillNotFoundException.class, () -> service.cancelOrderBill(3));
		assertNotNull(service.addOrderBill(orderBill));
		orderBill.setTotalCost(11331.0f);
		assertNotNull(service.cancelOrderBill(3));
		assertEquals(0,service.showAllOrderBills(3).size());
	}

	@Test
	void testShowAllOrderBills() {
		OrderBill orderBill = new OrderBill(1,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		List<OrderBillDTO> orderbillDTOList = service.showAllOrderBills();
	}

	@Test
	void testShowAllOrderBillsInt() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrderBillCreatedDate() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrderBillListSweetOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrderBillId() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrderBillTotalCost() {
		fail("Not yet implemented");
	}

}
