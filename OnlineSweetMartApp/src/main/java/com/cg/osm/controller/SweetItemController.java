package com.cg.osm.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.osm.entity.Product;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.entity.SweetItemInput;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.SweetItemNotFoundException;
import com.cg.osm.model.ProductDTO;
import com.cg.osm.model.SweetItemDTO;
import com.cg.osm.model.SweetOrderDTO;
import com.cg.osm.service.ISweetItemService;
import com.cg.osm.service.SweetItemServiceImp;
import com.cg.osm.util.ProductUtils;
import com.cg.osm.util.SweetOrderUtils;


/* Author :ANNIE HEPZHIBHA K
 * Date : 05-04-2021
 * Description : This is SweetItem Controller
*/

@RestController
@RequestMapping("/api/osm")

public class SweetItemController{
	@Autowired
	ISweetItemService sweetItemService;
    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RestTemplate restTemplate;
	 
	    /* Method         : addSweetItem
		 * Description    : It is used to addSweetItem into SweetItem Table
		 * Input Parameter: SweetItem Object
		 * Return value   : It returns SweetItemDTO Object with details
		 * PostMapping    : It is used to handle the HTTP POST requests matched with given URI expression.
		 * RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
		 * Exception      : SweetItemNotFoundException
		*/
	@PostMapping(value = "/addSweetItem",consumes  = "application/json")
	public ResponseEntity<Object> addSweetItem(@RequestBody SweetItemInput sweetItem) {
		LOGGER.info("addSweetItem() is initiated");
		Object result;
		HttpStatus status;
		SweetItem sweetItem1 = new SweetItem();
		Product product = ProductUtils.convertToProduct(restTemplate.getForObject("http://localhost:9191/api/osm/product/show-by-id/"+sweetItem.getProductId(), ProductDTO.class));
		SweetOrder sweetOrder = SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+sweetItem.getSweetOrderId(), SweetOrderDTO.class));
		sweetItem1.setProduct(product);
		sweetItem1.setSweetOrder(sweetOrder);
		if (!SweetItemServiceImp.validateSweetItemProduct(sweetItem1)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem1)) {
			result = "Invalid SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem1)) {
			result = "Invalid orderItemId.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else {
			result = sweetItemService.addSweetItem(sweetItem1);
			status = HttpStatus.ACCEPTED;
			 LOGGER.info("Sweet Item Added Successfully");
		}
			
		return new ResponseEntity<Object>(result,status);
	}
	/*
	 * Method         : updateSweetItem 
	 * Description    : It is used to update SweetItem into SweetItem table
	 * Input Parameter: SweetItem Object
	 * Return value   : It returns SweetItemDTO Object with details
	 * @PutMapping    : It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @Exception     : SweetItemNotFoundException
	 */
	@PutMapping(value = "/updateSweetItem", produces = "application/json",consumes  = "application/json")
	public ResponseEntity<Object> updateSweetItem(@RequestBody SweetItemInput sweetItem) throws SweetItemNotFoundException {
		LOGGER.info("updateSweetyItem() is initiated");
		Object result;
		HttpStatus status;
		SweetItem sweetItem1 = new SweetItem();
		Product product = ProductUtils.convertToProduct(restTemplate.getForObject("http://localhost:9191/api/osm/product/show-by-id/"+sweetItem.getProductId(), ProductDTO.class));
		SweetOrder sweetOrder = SweetOrderUtils.convertToSweetOrder( restTemplate.getForObject("http://localhost:9191/api/osm/showAllSweetOrder/"+sweetItem.getSweetOrderId(), SweetOrderDTO.class));
		sweetItem1.setProduct(product);
		sweetItem1.setSweetOrder(sweetOrder);
		sweetItem1.setOrderItemId(sweetItem.getOrderItemId());
		if (!SweetItemServiceImp.validateSweetItemProduct(sweetItem1)) {
			result = "Invalid product.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else if (!SweetItemServiceImp.validateSweetItemSweetOrder(sweetItem1)) {
			result = "Invalid SweetOrder.";
			status = HttpStatus.BAD_REQUEST;
		}
		else if (!SweetItemServiceImp.validateSweetItemOrderItemId(sweetItem1)) {
			result = "Invalid orderItemId.";
			status = HttpStatus.BAD_REQUEST;
		}
		
		else {
			result = sweetItemService.updateSweetItem(sweetItem1);
			status = HttpStatus.ACCEPTED;
			LOGGER.info("Sweet Item Updated Successfully");
		}
		
		return new ResponseEntity<Object>(result,status);
		 
	}
	 /* Method         : cancelSweetItem
	 * Description     : It is used to remove SweetItem from SweetItem table
	 * Input Parameter : integer orderItemId
	 * Return value    : It returns SweetItemDTO Object with details
	 * @DeleteMapping  : It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @Exception      : SweetItemNotFoundException
	 */
	@DeleteMapping(value="/cancelSweetItem/{orderItemId}", produces = "application/json")
	
		 public ResponseEntity<Object> cancelSweetItem(@PathVariable("orderItemId") int orderItemId) throws SweetItemNotFoundException
		  {
		 LOGGER.info("CancelSweetItem() is initiated");
			  SweetItemDTO sweetItem_cancel = null;
			  ResponseEntity<Object> response = null;
			  if (!(orderItemId<0))
			  {
				  sweetItem_cancel=sweetItemService.cancelSweetItem(orderItemId);
				  response =new ResponseEntity<Object>(sweetItem_cancel,HttpStatus.ACCEPTED);
				  LOGGER.info("Sweet Item Cancelled");
			  }
			  else 
			  {
			    response =	new ResponseEntity<Object>("SweetItem Cancellation failed",HttpStatus.BAD_REQUEST);
			  }
			  return response;  
			  }

	/*
	 * Method        : showAllSweetItems()
	 * Description   : It is used to view allSweetItem details present in sweetItem table
	 * Return value  : It returns all List<SweetItemDTO> Object with details
	 * GetMapping    : It is used to handle the HTTP GET requests matched with given URI expression.
	 * RequestBody   : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception     : SweetItemNotFoundException
	 */

  @GetMapping(value="/showAllSweetItems", produces = "application/json")
	public ResponseEntity<List<SweetItemDTO>> showAllSweetItem() {
		LOGGER.info("showAllSweetItems() is initiated");
		List<SweetItemDTO> showAllSweetItems = sweetItemService.showAllSweetItems();
		LOGGER.info("ShowAllSweetItems() has executed");
		return new ResponseEntity<List<SweetItemDTO>>(showAllSweetItems, HttpStatus.ACCEPTED);
		
	}
     /* Method        :showSweetItemByOrderItemId()
	 * Description    : It is used to view SweetItem from sweetItem table
	 * Input Parameter: integer orderItemId()
	 * Return Value   : It returns SweetItemDTO Object with details
	 * GetMapping     : It is used to handle the HTTP GET requests matched with given URI expression.
	 * RequestBody    : It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Exception      : SweetItemNotFoundException
	 */
	
	@GetMapping(value="/showSweetItem/{orderItemId}", produces = "application/json")
	public SweetItemDTO showSweetItem(@PathVariable("orderItemId") int orderItemItemId) throws SweetItemNotFoundException{
		LOGGER.info("showSweetItemByOrderItemId() is initiated");
		LOGGER.info("showSweetItemByOrderItemId() has executed");
		return sweetItemService.showSweetItem(orderItemItemId);
	
	}
	
}
