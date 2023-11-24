package com.wu.productservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu.productservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);

}
