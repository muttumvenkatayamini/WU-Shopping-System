package com.cartServicet.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
public class ProductEntity {
	
	@Id
	Long Productid;
	private int quantity;

	private double price;
	
	@ManyToOne()
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
	@JsonIgnore
    private CartEntity cartEntity;

}
