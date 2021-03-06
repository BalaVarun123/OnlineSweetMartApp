package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {


@Query("SELECT sum(item.product.price) FROM SweetItem item GROUP BY item.product.category.categoryId HAVING item.product.category.categoryId = ?1")
	public double calculateTotalCost(int categoryId); 
	

}
