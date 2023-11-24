package com.wu.registrationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class RegistrationController {
	
	@GetMapping("user")
	public ResponseEntity<String> userRegistration() {
		return ResponseEntity.ok("User Registered!!!");

	}

	@GetMapping("admin")
	public ResponseEntity<String> adminRegistration() {
		return ResponseEntity.ok("Admin Registered!!!");

	}
}
