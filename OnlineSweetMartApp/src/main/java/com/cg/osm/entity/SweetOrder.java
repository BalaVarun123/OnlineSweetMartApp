package com.cg.osm.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SweetOrder {
    @Id
	private Integer sweetOrderId;
	private User user;
	private List<SweetItem> listItems;
	private LocalDate createdDate;
	private Map<Product, Long> groupedProducts;
	
}
