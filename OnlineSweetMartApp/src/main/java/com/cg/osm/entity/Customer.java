package com.cg.osm.entity;
import java.util.List;
import java.util.Set;

import com.cg.osm.entity.SweetOrder;

public class Customer {

    private Long userId;
    private String username;
    private Set<SweetOrder> sweetOrders;
    private List<SweetItem> sweetItems;
	private Cart cart;
}
