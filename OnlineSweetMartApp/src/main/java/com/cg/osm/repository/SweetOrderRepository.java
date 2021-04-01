package com.cg.osm.repository;

import java.util.List;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;

public interface SweetOrderRepository  {

	public SweetOrder addSweetOrder(SweetOrder sweetOrder);
	public SweetOrder updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException;
	public SweetOrder cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException;
	public List<SweetOrder> showAllSweetOrders();
	public double calculateTotalCost(int sweetOrderId);
	
}
