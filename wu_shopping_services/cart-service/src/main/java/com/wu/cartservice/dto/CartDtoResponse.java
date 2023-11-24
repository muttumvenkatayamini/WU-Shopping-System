package com.wu.cartservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartDtoResponse {

	private String message;

	private String username;

	private Long cartId;

	private Long productId;

	private String productName;

	private int quantity;

	private BigDecimal price;

	private BigDecimal totalCartPrice;

}
