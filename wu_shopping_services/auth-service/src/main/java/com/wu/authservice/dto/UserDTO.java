package com.wu.authservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

	@NotBlank(message = "Username is required.")
	@Size(min = 3, max = 20, message = "Username must be from 3 to 20 characters.")
	private String username;

	@NotEmpty(message = "Email is required.")
	@Email(message = "Email provided is not a valid one.")
	private String email;

	@NotEmpty(message = "Password is required.")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$", message = "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
	private String password;

	@NotEmpty(message = "Firstname is required.")
	private String firstName;

	private String middleName;

	@NotEmpty(message = "Lastname is required.")
	private String lastName;

	@NotEmpty(message = "Address1 is required.")
	private String address1;

	private String address2;

	@NotEmpty(message = "City is required.")
	private String city;

	@NotEmpty(message = "State is required.")
	private String state;

	@NotEmpty(message = "Country is required.")
	private String country;

	@NotEmpty(message = "Pincode is required.")
	private String pincode;

	@NotEmpty(message = "Phone Number is required.")
	@Size(min = 8, max = 10, message = "Phone Number must be from 8 to 10 digits.")
	private String phoneNumber;

//	@NotEmpty(message = "The roleName is required.")
	private String roleName="USER";

}
