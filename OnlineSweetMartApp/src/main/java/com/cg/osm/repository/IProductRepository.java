package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Product;
@Repository

public interface IProductRepository extends JpaRepository<Product,Integer>{

}
