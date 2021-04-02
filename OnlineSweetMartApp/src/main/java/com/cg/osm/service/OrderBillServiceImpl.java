package com.cg.osm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.repository.IOrderBillRepository;
import com.cg.osm.util.OrderBillUtils;

@Service
public class OrderBillServiceImpl implements IOrderBillService{

	@Autowired
	IOrderBillRepository repository;
	@Autowired
	OrderBillUtils orderBillUtils;
	@Override
	public OrderBillDTO addOrderBill(OrderBill orderBill) {
		
		return orderBillUtils.convertToOrderBillDto(repository.save(orderBill));
	}

	@Override
	public OrderBillDTO updateOrderBill(OrderBill orderBill) throws OrderBillNotFoundException {
		OrderBill existingOrderBill = repository.findById(orderBill.getOrderBillId()).orElse(null);
		if (existingOrderBill == null) {
			throw new OrderBillNotFoundException();
		}
		else {
			return orderBillUtils.convertToOrderBillDto(repository.save(orderBill));
		}
	}

	@Override
	public OrderBillDTO cancelOrderBill(int orderBillId) throws OrderBillNotFoundException {
		OrderBill existingOrderBill = repository.findById(orderBillId).orElse(null);
		if (existingOrderBill == null) {
			throw new OrderBillNotFoundException();
		}
		else {
			repository.delete(existingOrderBill);
			return orderBillUtils.convertToOrderBillDto(existingOrderBill);
		}
	}

	@Override
	public List<OrderBillDTO> showAllOrderBills() {
		List<OrderBill> listOrderBills = repository.findAll();
		return orderBillUtils.convertToOrderBillDtoList(listOrderBills);
	}

	@Override
	public List<OrderBillDTO> showAllOrderBills(int orderBillId) {
		List<OrderBill> listOrderBills = new ArrayList<OrderBill>();
		Optional<OrderBill> orderBIllOptional = repository.findById(orderBillId);
		return orderBillUtils.convertToOrderBillDtoList(listOrderBills);
	}

}
