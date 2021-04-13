package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Product;
/*
 * Author      : KANAKASAI T
 * Version     : 1.0
 * Date        : 04-04-2021
 * Description : This is a repository interface
*/
@Repository

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
