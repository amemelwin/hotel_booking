package com.hotel.booking.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Booking {
	private int id;
	private Date date;
	private Date createdAt;
	private Date updatedAt;
}
