package com.wu.loginservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.loginservice.dto.LoginDto;
import com.wu.loginservice.dto.LoginResponse;
import com.wu.loginservice.exception.BadRequestException;
import com.wu.loginservice.service.LoginService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/user")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginDto login) throws BadRequestException {
		log.info("Inside login user of LoginController");
		return ResponseEntity.ok().body(this.loginService.loginUser(login));
	}
	
	@PostMapping("/createSessionData")
	public ResponseEntity<String> sessionData(String username) throws BadRequestException {
		log.info("Inside sessionData of LoginController");		
		return ResponseEntity.ok().body(this.loginService.getSessionData(username));
	}

}