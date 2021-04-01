package com.cg.osm.repository;

import java.util.List;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.OrderBillNotFoundException;

public interface OrderBillRepository {

	public OrderBill addOrderBill(OrderBill OrderBill);
	public OrderBill updateOrderBill(OrderBill OrderBill) throws OrderBillNotFoundException;
	public OrderBill cancelOrderBill(int OrderBillId) throws OrderBillNotFoundException;
	public List<OrderBill> showAllOrderBills();
	public List<OrderBill> showAllOrderBills(int OrderBilldId);
	
}
