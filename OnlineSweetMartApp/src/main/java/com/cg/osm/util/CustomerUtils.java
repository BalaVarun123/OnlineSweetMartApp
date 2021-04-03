package com.cg.osm.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.Customer;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.model.CustomerDTO;
@Component
public class CustomerUtils {
	public static List<CustomerDTO> convertToCustomerDtoList(List<Customer> list){
		List<CustomerDTO> dtolist = new ArrayList<com.cg.osm.model.CustomerDTO>();
		for(Customer customer : list) 
			dtolist.add(convertToCustomerDto(customer));
		return dtolist;
	}
	
	public static  CustomerDTO convertToCustomerDto(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO(null, null, null, null, null);
		customerDto.setUserId(customer.getUserId());
		customerDto.setUsername(customer.getUsername());
		customerDto.setSweetOrders(customer.getSweetOrders());
		customerDto.setSweetItems(customer.getSweetItems());
		return customerDto;
	}
	
	
	public static List<Customer> convertToCustomerList(List<CustomerDTO> dtoList){
		List<Customer> list = new ArrayList<Customer>();
		for(CustomerDTO customerDTO : dtoList) 
			list.add(convertToCustomer(customerDTO));
		return list;
	}
	
	
	public static Customer convertToCustomer(CustomerDTO customerDto) {
		Customer customer = new Customer();
		customer.setUserId(customerDto.getUserId());
		customer.setUsername (customerDto.getUsername());
		customer.setSweetOrders(customerDto.getSweetOrders());
        customer.setSweetItems(customerDto.getSweetItems());
		return customer;
	}

	public static CustomerDTO convertToOrderBillDto(Customer save) {
		// TODO Auto-generated method stub
		return null;
	}
}

