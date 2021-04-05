package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value="/api/osm")
public class CategoryController 
{
  @Autowired
  ICategoryService service;
  
  final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
 
  @PostMapping(value="/category/add")
  public ResponseEntity<Object> addCategory(@RequestBody Category category) throws CategoryNotFoundException
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
 
  @GetMapping(value="/category/get-all")
  public List<CategoryDTO> showAllCategorys()
  {
	return service.showAllCategorys();  
  }
 
  @GetMapping(value="/category/total-cost/{id}")
  public double calculateTotalCost(@PathVariable("id") int categoryId) 
	{
		if (!(categoryId<0))
		 return service.calculateTotalCost(categoryId);
		else 
			return 0;
		
	}
}
