package com.cg.osm.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.repository.ICategoryRepository;
import com.cg.osm.util.CategoryUtils;
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository repo;
	
	@Override
	public CategoryDTO addCategory(Category category) {
		Category category1 = repo.save(category);
		return CategoryUtils.convertToCategoryDto(category1);
	}

	@Override
	public CategoryDTO updateCategory(Category category) throws CategoryNotFoundException 
	{
		if (category ==null)
			return null;
		Category existingCategory = repo.findById(category.getCategoryId()).orElse(null);
		if (existingCategory == null)
		{
			throw new CategoryNotFoundException("No such category");
		}
		else
		return CategoryUtils.convertToCategoryDto(repo.save(category));
	}

	@Override
	public CategoryDTO cancelCategory(int categoryId) throws CategoryNotFoundException {
		Category existingCategory = repo.findById(categoryId).orElse(null);
		if(existingCategory == null)
		{
			throw new CategoryNotFoundException("No such category");
		}
		else
		{
			repo.delete(existingCategory);
			return CategoryUtils.convertToCategoryDto(existingCategory);
		}
	}

	@Override
	public List<CategoryDTO> showAllCategories() {
		List<Category> list= repo.findAll();
		
		return CategoryUtils.convertToCategoryDtoList(list);
	}

	@Override
	public double calculateTotalCost(int categoryId) 
	{
		double total;
		try {
			total = repo.calculateTotalCost(categoryId);
		}
		catch(AopInvocationException e) {
			total = 0;
			Logger.getLogger(this.getClass()).error("AopInvocationException occured.No suitable records found");
		}
		return total;
	}
	
	
	public CategoryDTO showCategory(int categoryId) throws CategoryNotFoundException{
		Category existingCategory = repo.findById(categoryId).orElse(null);
		if(existingCategory == null)
		{
			throw new CategoryNotFoundException("No such category");
		}
		else
		{
			return CategoryUtils.convertToCategoryDto(existingCategory);
		}
	}
	
	
	public static boolean validateCategoryId(Category category) 
	{
		boolean flag = true;
		if (category == null) {
			flag = false;
		}
		else {
			Integer id = category.getCategoryId();
			if (id == null|| id < 0 )
				flag = false;
		}
		
		return flag;
	}
	public static boolean validateName(Category category) 
	{
		boolean flag=true;
		if (category==null)
		{
			flag=false;
		}
		String name= category.getName();
	    if (name==null)
		{
			flag=false;
		}
		if(category.getName().matches("^[a-zA-Z0-9]+$") && category.getName().length()>2)
			flag=true;
	
		return flag; 
			
			
	}

	
}

