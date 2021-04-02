package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.repository.ISweetOrderRepository;
import com.cg.osm.util.SweetOrderUtils;

 @Service

public class SweetOrderServiceImpl implements ISweetOrderService {
	 @Autowired
	 
	 ISweetOrderRepository repository;


	@Override
	public SweetOrderDTO addSweetOrder(SweetOrder sweetOrder) {
	
		return  SweetOrderUtils. convertToSweetOrderDto( repository.save(sweetOrder));
	}

	@Override
	public SweetOrderDTO updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException {
		
		return null;
	}

	@Override
	public SweetOrderDTO cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		
		return null;
	}

	@Override
	public List<SweetOrderDTO> showAllSweetOrders() {
		
		return SweetOrderUtils.convertToSweetOrderDtoList(repository.findAll());
	}

	@Override
	public double calculateTotalCost(int sweetOrderId) {
	
		return 0;
	}

}
