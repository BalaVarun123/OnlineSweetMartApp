package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;

public interface ICategoryService  {

	public CategoryDTO addCategory(Category category);
	public CategoryDTO updateCategory(Category category) throws CategoryNotFoundException;
	public CategoryDTO cancelCategory(int categoryId) throws CategoryNotFoundException;
	public List<CategoryDTO> showAllCategorys();
	public double calculateTotalCost(int categoryId);

}
