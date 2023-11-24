package com.wu.cartservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartDto {

	private String username;
	private Long productId;
	private String productName;
	private int quantity;
	private BigDecimal price;

}
