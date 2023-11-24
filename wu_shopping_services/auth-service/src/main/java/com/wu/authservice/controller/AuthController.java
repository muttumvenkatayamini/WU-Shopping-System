package com.wu.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wu.authservice.dto.AuthRequest;
import com.wu.authservice.dto.UserDTO;
import com.wu.authservice.entity.UserCredential;
import com.wu.authservice.entity.Users;
import com.wu.authservice.exception.BadRequestException;
import com.wu.authservice.service.AuthService;
import com.wu.authservice.service.UserRegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRegistrationService userRegisterService;
	
//	@GetMapping("/myname")
//	public ResponseEntity<String> getMyName(){
//		return ResponseEntity.ok("Risap");
//	}

//	@PostMapping("/register")
//	public ResponseEntity<Users> createuser(@Valid @RequestBody UserDTO user) throws BadRequestException {
//		//log.info("Inside create user of UserRegistrationController");
//		return ResponseEntity.ok().body(this.userRegisterService.createUser(user));
//	}
	
//	@GetMapping("/users")
//	public ResponseEntity<List<Users>> getAlluser() {
////		log.info("Inside get user of UserRegistrationController");
//		return ResponseEntity.ok().body(userRegisterService.getAllUsers());
//	}
	
//	@PostMapping("/register")
//	public String addNewUser(@RequestBody UserCredential user) {
//		return service.saveUser(user);
//	}
	
//	@PostMapping("/token")
//	public String getToken(@RequestBody AuthRequest authRequest) {
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		if(authenticate.isAuthenticated())
//			return service.generateToken(authRequest.getUsername());
//		else
//			throw new RuntimeException("Invalid access - User is invalid");
//	}
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authenticate.isAuthenticated())
			return service.generateToken(authRequest.getUsername());
		else
			throw new RuntimeException("Invalid access - User is invalid");
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is valid";
	} 

}
