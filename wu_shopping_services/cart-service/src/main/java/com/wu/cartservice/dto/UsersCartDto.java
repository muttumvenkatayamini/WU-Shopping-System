package com.wu.cartservice.dto;
import java.math.BigDecimal;

/**
 * 
 * @author muttum.venkatayamini
 *
 */
import lombok.Data;

@Data
public class UsersCartDto{
	
	private String username;
	private BigDecimal totalPrice;
	private Long cartId;
	
	


}
