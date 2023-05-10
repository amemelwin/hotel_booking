package com.hotel.booking.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty(message="Email is required!")
	private String email;
	@NotEmpty(message="Passwrod is required!")
	private String password;
}