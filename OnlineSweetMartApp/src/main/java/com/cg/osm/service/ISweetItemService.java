package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.SweetItemDTO;

public interface ISweetItemService {
	public SweetItemDTO addSweetItem(SweetItem sweetItem);
	public SweetItemDTO updateSweetItem(SweetItem sweetItem) throws SweetItemNotFoundException;
	public SweetItemDTO cancelSweetItem(int orderItemItemId) throws SweetItemNotFoundException;
	public List<SweetItemDTO> showAllSweetItems();
}


