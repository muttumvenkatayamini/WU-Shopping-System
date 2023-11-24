package com.wu.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wu.authservice.entity.UserCredential;
import com.wu.authservice.entity.Users;
import com.wu.authservice.repository.UserCredentialRepository;
import com.wu.authservice.repository.UsersRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

//	@Autowired
//	private UserCredentialRepository repository;
	
	@Autowired
	private UsersRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserCredential> credential = repository.findByUsername(username);
		 Optional<Users> credential = repository.findByUsername(username);
		return credential.map(CustomUserDetail::new).orElseThrow(()->new UsernameNotFoundException("User not found with name :"+username));
	}

}

