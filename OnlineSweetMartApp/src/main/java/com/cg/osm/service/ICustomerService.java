package com.cg.osm.service;

import java.util.List;

import com.cg.osm.model.CartDTO;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;

public interface ICustomerService {

	public CustomerDTO addCustomer(Customer customer);
	public CustomerDTO updateCustomer(Customer customer) throws CustomerNotFoundException;
	public CustomerDTO cancelCustomer(int customerId) throws CustomerNotFoundException;
	public List<CustomerDTO> showAllCustomers();

	public List<CustomerDTO> showAllCustomers(int customerdId);	


	
	}

