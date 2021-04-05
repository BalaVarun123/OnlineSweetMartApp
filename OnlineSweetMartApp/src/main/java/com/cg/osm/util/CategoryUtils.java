package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import com.cg.osm.entity.Category;
import com.cg.osm.model.CategoryDTO;

public class CategoryUtils
{
	public static CategoryDTO convertToCategoryDto(Category category)
	{
		CategoryDTO dto = new CategoryDTO();
		dto.setCategoryId(category.getCategoryId());
		dto.setName(category.getName());
		return dto;
	}
	
	public static List<CategoryDTO> convertToCategoryDtoList(List<Category> list)
	{
		List<CategoryDTO> dtolist = new ArrayList<CategoryDTO>();
		for ( Category category : list)
			dtolist.add(convertToCategoryDto(category));
		return dtolist;
	}
	
}
