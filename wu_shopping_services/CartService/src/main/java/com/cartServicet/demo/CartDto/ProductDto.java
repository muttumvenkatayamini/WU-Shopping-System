package com.cartServicet.demo.CartDto;

import lombok.*;


@Data
public class ProductDto {
	
	private Long cartId;
	private Long productId;
	
	private int quantity;
	
	private double price;

}
