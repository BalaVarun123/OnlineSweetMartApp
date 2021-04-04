package com.cg.osm.service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.repository.ISweetItemRepository;
import com.cg.osm.util.SweetItemUtils;
 @Service
public class SweetItemServiceImp implements ISweetItemService {
	 @Autowired 
	 ISweetItemRepository repository;
	@Override
	public SweetItemDTO addSweetItem(SweetItem sweetItem) {	
		if (sweetItem == null)
			return  null;
		return  SweetItemUtils. convertToSweetItemDto( repository.save(sweetItem));
	}
	@Override
	public SweetItemDTO updateSweetItem(SweetItem sweetItem) throws SweetItemNotFoundException {	
		if (sweetItem == null)
			return  null;
		SweetItem existingSweetItem = repository.findById(sweetItem.getOrderItemId()).orElse(null);
		if (existingSweetItem == null) {
			throw new SweetItemNotFoundException("invalid ID");
		}
		else {
			return SweetItemUtils.convertToSweetItemDto(repository.save(sweetItem));
		}
	}	
	@Override
	public SweetItemDTO cancelSweetItem(int ordertItemId) throws SweetItemNotFoundException {
		SweetItem existingSweetItem = repository.findById(ordertItemId).orElse(null);
		if (existingSweetItem == null) {
			throw new SweetItemNotFoundException("Invalid ID");
		}
		else {
			repository.delete(existingSweetItem);
			return SweetItemUtils.convertToSweetItemDto(existingSweetItem);
		}
	}
	@Override
	public List<SweetItemDTO> showAllSweetItems() {
		List<SweetItem> listSweetItems = repository.findAll();
		return SweetItemUtils.convertToSweetItemDtoList(listSweetItems);
	}	
	@Override
	public List<SweetItemDTO> showAllSweetItems(int orderItemId) {
		List<SweetItem> listSweetItems = new ArrayList<SweetItem>();
		Optional<SweetItem> sweetItemOptional = repository.findById(orderItemId);
		if (sweetItemOptional.isPresent())
			listSweetItems.add(sweetItemOptional.get());
		return SweetItemUtils.convertToSweetItemDtoList(listSweetItems);
	}

 
 public static boolean validateSweetItem(SweetItem sweetItem) {
		boolean flag;
		if (sweetItem == null  ) {
			flag = false;
		}
		else if (!(validateSweetItemProduct(sweetItem) && validateSweetItemOrderItemId(sweetItem) && validateSweetItemSweetOrder(sweetItem))) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
private static boolean validateSweetItemProduct(SweetItem sweetItem) {
	boolean flag = true;
	Product product = sweetItem.getProduct();
	if (product == null)
		flag = false;
	return flag;
}

private static boolean validateSweetItemSweetOrder(SweetItem sweetItem) {
	boolean flag = true;
	SweetOrder sweetOrder = sweetItem.getSweetOrder();
	if (sweetOrder == null)
		flag = false;
	return flag;
}

private static boolean validateSweetItemOrderItemId(SweetItem sweetItem) {
	boolean flag = true;
	Integer id = sweetItem.getOrderItemId();
	SweetItemServiceImp service = new SweetItemServiceImp();
	if (id == null|| id < 0 || !service.repository.existsById(id))
		flag = false;
	return flag;
}
}
	
