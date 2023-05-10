package com.hotel.booking.entity;

import lombok.Data;

@Data
public class RoomBooking {
	private int id;
	private String roomNumber;
	private int lendFlag;
	private int bookUserId;
	private int bookingId;
}
