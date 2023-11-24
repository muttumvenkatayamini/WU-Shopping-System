package com.wu.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.authservice.dto.UserDTO;
import com.wu.authservice.entity.Users;
import com.wu.authservice.exception.BadRequestException;
import com.wu.authservice.exception.ResourceNotFoundException;
import com.wu.authservice.service.UserRegistrationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/registration")
@Slf4j
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegisterService;

	@PostMapping("/user/create")
	public ResponseEntity<Users> createuser(@Valid @RequestBody UserDTO user) throws BadRequestException {
		log.info("Inside create user of UserRegistrationController");
		return ResponseEntity.ok().body(this.userRegisterService.createUser(user));
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAlluser() {
		log.info("Inside get user of UserRegistrationController");
		return ResponseEntity.ok().body(userRegisterService.getAllUsers());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Users> getuserById(@PathVariable int userId) throws ResourceNotFoundException {
		log.info("Inside get user by id of UserRegistrationController");
		return ResponseEntity.ok().body(userRegisterService.getUserById(userId));
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<Users> createuser(@PathVariable int userId, @Valid @RequestBody UserDTO user)
			throws ResourceNotFoundException, BadRequestException {
		log.info("Inside update by user id of UserRegistrationController");
		return ResponseEntity.ok().body(this.userRegisterService.updateUser(userId, user));
	}

}
