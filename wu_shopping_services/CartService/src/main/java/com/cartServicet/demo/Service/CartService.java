package com.cartServicet.demo.Service;


import java.util.List;

import com.cartServicet.demo.CartDto.CartDtoResponse;
import com.cartServicet.demo.CartDto.Cartdto;
import com.cartServicet.demo.CartDto.DeleteProductDto;
import com.cartServicet.demo.CartDto.ProductDto;
import com.cartServicet.demo.CartDto.ProductResponseDto;
import com.cartServicet.demo.Entity.ProductEntity;

/**
 * 
 * @author muttum.venkatayamini
 *
 */
public interface CartService {


	CartDtoResponse createCart(Cartdto cartDto);

	ProductResponseDto addProductToCart(ProductDto productDto);

	void deleteCart(Long cartId);

	List<ProductEntity> getAllProductsByCartId(Long cartId);

	void deleteProduct(DeleteProductDto deleteProductDto);

//	List<CartEntity> getAllServiceRequestByUserName(Long cartId);


	

//CartItemEntity addToCart(Cartdto cartDto);    1 cart - 1 product

	


}
