package com.cartServicet.demo.repository;

import java.util.List;
/**
 * 
 * @author muttum.venkatayamini
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.cartServicet.demo.Entity.ProductEntity;



public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
	
	 List<ProductEntity> findAllProductEntityByCartEntity_cartId(Long cartId);

}
