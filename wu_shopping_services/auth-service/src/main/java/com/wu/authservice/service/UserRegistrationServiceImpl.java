package com.wu.authservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wu.authservice.dto.UserDTO;
import com.wu.authservice.entity.Users;
import com.wu.authservice.exception.BadRequestException;
import com.wu.authservice.exception.ResourceNotFoundException;
import com.wu.authservice.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ModelMapper modelMapper;

//	@Autowired
//	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users createUser(UserDTO users) throws BadRequestException {
		log.info("Inside  createUser of UserRegistrationServiceImpl");
		// add check for username exists in a DB
		if (usersRepository.existsByUsername(users.getUsername())) {
			log.error("Username is already avialble, so use other");
			throw new BadRequestException("Username is already avialble, so use other");
		}

		// add check for email exists in DB
		if (usersRepository.existsByEmail(users.getEmail())) {
			log.error("Email is already avialble, so use other");
			throw new BadRequestException("Email is already avialble, so use other!");
		}
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		Users user = modelMapper.map(users, Users.class);
		return usersRepository.save(user);
	}

	@Override
	public Users updateUser(long userId, UserDTO userDto) throws ResourceNotFoundException, BadRequestException {
		log.info("user id for update ::" + userId);
		Optional<Users> user = usersRepository.findById(userId);
		if (user.isPresent()) {
			Users updateUser = user.get();
			updateUser = modelMapper.map(userDto, Users.class);
			updateUser.setUserId(userId);
			// add check for username exists in a DB
			if (usersRepository.existsByUsername(updateUser.getUsername())) {
				log.error("Username is already avialble, so use other");
				throw new BadRequestException("Username is already taken!");
			}

			// add check for email exists in DB
			if (usersRepository.existsByEmail(updateUser.getEmail())) {
				log.error("Username is already avialble, so use other");
				throw new BadRequestException("Email is already taken!");
			}
			return usersRepository.save(updateUser);
		} else {
			log.error("userId not found for this id ::" + userId);
			throw new ResourceNotFoundException("userId not found for this id :: " + userId);

		}

	}

	@Override
	public List<Users> getAllUsers() {
		log.info("Inside  getAllUsers of UserRegistrationServiceImpl");
		return this.usersRepository.findAll();
	}

	@Override
	public Users getUserById(long userId) throws ResourceNotFoundException {
		log.info("Inside  getUserById of UserRegistrationServiceImpl");
		Optional<Users> productDb = this.usersRepository.findById(userId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			log.error("No user data found for this id ::" + userId);
			throw new ResourceNotFoundException("userId not found for this id :: " + userId);
		}

	}

}
