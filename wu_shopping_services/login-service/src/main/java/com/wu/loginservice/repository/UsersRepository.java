package com.wu.loginservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wu.loginservice.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

	Optional<Users> findByUsernameOrEmail(String username, String email);

	Optional<Users> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query("Select password from Users where username=?1")
	String findPassword(String username);

//	Optional<Users> findByUsernameOrEmailAndPassword(String username, String email, String password);

	Optional<Users> findByUsernameAndPassword(String username, String password);
}

