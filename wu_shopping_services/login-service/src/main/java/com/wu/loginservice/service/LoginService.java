package com.wu.loginservice.service;

import com.wu.loginservice.dto.LoginDto;
import com.wu.loginservice.dto.LoginResponse;

public interface LoginService {

	LoginResponse loginUser(LoginDto loginDto);

	String getSessionData(String username);
}
