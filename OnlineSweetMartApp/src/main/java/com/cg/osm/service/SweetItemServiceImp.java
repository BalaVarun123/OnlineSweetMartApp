package com.cg.osm.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.repository.ISweetItemRepository;
import com.cg.osm.util.SweetItemUtils;
import com.cg.osm.service.ISweetItemService;
/*
 * Author : ANNIE HEPZHIBHA K
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is SweetItem Service Layer
*/
@Service
public class SweetItemServiceImp implements ISweetItemService  {

		 final static Logger LOGGER = LoggerFactory.getLogger(CartServiceImp.class);

		 @Autowired 
		 ISweetItemRepository repo;
		 /*
		 * Description : This method Adds new SweetItem
		 * Input Param : SweetItem Object 
		 * Return Value : SweetItem Object 
		*/

			
		@Override
		public SweetItemDTO addSweetItem(SweetItem sweetItem) {
			LOGGER.info("addSweetItem() service is initiated");
			if (sweetItem == null)
				return  null;
			LOGGER.info("addSweetItem() service has executed");
			return  SweetItemUtils. convertToSweetItemDto( repo.save(sweetItem));
		}
		/*
		 * Description : This method Updates existing SweetItem
		 * Input Param : SweetItem Object 
		 * Return Value : SweetItem Object 
		 * Exception : SweetItemNotFound Exception id raised when OrderItemId is invalid
		 */

		
		@Override
		public SweetItemDTO updateSweetItem(SweetItem sweetItem) throws SweetItemNotFoundException {
			LOGGER.info("updateSweetItem() service is initiated");
			if (sweetItem == null)
				return  null;
			SweetItem existingSweetItem = repo.findById(sweetItem.getOrderItemId()).orElse(null);
			if (existingSweetItem == null) {
				throw new SweetItemNotFoundException("invalid ID");
			}
			else {
				LOGGER.info("updateSweetItem() service has executed");
				return SweetItemUtils.convertToSweetItemDto(repo.save(sweetItem));
			}
		}
		
		/*
		 * Description : This method Deletes existing SweetItem
		 * Input Param : int 
		 * Return Value : SweetItemDTO Object 
		 * Exception : SweetItemNotFound Exception id raised when OrderItemId is invalid
		 */
		
		@Override
		public SweetItemDTO cancelSweetItem(int ordertItemId) throws SweetItemNotFoundException {
			LOGGER.info("cancelSweetItem() service is initiated");
			SweetItem existingSweetItem = repo.findById(ordertItemId).orElse(null);
			if (existingSweetItem == null) {
				throw new SweetItemNotFoundException("Invalid ID");
			}
			else {
				repo.delete(existingSweetItem);
				LOGGER.info("cancelSweetItem() service has executed");
				return SweetItemUtils.convertToSweetItemDto(existingSweetItem);
			}
		}
		/*
		 * Description : This method Shows existing Cart
		 *  Input Param : int
		 *  Return Value : SweetItemDTO Object 
		 * Exception : SweetItemNotFound Exception id raised when OrderItemId is invalid
		 */

		@Override
		public List<SweetItemDTO> showAllSweetItems() {
			List<SweetItem> listSweetItems = repo.findAll();
			return SweetItemUtils.convertToSweetItemDtoList(listSweetItems);
		}	
		
		
		public SweetItemDTO showAllSweetItems(int orderItemItemId) throws SweetItemNotFoundException{
			LOGGER.info("showSweetItem() service is initiated");
			SweetItem existingSweetItem = repo.findById(orderItemItemId).orElse(null);
			if (existingSweetItem == null) {
				throw new SweetItemNotFoundException("Invalid ID");
			}
			else {
				LOGGER.info("showSweetItem() service has executed");
				return SweetItemUtils.convertToSweetItemDto(existingSweetItem);
			}
		}
		
	 
	 public static boolean validateSweetItem(SweetItem sweetItem) {
			boolean flag;
			if (sweetItem == null  ) {
				flag = false;
			}
			else if (!(validateSweetItemProduct(sweetItem) && validateSweetItemOrderItemId(sweetItem) && validateSweetItemSweetOrder(sweetItem))) {
				flag = false;
			}
			else {
				flag = true;
			}
			return flag;
		}
	public static boolean validateSweetItemProduct(SweetItem sweetItem) {
		boolean flag = true;
		Product product = sweetItem.getProduct();
		if (product == null)
			flag = false;
		return flag;
	}

	public static boolean validateSweetItemSweetOrder(SweetItem sweetItem) {
		boolean flag = true;
		SweetOrder sweetOrder = sweetItem.getSweetOrder();
		if (sweetOrder == null)
			flag = false;
		return flag;
	}

	public static boolean validateSweetItemOrderItemId(SweetItem sweetItem) {
		boolean flag = true;
		Integer id = sweetItem.getOrderItemId();
		//SweetItemServiceImp service = new SweetItemServiceImp();
		if (id == null)
			flag = false;
		return flag;
	}
	}
		


}
