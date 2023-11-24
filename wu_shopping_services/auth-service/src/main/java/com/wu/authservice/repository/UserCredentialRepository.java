package com.wu.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu.authservice.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer>{

//	Optional<UserCredential> findByUsername(String username);

}
