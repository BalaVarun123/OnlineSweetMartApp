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
		sweetOrderDto.setUser(sweetOrder.getUser());
		sweetOrderDto.setListItems(sweetOrder.getListItems());
		//sweetOrderDto.setGroupedProducts(sweetOrder.getGroupedProducts());
		sweetOrderDto.setSweetOrderId(sweetOrder.getSweetOrderId());
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
		sweetOrder.setUser(sweetOrderDto.getUser());
		sweetOrder.setListItems(sweetOrderDto.getListItems());
		//sweetOrder.setGroupedProducts(sweetOrderDto.getGroupedProducts());
		sweetOrder.setSweetOrderId(sweetOrderDto.getSweetOrderId());
		return sweetOrder;
	}
}
