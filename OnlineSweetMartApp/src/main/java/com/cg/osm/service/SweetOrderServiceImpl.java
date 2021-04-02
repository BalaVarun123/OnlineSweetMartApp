package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.repository.ISweetOrderRepository;

 @Service

public class SweetOrderServiceImpl implements ISweetOrderService {
	 @Autowired
	 ISweetOrderRepository repository;

	@Override
	public SweetOrderDTO addSweetOrder(SweetOrder sweetOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SweetOrderDTO updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SweetOrderDTO cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SweetOrderDTO> showAllSweetOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateTotalCost(int sweetOrderId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
