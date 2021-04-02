package com.cg.osm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.repository.IOrderBillRepository;
import com.cg.osm.util.OrderBillUtils;

@Service
public class OrderBillServiceImpl implements IOrderBillService{

	@Autowired
	public IOrderBillRepository repository;
	@Override
	public OrderBillDTO addOrderBill(OrderBill orderBill) {
		if (orderBill == null)
			return  null;
		return OrderBillUtils.convertToOrderBillDto(repository.save(orderBill));
	}

	@Override
	public OrderBillDTO updateOrderBill(OrderBill orderBill) throws OrderBillNotFoundException {
		if (orderBill == null)
			return  null;
		OrderBill existingOrderBill = repository.findById(orderBill.getOrderBillId()).orElse(null);
		if (existingOrderBill == null) {
			throw new OrderBillNotFoundException("Invalid id.");
		}
		else {
			return OrderBillUtils.convertToOrderBillDto(repository.save(orderBill));
		}
	}

	@Override
	public OrderBillDTO cancelOrderBill(int orderBillId) throws OrderBillNotFoundException {
		OrderBill existingOrderBill = repository.findById(orderBillId).orElse(null);
		if (existingOrderBill == null) {
			throw new OrderBillNotFoundException("Invalid id.");
		}
		else {
			repository.delete(existingOrderBill);
			return OrderBillUtils.convertToOrderBillDto(existingOrderBill);
		}
	}

	@Override
	public List<OrderBillDTO> showAllOrderBills() {
		List<OrderBill> listOrderBills = repository.findAll();
		return OrderBillUtils.convertToOrderBillDtoList(listOrderBills);
	}

	@Override
	public List<OrderBillDTO> showAllOrderBills(int orderBillId) {
		List<OrderBill> listOrderBills = new ArrayList<OrderBill>();
		Optional<OrderBill> orderBIllOptional = repository.findById(orderBillId);
		if (orderBIllOptional.isPresent())
			listOrderBills.add(orderBIllOptional.get());
		return OrderBillUtils.convertToOrderBillDtoList(listOrderBills);
	}
	
	
	public static boolean validateOrder(OrderBill orderBill) {
		boolean flag;
		if (orderBill == null  ) {
			flag = false;
		}
		else if (!(validateOrderBillCreatedDate(orderBill) && validateOrderBillListSweetOrder(orderBill) &&  validateOrderBillId(orderBill) && validateOrderBillTotalCost(orderBill))) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	

	public static boolean validateOrderBillCreatedDate(OrderBill orderBill) {
		boolean flag = true;
		if (orderBill.getCreatedDate() == null || orderBill.getCreatedDate().isAfter(LocalDate.now()))
			flag = false;
		return flag;
	}
	
	public static boolean validateOrderBillListSweetOrder(OrderBill orderBill) {
		boolean flag = true;
		List<SweetOrder> listSweetOrder = orderBill.getListSweetOrder();
		if (listSweetOrder == null || listSweetOrder.size() == 0)
			flag = false;
		return flag;
	}
	public static boolean validateOrderBillId(OrderBill orderBill) {
		boolean flag = true;
		Integer id = orderBill.getOrderBillId();
		OrderBillServiceImpl service1 = new OrderBillServiceImpl();
		if (id == null|| id < 0 || !service1.repository.existsById(id))
			flag = false;
		return flag;
	}
	
	public static boolean validateOrderBillTotalCost(OrderBill orderBill) {
		boolean flag = true;
		float totalCost = orderBill.getTotalCost();
		if ( totalCost < 0 || Float.isNaN(totalCost))
			flag = false;
		return flag;
	}

}
