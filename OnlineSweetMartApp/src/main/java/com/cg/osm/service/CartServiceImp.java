package com.cg.osm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.osm.entity.Cart;
import com.cg.osm.error.CartNotFoundException;
import com.cg.osm.model.CartDTO;
import com.cg.osm.repository.ICartRepository;
import com.cg.osm.util.CartUtils;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements ICartService {

	@Autowired
	ICartRepository cartRepo;

	static String cartNotFound = "No Cart found with given ID";

	@Override
	public CartDTO addCart(Cart cart) {
		Cart cartEntity;

		cartEntity = cartRepo.save(cart);
		return CartUtils.convertToCartDto(cartEntity);
	}

	@Override
	public CartDTO updateCart(Cart cart) throws CartNotFoundException {

		Cart updatecart;

		Cart existCart = cartRepo.findById(cart.getCartId()).orElse(null);

		if (existCart == null) {

			throw new CartNotFoundException(cartNotFound);
		} else

			updatecart = cartRepo.save(cart);
		return CartUtils.convertToCartDto(updatecart);

	}

	@Override
	public CartDTO cancelCart(int cartId) throws CartNotFoundException {

		Cart existCart = cartRepo.findById(cartId).orElse(null);

		if (existCart == null)
			throw new CartNotFoundException(cartNotFound);
		else

			cartRepo.delete(existCart);
		return CartUtils.convertToCartDto(existCart);
	}

	@Override
	public CartDTO showCart(int cartId) throws CartNotFoundException {

		Cart showCart = cartRepo.findById(cartId).orElse(null);
		if (showCart == null)
			throw new CartNotFoundException(cartNotFound);
		return CartUtils.convertToCartDto(showCart);
	}

	@Override
	public List<CartDTO> showAllCarts() {

		List<Cart> list = cartRepo.findAll();

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
