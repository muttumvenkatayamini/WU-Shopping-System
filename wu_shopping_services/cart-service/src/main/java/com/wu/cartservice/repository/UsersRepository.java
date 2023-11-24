package com.wu.cartservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wu.cartservice.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Boolean existsByUsername(String username);

	
}
