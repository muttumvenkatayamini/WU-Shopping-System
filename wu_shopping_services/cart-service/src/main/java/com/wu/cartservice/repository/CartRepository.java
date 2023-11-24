package com.wu.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wu.cartservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllCartByUsersCart_cartId(Long cartId);

	Cart findByProductName(String productName);

}
