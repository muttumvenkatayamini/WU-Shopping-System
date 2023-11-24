package com.wu.cartservice.entity;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;
/**
 * 
 * @author muttum.venkatayamini
 *
 */
import lombok.*;

@Entity
@Table(name="users_cart")
@Data
public class UsersCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	private String username;

	private BigDecimal totalPrice;

	@OneToMany(mappedBy = "usersCart", cascade = CascadeType.ALL, orphanRemoval = true)

	private List<Cart> products = new ArrayList<>();

}
