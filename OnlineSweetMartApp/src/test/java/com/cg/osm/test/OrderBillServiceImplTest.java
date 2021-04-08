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
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.service.IOrderBillService;
import com.cg.osm.service.OrderBillServiceImpl;
import com.cg.osm.util.OrderBillUtils;


@SpringBootTest
class OrderBillServiceImplTest {

	@Autowired
	IOrderBillService service;
	
	
	
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
		OrderBill orderBill = new OrderBill(4,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		List<OrderBillDTO> orderbillDTOList = service.showAllOrderBills();
		assertNotNull(orderbillDTOList);
		//assertTrue(orderbillDTOList.contains(OrderBillUtils.convertToOrderBillDto(orderBill)));
	}

	@Test
	void testShowAllOrderBillsInt() {
		OrderBill orderBill = new OrderBill(5,LocalDate.now(),0,new ArrayList<SweetOrder>());
		assertNotNull(service.addOrderBill(orderBill));
		List<OrderBillDTO> orderbillDTOList = service.showAllOrderBills(5);
		assertNotNull(orderbillDTOList);
		assertEquals(1,orderbillDTOList.size());
		assertTrue(orderbillDTOList.contains(OrderBillUtils.convertToOrderBillDto(orderBill)));
	}

	@Test
	void testValidateOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateOrderBillCreatedDate() {
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
	}

	@Test
	void testValidateOrderBillListSweetOrder() {
		OrderBill orderBill = new OrderBill(5,LocalDate.now(),0,null);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		List<SweetOrder> sweetOrderList = new ArrayList<SweetOrder>();
		orderBill.setListSweetOrder(sweetOrderList);
		assertFalse(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
		sweetOrderList.add(new SweetOrder());
		assertTrue(OrderBillServiceImpl.validateOrderBillListSweetOrder(orderBill));
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
