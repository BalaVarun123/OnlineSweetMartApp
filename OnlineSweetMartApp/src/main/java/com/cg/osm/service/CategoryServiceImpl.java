package com.cg.osm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.repository.ICategoryRepository;
import com.cg.osm.util.CategoryUtils;

/*
 * Author      : RAKSHA R
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : This is Cart Service Layer
*/

@Service
public class CategoryServiceImpl implements ICategoryService {
final static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private ICategoryRepository repo;
	
	/*
	 * Description     : This method Adds new Category
	 * Input Parameter : Category Object 
	 * Return Value    : CategoryDTO Object 
	 * Exception       : CategoryNotFoundException
	*/
	
	@Override
	public CategoryDTO addCategory(Category category)
	{
		LOGGER.info("addCategory() service has been initiated");
		Category category1 = repo.save(category);
		return CategoryUtils.convertToCategoryDto(category1);
	}
	
	/*
	 * Description     : This method Updates existing Category
	 * Input Parameter : Category Object 
	 * Return Value    : CategoryDTO Object 
	 * Exception       : CategoryNotFoundException
	 */

	@Override
	public CategoryDTO updateCategory(Category category) throws CategoryNotFoundException 
	{
		LOGGER.info("updateCategory() service has been initiated");
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
	 * Description     : This method Deletes existing Category
	 * Input Parameter : integer 
	 * Return Value    : CategoryDTO Object 
	 * Exception       : CategoryNotFoundException
	 */
	@Override
	public CategoryDTO cancelCategory(int categoryId) throws CategoryNotFoundException
	{
		LOGGER.info("cancelCategory() service has been initiated");
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
	 * Description     : This method Shows all existing Category
	 * Input Parameter : integer
	 * Return Value    : CategoryDTO Object 
	 * Exception       : CategoryNotFoundException
	 */

	@Override
	public List<CategoryDTO> showAllCategories()
	{
		LOGGER.info("showAllCategories() service has been initiated");
		List<Category> list= repo.findAll();
		
		return CategoryUtils.convertToCategoryDtoList(list);
	}

	/*
	 * Description     : This method shows the total cost for the grouped category 
	 * Input Parameter : integer 
	 * Return Value    : double
	 */
	@Override
	public double calculateTotalCost(int categoryId) 
	{
		LOGGER.info("calculateTotalCost() service has been initiated");
		double total;
		try {
			total = repo.calculateTotalCost(categoryId);
		}
		catch(AopInvocationException e) {
			total = 0;
			LOGGER.error("AopInvocationException occured.No suitable records found");
		}
		return total;
	}
	 
	/*
	 * Description      : This method Shows existing Category
	 * Input Parameter  : integer
	 * Return Value     : CategoryDTO Object 
	 * Exception        : CategoryNotFoundException
	 */
	
	public CategoryDTO showCategory(int categoryId) throws CategoryNotFoundException
	{
		LOGGER.info("showCategory() service has been initiated");
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
	
	//VALIDATIONS
	//1. Validating Category Id
	public static boolean validateCategoryId(Category category) 
	{
		LOGGER.info("validateCategoryId() has been initiated");
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
	//2. Validating Category Name
	public static boolean validateName(Category category) 
	{
		LOGGER.info("validateName() has been initiated");
		boolean flag=true;
		if (category==null)
		{
			flag=false;
		}
		else
		{
		String name= category.getName();
	    if (name==null)
		{
			flag=false;
		}
	    else
	    {
		if(category.getName().matches("^[a-zA-Z0-9]+$") && category.getName().length()>2)
			flag=true;
	    }
		}
		return flag; 
			
			
	}

	
}

