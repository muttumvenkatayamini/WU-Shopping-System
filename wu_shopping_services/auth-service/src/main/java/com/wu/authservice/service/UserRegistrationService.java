package com.wu.authservice.service;

import java.util.List;

import com.wu.authservice.dto.UserDTO;
import com.wu.authservice.entity.Users;
import com.wu.authservice.exception.BadRequestException;
import com.wu.authservice.exception.ResourceNotFoundException;



public interface UserRegistrationService {

	Users createUser(UserDTO users) throws BadRequestException;

	Users updateUser(long userId, UserDTO users) throws ResourceNotFoundException, BadRequestException;

	List<Users> getAllUsers();

	Users getUserById(long userId) throws ResourceNotFoundException;

}
