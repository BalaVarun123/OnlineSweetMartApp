package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.model.SweetOrderDTO;
@Component
public class SweetOrderUtils {
	public static List<SweetOrderDTO> convertToSweetOrderDtoList(List<SweetOrder> list){
		List<SweetOrderDTO> dtolist = new ArrayList<SweetOrderDTO>();
		for(SweetOrder sweetOrder : list) 
			dtolist.add(convertToSweetOrderDto(sweetOrder));
		return dtolist;
	}
	
	public static SweetOrderDTO convertToSweetOrderDto(SweetOrder sweetOrder) {
		SweetOrderDTO sweetOrderDto = new SweetOrderDTO();
		sweetOrderDto.setCreatedDate(sweetOrder.getCreatedDate());
		sweetOrderDto.setListSweetOrder(sweetOrder.getListsweetOrder());
		sweetOrderDto.setSweetOrderId(sweetOrder.getSweetOrderId());
		sweetOrderDto.setTotalCost(sweetOrder.getTotalCost());
		return sweetOrderDto;
	}
	
	
	
	public static List<SweetOrder> convertToSweetOrderList(List<SweetOrderDTO> dtoList){
		List<SweetOrder> list = new ArrayList<SweetOrder>();
		for(SweetOrderDTO sweetOrderDTO : dtoList) 
			list.add(convertToSweetOrder(sweetOrderDTO));
		return list;
	}
	
	
	public static SweetOrder convertToSweetOrder(SweetOrderDTO sweetOrderDto) {
		SweetOrder sweetOrder = new SweetOrder();
		sweetOrder.setCreatedDate(sweetOrderDto.getCreatedDate());
		sweetOrder.setListSweetOrder(sweetOrderDto.getListSweetOrder());
		sweetOrder.setSweetOrderId(sweetOrderDto.getSweetOrderId());
		sweetOrder.setTotalCost(sweetOrderDto.getTotalCost());
		return sweetOrder;
	}
}
