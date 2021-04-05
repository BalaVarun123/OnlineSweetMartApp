package com.cg.osm.model;

import org.springframework.stereotype.Component;

@Component
public class CategoryDTO 
{
	private Integer categoryId;
    private String name;
	
    public CategoryDTO()
    {
    	super();
    }
    
    public CategoryDTO(Integer categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
	
    
    public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
    
}
