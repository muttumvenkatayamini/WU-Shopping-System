package com.wu.cartservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wu.cartservice.entity.UsersCart;

@Repository
public interface UsersCartRepository extends JpaRepository<UsersCart, Long> {

	void deleteById(Long id);

	Optional<UsersCart> findByUsername(String userName);

}
