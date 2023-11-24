package com.wu.cartservice.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cart")
@Data
public class Cart {
	
	@Id
	Long productId;
	
	private int quantity;

	private String productName;
	
	private BigDecimal price;
	
	@ManyToOne()
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
	@JsonIgnore
    private UsersCart usersCart;

}
