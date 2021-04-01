package com.cg.osm.service;

import java.util.List;
import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer Customer);
	public Customer updateCustomer(Customer Customer) throws CustomerNotFoundException;
	public Customer cancelCustomer(int CustomerId) throws CustomerNotFoundException;
	public List<Customer> showAllCustomers();
	public List<Customer> showAllCustomers(int CustomerdId);	
	
}
