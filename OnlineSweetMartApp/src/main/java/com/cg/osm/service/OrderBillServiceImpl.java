package com.cg.osm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.OrderBillNotFoundException;
import com.cg.osm.model.OrderBillDTO;
import com.cg.osm.repository.IOrderBillRepository;
import com.cg.osm.util.OrderBillUtils;


/*
 * Author      : BALASUBRAMANIAN S
 * Version     : 1.0
 * Date        : 05-04-2021
 * Description : Implementation for IOrderBillService
*/

@Service
public class OrderBillServiceImpl implements IOrderBillService{

	@Autowired
	public IOrderBillRepository repository;
	
	final static Logger LOGGER = LoggerFactory.getLogger(OrderBillServiceImpl.class);
	
	
	/*
	 * Description     : Service method for adding a new OrderBill record.
	 * Input Parameter : OrderBill Object 
	 * Return Value    : OrderBillDTO Object 
	*/
	
	@Override
	public OrderBillDTO addOrderBill(OrderBill orderBill) {
		LOGGER.info("addOrderBill service method is initiated.");
		OrderBillDTO orderBillDTO;
		
		if (orderBill == null) {
			LOGGER.error("orderBill is null.");
			orderBillDTO = null;
		}
		else
			orderBillDTO = OrderBillUtils.convertToOrderBillDto(repository.save(orderBill));
		
		LOGGER.info("addOrderBill service method is terminated.");
		return orderBillDTO;
	}

	
	/*
	 * Description     : Service method for updating an existing OrderBill record.
	 * Input Parameter : OrderBill Object 
	 * Return Value    : OrderBillDTO Object 
	 * Exception       : OrderBillNotFoundException
	 */
	
	
	@Override
	public OrderBillDTO updateOrderBill(OrderBill orderBill) throws OrderBillNotFoundException {
		LOGGER.info("updateOrderBill service method is initiated.");
		OrderBillDTO orderBillDTO;
		if (orderBill == null) {
			LOGGER.error("orderBill is null.");
			orderBillDTO =  null;
		}
		else {
			OrderBill existingOrderBill = repository.findById(orderBill.getOrderBillId()).orElse(null);
			if (existingOrderBill == null) {
				LOGGER.error("Invalid orderBillId.");
				throw new OrderBillNotFoundException("Invalid orderBillId.");
			}
			else {
				orderBillDTO = OrderBillUtils.convertToOrderBillDto(repository.save(orderBill));
			}
		}
		LOGGER.info("updateOrderBill service method is terminated.");
		return orderBillDTO;

	}

	
	/*
	 * Description     : This method deletes existing OrderBill record.
	 * Input Parameter : int orderBillId
	 * Return Value    : OrderBillDTO Object 
	 * Exception       : OrderBillNotFoundException
	 */
	
	@Override
	public OrderBillDTO cancelOrderBill(int orderBillId) throws OrderBillNotFoundException {
		LOGGER.info("cancelOrderBill service method is initiated.");
		OrderBillDTO orderBillDTO;
		OrderBill existingOrderBill = repository.findById(orderBillId).orElse(null);
		if (existingOrderBill == null) {
			LOGGER.error("Invalid orderBillId.");
			throw new OrderBillNotFoundException("Invalid orderBillId.");
		}
		else {
			repository.delete(existingOrderBill);
			orderBillDTO = OrderBillUtils.convertToOrderBillDto(existingOrderBill);
		}
		LOGGER.info("cancelOrderBill service method is terminated.");
		return orderBillDTO;
	}

	/*
	 * Description      : This method is used to get all OrderBill records.
	 * Return Value     : List<OrderBillDTO> Object 
	 */
	
	@Override
	public List<OrderBillDTO> showAllOrderBills() {
		List<OrderBill> listOrderBills = repository.findAll();
		LOGGER.info("showAllOrderBills service method is executed.");
		return OrderBillUtils.convertToOrderBillDtoList(listOrderBills);
	}

	
	/*
	 * Description     : This method is used to get OrderBill details by orderBillId.
	 * Input Parameter : int orderBillId
	 * Return Value    : List<OrderBillDTO> Object
	 */
	@Override
	public List<OrderBillDTO> showAllOrderBills(int orderBillId) {
		LOGGER.info("showAllOrderBills(int orderBillId) service method is initiated.");
		List<OrderBill> listOrderBills = new ArrayList<OrderBill>();
		Optional<OrderBill> orderBIllOptional = repository.findById(orderBillId);
		List<OrderBillDTO> listDTO;
		if (orderBIllOptional.isPresent())
			listOrderBills.add(orderBIllOptional.get());
		listDTO = OrderBillUtils.convertToOrderBillDtoList(listOrderBills);
		LOGGER.info("showAllOrderBills(int orderBillId) service method is terminated.");
		return listDTO;
	}
	
	

	
	
	//Validation for createdDate field of OrderBill.
	public static boolean validateOrderBillCreatedDate(OrderBill orderBill) {
		boolean flag = true;
		if (orderBill == null || orderBill.getCreatedDate() == null || orderBill.getCreatedDate().isAfter(LocalDate.now()))
			flag = false;
		LOGGER.info("validateOrderBillCreatedDate is executed.");
		return flag;
	}
	

	//Validation for listSweetOrder field of OrderBill.
	public static boolean validateOrderBillListSweetOrder(OrderBill orderBill) {
		boolean flag = true;
		if (orderBill== null) {
			flag = false;
		}
		else {
			List<SweetOrder> listSweetOrder = orderBill.getListSweetOrder();
			if (listSweetOrder == null || listSweetOrder.size() == 0)
				flag = false;
		}
		LOGGER.info("validateOrderBillListSweetOrder is executed.");
		return flag;
	}
	
	
	//Validation for orderBillId field of OrderBill.
	public static boolean validateOrderBillId(OrderBill orderBill) {
		boolean flag = true;
		if (orderBill == null) {
			flag = false;
		}
		else {
			Integer id = orderBill.getOrderBillId();
			if (id == null|| id < 0 )
				flag = false;
		}
		LOGGER.info("validateOrderBillId is executed.");
		return flag;
	}
	
	
	//Validation for totalCost field of OrderBill.
	public static boolean validateOrderBillTotalCost(OrderBill orderBill) {
		boolean flag = true;
		if (orderBill == null) {
			flag = false;
		}
		else {
			float totalCost = orderBill.getTotalCost();
			if ( totalCost < 0 || Float.isNaN(totalCost))
				flag = false;
		}
		LOGGER.info("validateOrderBillTotalCost is executed.");
		return flag;
	}

}
