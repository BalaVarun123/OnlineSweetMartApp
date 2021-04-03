package com.cg.osm.service;

import java.util.List;

import com.cg.osm.customerdto.CustomerDTO;
import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;

public interface ICustomerService {

	public CustomerDTO addCustomer(Customer customer);
	public CustomerDTO updateCustomer(Customer customer) throws CustomerNotFoundException;
	public CustomerDTO cancelCustomer(int customerId) throws CustomerNotFoundException;
	public List<Customer> showAllCustomers();
	public List<Customer> showAllCustomers(int customerdId);	
	
}
