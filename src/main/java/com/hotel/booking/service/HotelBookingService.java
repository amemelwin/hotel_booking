package com.hotel.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;
import com.hotel.booking.form.SignUpForm;
import com.hotel.booking.repository.HotelBookingMapper;

@Service
public class HotelBookingService {

	@Autowired
	HotelBookingMapper hotelBookingMapper;

	public List<RoomBooking> getRoomBooking() {
		return this.hotelBookingMapper.getRoomBooking();
	}

	public void createBooking(int userId, int roomId) {
		this.hotelBookingMapper.createBooking(userId, roomId);
	}

	public void updateRoom(int roomId, int lendFlag) {
		this.hotelBookingMapper.updateRoom(roomId, lendFlag);
	}

	public void cancelBooking(int bookingId) {
		this.hotelBookingMapper.cancelBooking(bookingId);
	}

	public User checkEmail(String email) {
		return this.hotelBookingMapper.checkEmail(email);
	}

	public List<Booking> getBooking(int userId) {
		return this.hotelBookingMapper.getBooking(userId);
	}

	public User login(String email, String password) {
		return this.hotelBookingMapper.login(email, password);
	}

	public void createUser(SignUpForm signUpForm) {
		this.hotelBookingMapper.createUser(signUpForm);
	}
}
