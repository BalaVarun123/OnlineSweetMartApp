package com.cg.osm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.OrderBill;



@Repository
public interface IOrderBillRepository extends JpaRepository<OrderBill,Integer>{

	
	
}
