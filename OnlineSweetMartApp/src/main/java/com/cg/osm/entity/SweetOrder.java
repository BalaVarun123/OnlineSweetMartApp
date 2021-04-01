package com.cg.osm.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SweetOrder {

	private Integer sweetOrderId;
	private User user;
	private List<SweetItem> listItems;
	private LocalDate createdDate;
	private Map<Product, Long> groupedProducts;
	
}
