package com.cg.osm.service;

import java.util.List;
import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.model.CustomerDTO;

public interface ICustomerService {

	public CustomerDTO addCustomer(Customer Customer);
	public CustomerDTO updateCustomer(Customer Customer) throws CustomerNotFoundException;
	public CustomerDTO cancelCustomer(int CustomerId) throws CustomerNotFoundException;
	public List<CustomerDTO> showAllCustomers();
	public List<CustomerDTO> showAllCustomers(int CustomerdId);	
	
}
