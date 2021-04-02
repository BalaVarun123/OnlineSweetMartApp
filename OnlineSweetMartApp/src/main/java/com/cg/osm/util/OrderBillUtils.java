package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.model.OrderBillDTO;
@Component
public class OrderBillUtils {
	public static List<OrderBillDTO> convertToOrderBillDtoList(List<OrderBill> list){
		List<OrderBillDTO> dtolist = new ArrayList<OrderBillDTO>();
		for(OrderBill orderBill : list) 
			dtolist.add(convertToOrderBillDto(orderBill));
		return dtolist;
	}
	
	public static OrderBillDTO convertToOrderBillDto(OrderBill orderBill) {
		OrderBillDTO orderBillDto = new OrderBillDTO();
		orderBillDto.setCreatedDate(orderBill.getCreatedDate());
		orderBillDto.setListSweetOrder(orderBill.getListSweetOrder());
		orderBillDto.setOrderBillId(orderBill.getOrderBillId());
		orderBillDto.setTotalCost(orderBill.getTotalCost());
		return orderBillDto;
	}
	
	
	
	public static List<OrderBill> convertToOrderBillList(List<OrderBillDTO> dtoList){
		List<OrderBill> list = new ArrayList<OrderBill>();
		for(OrderBillDTO orderBillDTO : dtoList) 
			list.add(convertToOrderBill(orderBillDTO));
		return list;
	}
	
	
	public static OrderBill convertToOrderBill(OrderBillDTO orderBillDto) {
		OrderBill orderBill = new OrderBill();
		orderBill.setCreatedDate(orderBillDto.getCreatedDate());
		orderBill.setListSweetOrder(orderBillDto.getListSweetOrder());
		orderBill.setOrderBillId(orderBillDto.getOrderBillId());
		orderBill.setTotalCost(orderBillDto.getTotalCost());
		return orderBill;
	}
}
