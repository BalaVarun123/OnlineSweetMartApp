package com.cg.osm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CustomerDTO;

@Repository
public interface IOrderBillRepository extends JpaRepository<OrderBill,Integer>{

	
	
}
