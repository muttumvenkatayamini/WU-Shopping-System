package com.wu.cartservice.service;

import java.util.List;

import com.wu.cartservice.dto.CartDto;
import com.wu.cartservice.dto.CartDtoResponse;
import com.wu.cartservice.dto.DeleteProductDto;
import com.wu.cartservice.entity.Cart;

/**
 * 
 * @author muttum.venkatayamini
 *
 */
public interface CartService {

//	CartDtoResponse createCart(Cartdto cartDto);

	CartDtoResponse addProductToCart(CartDto productDto);

	void deleteCart(Long cartId);

	List<Cart> getAllProductsByCartId(Long cartId);

	void deleteProduct(DeleteProductDto deleteProductDto);

}
