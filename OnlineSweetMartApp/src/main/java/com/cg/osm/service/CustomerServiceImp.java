package com.cg.osm.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.osm.util.CustomerUtils;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.repository.ICustomerRepository;
import com.cg.osm.entity.SweetOrder;

/*
 * Author : JEEVETHA
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is CUSTOMER Service Layer
*/

@Service
public class CustomerServiceImp implements ICustomerService{
    @Autowired
    public ICustomerRepository repository;
    
    /*
	 * Description : This method Adds new Customer
	 * Input Param : Customer Object 
	 * Return Value : CustomerDTO Object 
	*/

    
	@Override
	public CustomerDTO addCustomer(Customer customer) {
		if(customer==null)
			return null;
		return CustomerUtils.convertToCustomerDto(repository.save(customer));
	}
	
	/*
	 * Description : This method updates Customer
	 * Input Param : Customer Object 
	 * Return Value : CustomerDTO Object 
	 * Exception : Customer Not Found Exception
	*/

	
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
	
	/*
	 * Description : This method cancels Customer
	 * Input Param : int 
	 * Return Value : CustomerDTO Object 
	 * Exception : Customer Not Found Exception
	*/


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
	
	/*
	 * Description : This method displays existing Customer
	 * Return Value : CustomerDTO Object 
	*/

	
	@Override
	public List<CustomerDTO> showAllCustomers() {
		List<Customer> listCustomers = repository.findAll();
		return CustomerUtils.convertToCustomerDtoList(listCustomers);
	}
	
	/*
	 * Description : This method displays existing Customer
	 * Input Param : int 
	 * Return Value : CustomerDTO Object 
	*/


	public List<CustomerDTO> showAllCustomers(int customerId) {
		List <Customer> listCustomers = new ArrayList<Customer>();
		Optional<Customer> customerOptional = repository.findById(customerId);
		if (customerOptional.isPresent())
			listCustomers.add(customerOptional.get());
		return CustomerUtils.convertToCustomerDtoList(listCustomers);
		
	}
	
	//Validations
	
	public static boolean validateCustomer(Customer customer) throws CustomerNotFoundException {
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
	
	//username

	public static boolean validateCustomerUsername(Customer customer) throws CustomerNotFoundException {
		boolean flag = true;
		if(customer.getUsername().matches("^[a-zA-Z0-9 ]+$") && customer.getUsername().length()>2)
			flag=true;
		else 
			throw new CustomerNotFoundException("Enter a valid name");
		return flag; 
			
	}

	//userId
	
	public static boolean validateCustomerUserId(Customer customer) throws CustomerNotFoundException {
		boolean flag = true;
		if(customer.getUserId()>0)
			flag=true;
		else
			throw new CustomerNotFoundException("Enter valid user id");
		return flag;
	}
	
	//Validating sweet order
	
	public static boolean validateCustomerSetSweetOrders(Customer customer) {
		boolean flag = true;
		Set<SweetOrder> setSweetOrder = customer.getSweetOrders();
		if (setSweetOrder == null || setSweetOrder.size() == 0)
			flag = false;
		return flag;
	}
	
	//validating sweetItem	
	
		public static boolean validateCustomerSweetItem(Customer customer) {
		boolean flag = true;
		List<SweetItem> listSweetItem = customer.getSweetItems();
		if (listSweetItem == null || listSweetItem.size() == 0)
			flag = false;
		return flag;
	}
		//Validating Cart
		
		public static boolean validateCartId(Cart cart) throws CartNotFoundException
		{
			boolean flag=false;
			if(cart.getCartId()>0)
				flag=true;
			else
				throw new CartNotFoundException("Not a valid cart id");
			return flag;
		}

}