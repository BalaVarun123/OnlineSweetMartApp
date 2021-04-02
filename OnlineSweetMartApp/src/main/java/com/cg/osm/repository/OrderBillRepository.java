package com.cg.osm.repository;

import java.util.List;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;

public interface OrderBillRepository {

	public OrderBillDTO addOrderBill(OrderBill OrderBill);
	public OrderBillDTO updateOrderBill(OrderBill OrderBill) throws OrderBillNotFoundException;
	public OrderBillDTO cancelOrderBill(int OrderBillId) throws OrderBillNotFoundException;
	public List<OrderBillDTO> showAllOrderBills();
	public List<OrderBillDTO> showAllOrderBills(int OrderBilldId);
	
}
