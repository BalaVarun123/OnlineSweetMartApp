package com.cg.osm.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.repository.ISweetOrderRepository;
import com.cg.osm.util.SweetOrderUtils;

@Service
/*
 * Author      : BALASUBRAMANIAN S
 * Version     : 1.0
 * Date        : 06-04-2021
 * Description : Implementation for ISweetOrderService
*/
public class SweetOrderServiceImpl implements ISweetOrderService {
	@Autowired
	ISweetOrderRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImp.class);
	private static final String MESSAGE_INVALID_ID = "Invalid sweetOrderId.";
	/*
	 * Description     : This method adds a new SweetOrder record.
	 * Input Parameter : SweetOrder Object 
	 * Return Value    : SweetOrderDTO Object 
	*/
	@Override
	public SweetOrderDTO addSweetOrder(SweetOrder sweetOrder) {
		LOGGER.info("addSweetOrder service method is initiated.");
		SweetOrderDTO sweetOrderDTO;
		if (sweetOrder ==null) {
			LOGGER.error("sweetOrder is null.");
			sweetOrderDTO = null;
		}
		else {
			sweetOrderDTO = SweetOrderUtils.convertToSweetOrderDto(repository.save(sweetOrder));
		}
		LOGGER.info("addSweetOrder service method is terminated.");
		return sweetOrderDTO;
	}
	
	
	
	/*
	 * Description     : This method updates an existing SweetOrder record.
	 * Input Parameter : SweetOrder Object 
	 * Return Value    : SweetOrderDTO Object 
	 * Exception       : SweetOrderNotFoundException
	*/
	@Override
	public SweetOrderDTO updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException {
		LOGGER.info("updateSweetOrder service method is initiated.");
		SweetOrderDTO sweetOrderDTO;
		if (sweetOrder == null) {
			LOGGER.error("sweetOrder is null.");
			sweetOrderDTO = null;
		} else {
			if (repository.existsById(sweetOrder.getSweetOrderId())) {
				sweetOrderDTO = SweetOrderUtils.convertToSweetOrderDto(repository.save(sweetOrder));
			} else {
				LOGGER.error(MESSAGE_INVALID_ID);
				throw new SweetOrderNotFoundException(MESSAGE_INVALID_ID);
			}
		}
		LOGGER.info("updateSweetOrder service method is terminated.");
		return sweetOrderDTO;
	}

	
	/*
	 * Description     : This method removes an existing SweetOrder record.
	 * Input Parameter : int sweetOrderId 
	 * Return Value    : SweetOrderDTO Object 
	 * Exception       : SweetOrderNotFoundException
	*/
	@Override
	public SweetOrderDTO cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		LOGGER.info("cancelSweetOrder service method is initiated.");
		SweetOrderDTO sweetOrderDTO;
		SweetOrder sweetOrder = repository.findById(sweetOrderId).orElse(null);
		if (sweetOrder != null) {
			repository.delete(sweetOrder);
			sweetOrderDTO = SweetOrderUtils.convertToSweetOrderDto(sweetOrder);
		} else {
			LOGGER.error(MESSAGE_INVALID_ID);
			throw new SweetOrderNotFoundException(MESSAGE_INVALID_ID);
		}
		LOGGER.info("cancelSweetOrder service method is terminated.");
		return sweetOrderDTO;
	}
	
	
	
	/*
	 * Description     : This retrieves a single SweetOrder instance by sweetOrderId.
	 * Input Parameter : int sweetOrderId
	 * Return Value    : SweetOrderDTO Object 
	 * Exception       : SweetOrderNotFoundException
	*/
	@Override
	public SweetOrderDTO showSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		LOGGER.info("showSweetOrder service method is initiated.");
		SweetOrderDTO sweetOrderDTO;
		SweetOrder sweetOrder = repository.findById(sweetOrderId).orElse(null);
		if (sweetOrder != null) {
			sweetOrderDTO = SweetOrderUtils.convertToSweetOrderDto(sweetOrder);
		} else {
			LOGGER.error(MESSAGE_INVALID_ID);
			throw new SweetOrderNotFoundException(MESSAGE_INVALID_ID);
		}
		LOGGER.info("showSweetOrder service method is terminated.");
		return sweetOrderDTO;
	}

	/*
	 * Description     : This method retrieves all SweetOrder records.
	 * Return Value    : List<SweetOrderDTO> Object 
	*/
	@Override
	public List<SweetOrderDTO> showAllSweetOrders() {
		LOGGER.info("showAllSweetOrders service method is executed.");
		return SweetOrderUtils.convertToSweetOrderDtoList(repository.findAll());
	}
	
	
	/*
	 * Description     : This method returns the total cost of all sweet items in this order.
	 * Input Parameter : int sweetOrderId
	 * Return Value    : double total cost of sweet items.
	 * Exception       : SweetOrderNotFoundException
	*/
	
	@Override
	public double calculateTotalCost(int sweetOrderId) throws SweetOrderNotFoundException {
		LOGGER.info("calculateTotalCost service method is initiated.");
		double total = 0;
		SweetOrder sweetOrder = repository.findById(sweetOrderId).orElse(null);
		if (sweetOrder != null) {
			List<SweetItem> sweetItems = sweetOrder.getListItems();
			for (SweetItem item : sweetItems) {
				total += item.getProduct().getPrice();
			}
		} else {
			LOGGER.error(MESSAGE_INVALID_ID);
			throw new SweetOrderNotFoundException(MESSAGE_INVALID_ID);
		}
		LOGGER.info("calculateTotalCost service method is terminated.");
		return total;
	}
	
	
	//Validation for sweetOrderId field of a SweetOrder instance.
	public static boolean validateSweetOrderId(SweetOrder sweetOrder) {
		boolean result;
		if (sweetOrder == null) {
			result = false;
		} else {
			Integer id = sweetOrder.getSweetOrderId();
			if (id != null && id >= 0) {
				result = true;
			} else {
				result = false;
			}
		}
		LOGGER.info("validateSweetOrderId is executed.");
		return result;
	}

	//Validation for user field of a SweetOrder instance.
	public static boolean validateUser(SweetOrder sweetOrder) {
		LOGGER.info("validateUser is executed.");
		return (sweetOrder != null && sweetOrder.getUser() != null);
	}

	//Validation for listItems field of a SweetOrder instance.
	public static boolean validateListItems(SweetOrder sweetOrder) {
		boolean result = false;
		if (sweetOrder != null) {
			List<SweetItem> items = sweetOrder.getListItems();
			if (items != null ) {
				result = true;
			}
		}
		LOGGER.info("validateListItems is executed.");
		return result;
	}

	
	
	//Validation for createdDate field of a SweetOrder instance.
	public static boolean validateCreatedDate(SweetOrder sweetOrder) {
		boolean result = false;
		if (sweetOrder != null) {
			LocalDate date = sweetOrder.getCreatedDate();
			if (date != null && date.isBefore(LocalDate.now())) {
				result = true;
			}
		}
		LOGGER.info("validateCreatedDate is executed.");
		return result;
	}


}
