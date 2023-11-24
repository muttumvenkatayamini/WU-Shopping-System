package com.wu.loginservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	
	private String username;
	
	private String token;
	
	private String sessionId;
	
	private String message;
	

}
