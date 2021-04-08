package com.cg.osm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.repository.ICartRepository;
import com.cg.osm.util.CartUtils;

/*
 * Author : UJJWAL SINGH A
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is Cart Service Layer
*/

@Service
public class CartServiceImp implements ICartService {

	final static Logger LOGGER = LoggerFactory.getLogger(CartServiceImp.class);

	@Autowired
	private ICartRepository cartRepo;

	static String cartNotFound = "No Cart found with given ID";
	
	/*
	 * Description : This method Adds new Cart
	 * Input Param : Cart Object 
	 * Return Value : CartDTO Object 
	*/

	@Override
	public CartDTO addCart(Cart cart) {
		LOGGER.info("addCart() service is initiated");
		Cart cartEntity;
		cartEntity = cartRepo.save(cart);
		LOGGER.info("addCart() service has executed");
		return CartUtils.convertToCartDto(cartEntity);
	}

	/*
	 * Description : This method Updates existing Cart
	 * Input Param : Cart Object 
	 * Return Value : CartDTO Object 
	 * Exception : CartNotFoundException
	 */

	@Override
	public CartDTO updateCart(Cart cart) throws CartNotFoundException {
		LOGGER.info("updateCart() service is initiated");
		Cart updatecart;

		Cart existCart = cartRepo.findById(cart.getCartId()).orElse(null);

		if (existCart == null) {

			throw new CartNotFoundException(cartNotFound);
		} else

			updatecart = cartRepo.save(cart);

		LOGGER.info("updateCart() service has executed");

		return CartUtils.convertToCartDto(updatecart);

	}

	/*
	 * Description : This method Deletes existing Cart
	 * Input Param : int 
	 * Return Value : CartDTO Object 
	 * Exception : CartNotFoundException
	 */
	
	@Override
	public CartDTO cancelCart(int cartId) throws CartNotFoundException {
		LOGGER.info("cancelCart() service is initiated");

		Cart existCart = cartRepo.findById(cartId).orElse(null);

		if (existCart == null)
			throw new CartNotFoundException(cartNotFound);
		else

			cartRepo.delete(existCart);

		LOGGER.info("cancelCart() service has executed");

		return CartUtils.convertToCartDto(existCart);
	}

	/*
	 * Description : This method Shows existing Cart
	 *  Input Param : int
	 *  Return Value: CartDTO Object 
	 * Exception : CartNotFoundException
	 */

	@Override
	public CartDTO showCart(int cartId) throws CartNotFoundException {
		LOGGER.info("showCart() service is initiated");
		Cart showCart = cartRepo.findById(cartId).orElse(null);
		if (showCart == null)
			throw new CartNotFoundException(cartNotFound);

		LOGGER.info("showCart() service has executed");

		return CartUtils.convertToCartDto(showCart);
	}
	
	/*
	 * Description : This method Shows existing Cart
	 * Input Param : int
	 * Return Value : CartDTO Object 
	 * Exception : CartNotFoundException
	 */

	@Override
	public List<CartDTO> showAllCarts() {
		LOGGER.info("showAllCarts() service is initiated");
		List<Cart> list = cartRepo.findAll();
		LOGGER.info("showAllCarts() service has executed");
		return CartUtils.convertToCartDtoList(list);
	}

	// VALIDATIONS
	public static boolean validateCart(Cart cart) throws CartNotFoundException {
		boolean flag = false;
		if (cart == null)
			throw new CartNotFoundException("Cart details cannot be blank");
		else if (!(validateTotalCost(cart.getTotal()) && validateTotalCost(cart.getGrandTotal())
				&& validateProductCount(cart.getProductCount())))

			throw new CartNotFoundException("Invalid Data");
		else
			flag = true;
		return flag;
	}

	// integer cartId validation

	public static boolean validateCartId(Cart cart) throws CartNotFoundException {
		boolean flag = false;
		if (cart.getCartId() > 0)
			flag = true;
		else
			throw new CartNotFoundException("Not a valid cart id");
		return flag;
	}

	// double total validation
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
