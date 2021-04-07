package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.repository.ICategoryRepository;
import com.cg.osm.util.CategoryUtils;

/*
 * Author : RAKSHA R
 * Version : 1.0
 * Date : 02-04-2021
 * Description : This is Category Service Layer
*/

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository repo;
	
	/*
	 * Description : This method Adds new Category
	 * Input Param : Category Object 
	 * Return Value : CategoryDTO Object 
	*/
	@Override
	public CategoryDTO addCategory(Category category) 
	{
		Category ctgy = repo.save(category);
		return CategoryUtils.convertToCategoryDto(ctgy);
	}
	
	/*
	 * Description : This method updates Category
	 * Input Param : Category Object 
	 * Return Value : CategoryDTO Object 
	*/

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
	
	/*
	 * Description : This method deletes Category
	 * Input Param : int 
	 * Return Value : CategoryDTO Object 
	*/

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
	/*
	 * Description : This method Shows existing Categories
	 * Return Value : CategoryDTO Object 
	 * Exception : CategoryNotFoundException
	 */

	@Override
	public List<CategoryDTO> showAllCategorys() {
		List<Category> list= repo.findAll();
		
		return CategoryUtils.convertToCategoryDtoList(list);
	}
	
	/*
	 * Description : This method calculates the total cost for that cateory id
	 * Input Param : int
	 * Return Value : double
	  */

	@Override
	public double calculateTotalCost(int categoryId) 
	{
		return repo.calculateTotalCost(categoryId);
	}
	
	
	//VALIDATIONS
	public static boolean validateCategoryId(Category category) throws CategoryNotFoundException
	{
		boolean flag=false;
		if(category.getCategoryId()>0)
			flag=true;
		else
			throw new CategoryNotFoundException("Not a valid category id");
		return flag;
	}
	public static boolean validateName(Category category) throws CategoryNotFoundException
	{
		boolean flag=false;
		if(category.getName().matches("^[a-zA-Z]+$") && category.getName().length()>2)
			flag=true;
		else 
			throw new CategoryNotFoundException("Not a valid name");
		return flag; 
			
			
	}

	
}

