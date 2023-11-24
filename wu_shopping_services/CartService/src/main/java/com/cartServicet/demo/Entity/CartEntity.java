package com.cartServicet.demo.Entity;

import java.util.*;

import jakarta.persistence.*;
/**
 * 
 * @author muttum.venkatayamini
 *
 */
import lombok.*;



@Entity
@Table
@Data
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	private Long userId;
	
	private double totalPrice;
	
	@OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<ProductEntity> products = new ArrayList<>();

	

}
