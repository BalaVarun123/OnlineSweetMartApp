package com.cg.osm.service;

import java.util.List;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetOrderDTO;

public interface ISweetOrderService  {

	public SweetOrderDTO addSweetOrder(SweetOrder sweetOrder);
	public SweetOrderDTO updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException;
	public SweetOrderDTO cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException;
	public List<SweetOrderDTO> showAllSweetOrders();
	public double calculateTotalCost(int sweetOrderId) throws SweetOrderNotFoundException;
	public SweetOrderDTO showSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException;
	
}
