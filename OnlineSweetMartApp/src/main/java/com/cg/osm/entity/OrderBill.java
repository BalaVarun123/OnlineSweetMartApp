package com.cg.osm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderBill {
	@Id//Identity
	private Integer orderBillId;
	private LocalDate createdDate;
	private float totalCost;
	private List<SweetOrder> listSweetOrder;
	
}
