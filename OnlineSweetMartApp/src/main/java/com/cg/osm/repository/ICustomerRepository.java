package com.cg.osm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.osm.customerdto.CustomerDTO;
import com.cg.osm.entity.Customer;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

	Customer existsById(Long id);

	
	/* public CustomerDTO addCustomer(Customer Customer);
	public CustomerDTO updateCustomer(Customer Customer) throws CustomerNotFoundException;
	public CustomerDTO cancelCustomer(int CustomerId) throws CustomerNotFoundException;
	public List<Customer> showAllCustomers();
	public List<Customer> showAllCustomers(int CustomerdId);
	public CustomerDTO save(Customer customer);*/
	
}
