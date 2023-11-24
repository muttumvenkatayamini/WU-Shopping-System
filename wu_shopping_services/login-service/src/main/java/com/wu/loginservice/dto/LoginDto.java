package com.wu.loginservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
	@NotEmpty(message = "The username is required.")
	private String username;

	@NotEmpty(message = "The password is required.")
	private String password;
}