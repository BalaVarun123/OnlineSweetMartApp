package com.cg.osm.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.SweetItem;
@Repository
public interface ISweetItemRepository extends JpaRepository<SweetItem,Integer>{

	/*public SweetItem addSweetItem(SweetItem SweetItem);
	public SweetItem updateSweetItem(SweetItem SweetItem) throws SweetItemNotFoundException;
	public SweetItem cancelSweetItem(int SweetItemId) throws SweetItemNotFoundException;
	public List<SweetItem> showAllSweetItems();*/

}
