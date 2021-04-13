package com.cg.osm.model;


import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.User;

@Component
public class SweetOrderDTO {


	private int sweetOrderId;
	private User user;

	private List<SweetItem> listItems;
	private LocalDate createdDate;
	
	public SweetOrderDTO() {
		
	}
	
	

	public SweetOrderDTO(int sweetOrderId, User user, List<SweetItem> listItems, LocalDate createdDate) {
		super();
		this.sweetOrderId = sweetOrderId;
		this.user = user;
		this.listItems = listItems;
		this.createdDate = createdDate;
	}



	public Integer getSweetOrderId() {
		return sweetOrderId;
	}

	public void setSweetOrderId(Integer sweetOrderId) {
		this.sweetOrderId = sweetOrderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SweetItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<SweetItem> listItems) {
		this.listItems = listItems;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


}
	

	
	
	
	
