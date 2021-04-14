package com.cg.osm.util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cg.osm.model.CustomerDTO;
import com.cg.osm.entity.Customer;
@Component
public class CustomerUtils {
	public static List<CustomerDTO> convertToCustomerDtoList(List<Customer> list){
		List<CustomerDTO> dtolist = new ArrayList<CustomerDTO>();
		for(Customer customer : list) 
			dtolist.add(convertToCustomerDto(customer));
		return dtolist;
	}
	
	public static  CustomerDTO convertToCustomerDto(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setUserId(customer.getUserId());
		customerDto.setUsername(customer.getUsername());
		customerDto.setSweetOrders(customer.getSweetOrders());
		customerDto.setSweetItems(customer.getSweetItems());
		customerDto.setCart(customer.getCart());
		return customerDto;
	}
	
	
	
	public static List<Customer> convertToCustomerList(List<CustomerDTO> dtolist){
		List<Customer> list = new ArrayList<Customer>();
		for(CustomerDTO customerDTO : dtolist) 
			list.add(convertToCustomer(customerDTO));
		return list;
	}
	
	public static  Customer convertToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setUserId(customerDTO.getUserId());
		customer.setUsername(customerDTO.getUsername());
		customer.setSweetOrders(customerDTO.getSweetOrders());
		customer.setSweetItems(customerDTO.getSweetItems());
		customer.setCart(customerDTO.getCart());
		return customer;
	}
	
	

}
