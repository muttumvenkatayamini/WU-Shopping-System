package com.wu.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu.authservice.entity.Users;



public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

	//Optional<Users> findByUserNameOrEmail(String username, String email);

	
	Optional<Users> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	//Optional<Users> findByUserNameOrEmailAndPassword(String username, String email, String password);

}
