package com.cg.osm.entity;

import java.time.LocalDate;
import java.util.List;

public class OrderBill {
	
	private Integer orderBillId;
	private LocalDate createdDate;
	private float totalCost;
	private List<SweetOrder> listSweetOrder;
	
}
