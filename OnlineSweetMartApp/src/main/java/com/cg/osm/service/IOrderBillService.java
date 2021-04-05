package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;

public interface IOrderBillService {
	public OrderBillDTO addOrderBill(OrderBill orderBill);
	public OrderBillDTO updateOrderBill(OrderBill orderBill) throws OrderBillNotFoundException;
	public OrderBillDTO cancelOrderBill(int orderBillId) throws OrderBillNotFoundException;
	public List<OrderBillDTO> showAllOrderBills();
	public List<OrderBillDTO> showAllOrderBills(int orderBillId);
}
