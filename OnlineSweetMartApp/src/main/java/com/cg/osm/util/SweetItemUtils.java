package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.model.SweetItemDTO;
@Component
public class SweetItemUtils {
	public static List<SweetItemDTO> convertToSweetItemDtoList(List<SweetItem> list){
		List<SweetItemDTO> dtolist = new ArrayList<SweetItemDTO>();
		for(SweetItem sweetItem : list) 
			dtolist.add(convertToSweetItemDto(sweetItem));
		return dtolist;
	}
	
	public static SweetItemDTO convertToSweetItemDto(SweetItem sweetItem) {
		SweetItemDTO sweetItemDto = new SweetItemDTO();
		sweetItemDto.setOrderItemId(sweetItem.getOrderItemId());
		sweetItemDto.setProduct(sweetItem.getProduct());
		sweetItemDto.setSweetOrder(sweetItem.getSweetOrder());
		return sweetItemDto;
	}
	
	
	
	public static List<SweetItem> convertToSweetItemList(List<SweetItemDTO> dtoList){
		List<SweetItem> list = new ArrayList<SweetItem>();
		for(SweetItemDTO sweetItemDTO : dtoList) 
			list.add(convertToSweetItem(sweetItemDTO));
		return list;
	}
	
	
	public static SweetItem convertToSweetItem(SweetItemDTO sweetItemDto) {
		SweetItem sweetItem = new SweetItem();
		sweetItemDto.setOrderItemId(sweetItemDto.getOrderItemId());
		sweetItemDto.setProduct(sweetItemDto.getProduct());
		sweetItemDto.setSweetOrder(sweetItemDto.getSweetOrder());
		return sweetItem;
	}
}