package com.wu.productservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.wu.productservice.entity.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {

}
