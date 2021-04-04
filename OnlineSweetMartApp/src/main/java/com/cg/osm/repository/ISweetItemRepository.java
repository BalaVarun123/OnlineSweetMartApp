package com.cg.osm.repository;

import java.util.List;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.SweetItemNotFoundException;

public interface ISweetItemRepository {

	public SweetItem addSweetItem(SweetItem SweetItem);
	public SweetItem updateSweetItem(SweetItem SweetItem) throws SweetItemNotFoundException;
	public SweetItem cancelSweetItem(int SweetItemId) throws SweetItemNotFoundException;
	public List<SweetItem> showAllSweetItems();

}
