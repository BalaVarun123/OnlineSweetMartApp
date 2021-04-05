package com.cg.osm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Customer;



@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer>{

	Optional<Customer> existsById(Long userId);
	
	
	
}
