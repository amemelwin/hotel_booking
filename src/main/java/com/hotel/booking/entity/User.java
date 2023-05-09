package com.hotel.booking.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private Date createdAt;
	private Date updatedAt;
}
