package com.wu.cartservice.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wu.cartservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

//	@Query(value = "SELECT * FROM product p WHERE p.name = :name or p.category = :category or p.price = :price", nativeQuery = true)
//	List<Product> findByNameCategoryPrice(@Param("name") String name, @Param("category") String category,
//			@Param("price") String price);
//
//	@Query(value = "SELECT p FROM Product p WHERE p.price >= :price")
//	List<Product> findAllByPrice(@Param("price") BigDecimal price, Sort sort);

	@Query("SELECT p FROM Product p " + "WHERE p.name = :productName " + "AND p.price = :userPrice "
			+ "AND p.stockQuantity >= :userQuantity")
	Product findProductByNamePriceAndQuantity(@Param("productName") String productName,
			@Param("userPrice") BigDecimal userPrice, @Param("userQuantity") Integer userQuantity);
}
