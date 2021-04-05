package com.cg.osm.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.util.CustomerUtils;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.repository.ICustomerRepository;
import com.cg.osm.entity.SweetOrder;

@Service
public class CustomerServiceImp implements ICustomerService{
    @Autowired
    public ICustomerRepository repository;
	@Override
	public CustomerDTO addCustomer(Customer customer) {
		if(customer==null)
			return null;
		return CustomerUtils.convertToCustomerDto(repository.save(customer));
	}
	
	@Override
	public CustomerDTO updateCustomer(Customer customer)throws CustomerNotFoundException {
	if(customer==null)
		{
		throw new CustomerNotFoundException("Invalid id");
	}
	else {
		return CustomerUtils.convertToCustomerDto(repository.save(customer));
	}
		
	}

	@Override
	public CustomerDTO cancelCustomer(int customerId) throws CustomerNotFoundException 
	{
		Customer existingCustomer = repository.findById(customerId).orElse(null);
		if (existingCustomer == null) {
			throw new CustomerNotFoundException("Invalid id");
		}
		else {
			repository.delete(existingCustomer);
			return CustomerUtils.convertToCustomerDto(existingCustomer);
		}
	}
	
	@Override
	public List<CustomerDTO> showAllCustomers() {
		List<Customer> listCustomers = repository.findAll();
		return CustomerUtils.convertToCustomerDtoList(listCustomers);
	}

	public List<CustomerDTO> showAllCustomers(int customerId) {
		List <Customer> listCustomers = new ArrayList<Customer>();
		Optional<Customer> customerOptional = repository.findById(customerId);
		if (customerOptional.isPresent())
			listCustomers.add(customerOptional.get());
		return CustomerUtils.convertToCustomerDtoList(listCustomers);
	}
	
	public static boolean validateCust(Customer customer) {
		boolean flag;
		if (customer == null  ) {
			flag = false;
		}
		else if (!(validateCustomerUserId(customer) && validateCustomerUsername(customer) &&  validateCustomerSetSweetOrders(customer) && validateCustomerSweetItem(customer))) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	

	public static boolean validateCustomerUsername(Customer customer) {
		boolean flag = true;
		if (customer.getUsername() == null)
			flag = false;
		return flag;
	}

	public static boolean validateCustomerUserId(Customer customer) {
		boolean flag = true;
		Long id = customer.getUserId();
		CustomerServiceImp service1 = new CustomerServiceImp();
		if (id == null|| id < 0)
			flag = false;
		return flag;
	}
	

	public static boolean validateCustomerSetSweetOrders(Customer customer) {
		boolean flag = true;
		Set<SweetOrder> setSweetOrder = customer.getSweetOrders();
		if (setSweetOrder == null || setSweetOrder.size() == 0)
			flag = false;
		return flag;
	}
		public static boolean validateCustomerSweetItem(Customer customer) {
		boolean flag = true;
		List<SweetItem> listSweetItem = customer.getSweetItems();
		if (listSweetItem == null || listSweetItem.size() == 0)
			flag = false;
		return flag;
	}

}