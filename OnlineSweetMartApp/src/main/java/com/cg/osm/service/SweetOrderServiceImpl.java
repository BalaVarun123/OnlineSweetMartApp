package com.cg.osm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
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
		SweetOrderDTO sweetOrderDTO;
		if (sweetOrder == null) {
			sweetOrderDTO = null;
		}
		else {
			if (repository.existsById(sweetOrder.getSweetOrderId())) {
				sweetOrderDTO = SweetOrderUtils. convertToSweetOrderDto( repository.save(sweetOrder));
			}
			else {
				throw new SweetOrderNotFoundException();
			}
		}
		return sweetOrderDTO;
	}

	@Override
	public SweetOrderDTO cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		SweetOrderDTO sweetOrderDTO;
		SweetOrder sweetOrder = repository.findById(sweetOrderId).orElse(null);
		if (sweetOrder != null) {
			repository.delete(sweetOrder);
			sweetOrderDTO = SweetOrderUtils.convertToSweetOrderDto(sweetOrder);
		} else {
			throw new SweetOrderNotFoundException();
		}

		return sweetOrderDTO;
	}

	@Override
	public List<SweetOrderDTO> showAllSweetOrders() {
		
		return SweetOrderUtils.convertToSweetOrderDtoList(repository.findAll());
	}

	@Override
	public double calculateTotalCost(int sweetOrderId) {
		double sum = 0.0;
		Long count;
		SweetOrder sweetOrder = repository.findById(sweetOrderId).orElse(null);
		if (sweetOrder != null) {
			Map<Product, Long> groupedProducts = sweetOrder.getGroupedProducts();
			if (groupedProducts != null) {
				Set<Product> keys = groupedProducts.keySet();
				for (Product product : keys) {
					count =  groupedProducts.get(product);
					if (count != null) {
						sum += (product.getPrice() * count);
					}
				}
			}
			
		}
		return sum;
	}
	
	public static boolean vaidateGroupedProducts(SweetOrder sweetOrder) {
		boolean result;
		Map<Product,Long> groupedProducts = null;
		groupedProducts = sweetOrder.getGroupedProducts();
		
		if (groupedProducts == null) {
			result = false;
		}
		else {
			Map<Product,Long> correctGroupedProducts = sweetOrder.initiateGroupedProducts();
			if (groupedProducts.equals(correctGroupedProducts)) {
				result = true;
			}
			else {
				result = false;
			}
		}
		return result;
	}

}
