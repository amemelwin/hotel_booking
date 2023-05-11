package com.hotel.booking.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpForm {
	@NotEmpty(message = "username is required!")
	private String username;
	@NotEmpty(message = "email is required!")
	private String email;
	@NotEmpty(message = "password is required!")
	private String password;
	@NotEmpty(message = "confirm password is required!")
	private String confirmPassword;
}
