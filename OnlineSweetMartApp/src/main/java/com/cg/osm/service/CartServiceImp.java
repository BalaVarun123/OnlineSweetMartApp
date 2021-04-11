package com.cg.osm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.repository.ICartRepository;
import com.cg.osm.util.CartUtils;
import org.springframework.stereotype.Service;

/*
 * Author      : UJJWAL SINGH A
 * Version     : 1.0
 * Date        : 05-04-2021
 * Description : This is Cart Service Layer
*/



@Service
public class CartServiceImp implements ICartService {

	final static Logger logger = LoggerFactory.getLogger(CartServiceImp.class);

	@Autowired
	private ICartRepository cartRepo;

	static String cartNotFound = "No Cart found with given ID";
	
	
	
	
	/*
	 * Description     : This method Adds new Cart
	 * Input Parameter : Cart Object 
	 * Return Value    : CartDTO Object 
	 * Exception       : CartNotFoundException
	*/

	@Override
	public CartDTO addCart(Cart cart) throws CartNotFoundException {
		logger.info("addCart() service is initiated");
		Cart cartEntity;
		if (cart == null)
			cartEntity = null;
		
		else
		{   
			
			validateCart(cart);
			cartEntity = cartRepo.save(cart);
			
		}
		
		logger.info("addCart() service has executed");
		 return CartUtils.convertToCartDto(cartEntity);
	}
	
	
	

	/*
	 * Description     : This method Updates existing Cart
	 * Input Parameter : Cart Object 
	 * Return Value    : CartDTO Object 
	 * Exception       : CartNotFoundException
	 */

	@Override
	public CartDTO updateCart(Cart cart) throws CartNotFoundException {
		logger.info("updateCart() service is initiated");
		Cart cartEntity;
        Cart existCart = cartRepo.findById(cart.getCartId()).orElse(null);

		if (existCart == null) {

			throw new CartNotFoundException(cartNotFound);
		} else
		{
			validateCart(cart);
			cartEntity = cartRepo.save(cart);
		}

		logger.info("updateCart() service has executed");
        return CartUtils.convertToCartDto(cartEntity);

	}
	
	
	

	/*
	 * Description     : This method Deletes existing Cart
	 * Input Parameter : integer 
	 * Return Value    : CartDTO Object 
	 * Exception       : CartNotFoundException
	 */
	
	@Override
	public CartDTO cancelCart(int cartId) throws CartNotFoundException {
		logger.info("cancelCart() service is initiated");

		Cart existCart = cartRepo.findById(cartId).orElse(null);

		if (existCart == null)
			throw new CartNotFoundException(cartNotFound);
		else

			cartRepo.delete(existCart);

		logger.info("cancelCart() service has executed");
        return CartUtils.convertToCartDto(existCart);
	}

	
	
	
	/*
	 * Description      : This method Shows existing Cart
	 * Input Parameter  : integer
	 * Return Value     : CartDTO Object 
	 * Exception        : CartNotFoundException
	 */

	@Override
	public CartDTO showCartById(int cartId) throws CartNotFoundException {
		logger.info("showCartById() service is initiated");
		Cart existCart = cartRepo.findById(cartId).orElse(null);
		if (existCart == null)
			throw new CartNotFoundException(cartNotFound);

		logger.info("showCartById() service has executed");
        return CartUtils.convertToCartDto(existCart);
	}
	
	
	
	
	
	/*
	 * Description     : This method Shows existing Cart
	 * Return Value    : CartDTO Object 
	 */

	@Override
	public List<CartDTO> showAllCarts() {
		logger.info("showAllCarts() service is initiated");
		List<Cart> cartList = cartRepo.findAll();
		logger.info("showAllCarts() service has executed");
		return CartUtils.convertToCartDtoList(cartList);
	}
	
	
	
	

	// VALIDATIONS
	public static boolean validateCart(Cart cart) throws CartNotFoundException {
		logger.info("validateCart() is initiated");
		boolean flag = false;
		if (cart == null) {
			logger.error("Cart details cannot be blank");
			throw new CartNotFoundException("Cart details cannot be blank"); 
			}
		else { 
			validateTotalCost(cart.getTotal());
			validateTotalCost(cart.getGrandTotal());
		    validateProductCount(cart.getProductCount());

		    logger.info("Validation Successful");
			flag = true;
		}
		logger.info("validateCart() has executed");
		return flag;
	}

	
	  //double total validation
	    public static boolean validateTotalCost(double total) throws CartNotFoundException {
		boolean flag = false;
		if (total <= 0)
			throw new CartNotFoundException("Please add some Product to cart");
		else if (total < 200)
			throw new CartNotFoundException(" Minimum purchase should be atleast for Rs 200 /-");
		else
			flag = true;
		return flag;
	}

	// integer productCount validation
	public static boolean validateProductCount(int productCount) throws CartNotFoundException {
		boolean flag = false;
		if (productCount <= 0)
			throw new CartNotFoundException("No products added yet in cart.. Add some products please ");
		else
			flag = true;
		return flag;
	}

}
