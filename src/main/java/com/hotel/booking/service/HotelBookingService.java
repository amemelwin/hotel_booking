package com.hotel.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;
import com.hotel.booking.repository.HotelBookingMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class HotelBookingService {
	
	@Autowired
	HotelBookingMapper hotelBookingMapper; 
	
	public List<RoomBooking> getRoomBooking(){
		return this.hotelBookingMapper.getRoomBooking();
		
	}	
	
	public List<Room> getRoom(){
		return this.hotelBookingMapper.getRoom();
	}
	
	public List<User> getUser(){
		return this.hotelBookingMapper.getUser();
	}
	
	public User checkAuth(HttpSession session) {
		User user = (User) session.getAttribute("Auth");
		return user;
	}
	
	public void login(User user,HttpSession session) {
		session.setAttribute("Auth", user);
	}
}
