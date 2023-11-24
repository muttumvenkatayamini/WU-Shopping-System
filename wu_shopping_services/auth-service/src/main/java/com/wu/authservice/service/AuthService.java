package com.wu.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wu.authservice.entity.UserCredential;
import com.wu.authservice.entity.Users;
import com.wu.authservice.repository.UserCredentialRepository;
import com.wu.authservice.repository.UsersRepository;
import com.wu.authservice.util.JwtService;

@Service
public class AuthService {

//	@Autowired
//	private UserCredentialRepository repository;

	@Autowired
	private UsersRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

//	public String saveUser(UserCredential credential) {
//		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
//		repository.save(credential);
//		return "User added to the system";
//		
//	}

	public String generateToken(String username) {
		return jwtService.generateToken(username, getUserRoles(username));
	}

	private String getUserRoles(String username) {
		Optional<Users> credential = repository.findByUsername(username);
		if (credential.isPresent() && null != credential.get().getRoleName())
			return credential.get().getRoleName();
		else
			return "USER";
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
