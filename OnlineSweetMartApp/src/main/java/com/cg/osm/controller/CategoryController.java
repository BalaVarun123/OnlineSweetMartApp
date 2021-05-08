package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Category;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.model.CategoryDTO;
import com.cg.osm.service.CategoryServiceImpl;
import com.cg.osm.service.ICategoryService;

/*
 * Author : RAKSHA R
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is Category Controller
*/
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/osm")
public class CategoryController 
{
  @Autowired
  private ICategoryService service;
  
 static final Logger LOGGER =	LoggerFactory.getLogger(CategoryController.class);
  
  /************************************************************************************
	 * Method       : addCategory
	 * Description  : It is used to add Category into category Table
	 * @param       : Category Object
	 * @returns     : It returns CategoryDTO Object with details
	 * @PostMapping : It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By   : RAKSHA R
     * Created Date : 05-04-2021 
   *
	 ************************************************************************************/
 
  @PostMapping(value="/category/add")
  public ResponseEntity<Object> addCategory(@RequestBody Category category) 
  {
	  CategoryDTO category_add = null;
	  ResponseEntity<Object> response = null;
	  if (CategoryServiceImpl.validateCategoryId(category) && CategoryServiceImpl.validateName(category))
	  {
		  category_add=service.addCategory(category);
		  response =	new ResponseEntity(category_add,HttpStatus.ACCEPTED);
		  LOGGER.info("Category added");
	  }
	  else 
	  {
		response =	new ResponseEntity("Category insert failed",HttpStatus.BAD_REQUEST);
				
	  }
	  return response;  
  }
 
  /************************************************************************************
	 * Method         : updateCategory
	 * Description    : It is used to update category into category table
	 * @param         : Category Object
	 * @returns       : It returns CategoryDTO Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CategoryNotFoundException
	 * Created By     : RAKSHA R
     * Created Date   : 05-04-2021 
	 * 
	 ************************************************************************************/
  @PutMapping(value="/category/update")
  public ResponseEntity<Object> updateCategory( @RequestBody Category category) throws CategoryNotFoundException
  {
	  CategoryDTO category_update = null;
	  ResponseEntity<Object> response = null;
	  if (CategoryServiceImpl.validateCategoryId(category) && CategoryServiceImpl.validateName(category))
	  {
		  category_update=service.updateCategory(category);
		  response =	new ResponseEntity(category_update,HttpStatus.ACCEPTED);
		  LOGGER.info("Category updated");
	  }
	  else 
	  {
	    response =	new ResponseEntity("Category update failed",HttpStatus.BAD_REQUEST);
	  }
	  return response;  
  }
  

	/************************************************************************************
	 * Method          : cancelCategory
	 * Description     : It is used to remove category from category table
	 * @param id       : integer categoryId
	 * @returns        : It returns CategoryDTO Object with details
	 * @DeleteMapping  : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception      : CategoryNotFoundException
	 * Created By      : RAKSHA R
     * Created Date    : 05-04-2021 
	 * 
	 ************************************************************************************/
  @DeleteMapping(value="/category/delete/{id}")
  public ResponseEntity<Object> cancelCategory(@PathVariable("id") int categoryId) throws CategoryNotFoundException
  {
	  CategoryDTO category_delete = null;
	  ResponseEntity<Object> response = null;
	  if (!(categoryId<0))
	  {
		  category_delete=service.cancelCategory(categoryId);
		  response =	new ResponseEntity(category_delete,HttpStatus.ACCEPTED);
		  LOGGER.info("Category deleted");
	  }
	  else 
	  {
	    response =	new ResponseEntity("Category delete failed",HttpStatus.BAD_REQUEST);
	  }
	  return response;   
  }
  
  /************************************************************************************
	 * Method         : showAllCategories
	 * Description    : It is used to view all category details present in category table
	 * @returns       : It returns all List<CategoryDTO> Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By     : RAKSHA R
     * Created Date   : 05-04-2021 
	 * 
	 ************************************************************************************/
	
  @GetMapping(value="/category/get-all")
  public List<CategoryDTO> showAllCategories()
  {
	LOGGER.info("All categories shown");
	return service.showAllCategories();  
  }
 
  /************************************************************************************
	 * Method         : calculateTotalCost
	 * Description    : It is used to view the total cost
	 * @param         : integer categoryId
	 * @returns       : It returns a double price
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By     : RAKSHA R 
     * Created Date   : 05-04-2021 
	 * 
	 ************************************************************************************/
  @GetMapping(value="/category/total-cost/{id}")
  public double calculateTotalCost(@PathVariable("id") int categoryId) 
	{
	   LOGGER.info("Calculating total cost");
		if (categoryId < 0)
			return 0;
		else 
		    return service.calculateTotalCost(categoryId);
		
	}
  
 
  /************************************************************************************
	 * Method         : showCategoryById
	 * Description    : It is used to view category from category table
	 * @param         : integer categoryId
	 * @returns       : It returns CategoryDTO Object with details
	 * @GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception     : CategoryNotFoundException
	 * Created By     : RAKSHA R
     * Created Date   : 05-04-2021 
	 * 
	 ************************************************************************************/
  @GetMapping(value="/category/show/{id}")
  public CategoryDTO showCategory(@PathVariable("id") int categoryId) throws CategoryNotFoundException
  {
	  LOGGER.info("Category shown by id");
	  return service.showCategory(categoryId);
  }
}
