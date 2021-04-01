package com.cg.osm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
	private Integer categoryId;
    private String name;
}
