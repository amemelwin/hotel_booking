package com.hotel.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.repository.HotelBookingMapper;

@Service
public class HotelBookingApiService {
	
	@Autowired
	HotelBookingMapper hotelBookingMapper;
	
	// API

	public List<User> getUsers() {
		return this.hotelBookingMapper.getUsers();
	}

	public User getUserById(int id) {
		return this.hotelBookingMapper.getUserById(id);
	}

	public List<Room> getRooms() {
		return this.hotelBookingMapper.getRooms();
	}

	public Room getRoomById(int id) {
		return this.hotelBookingMapper.getRoomById(id);
	}

	public List<Booking> getBookings() {
		return this.hotelBookingMapper.getBookings();
	}

	public Booking getBookingById(int id) {
		return this.hotelBookingMapper.getBookingById(id);
	}
	
	public User LatestUser() {
		return this.hotelBookingMapper.latestUser();
	}

}
