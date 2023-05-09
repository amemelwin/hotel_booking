package com.hotel.booking.service;

import org.springframework.stereotype.Service;

import com.hotel.booking.entity.User;

import jakarta.servlet.http.HttpSession;

@Service
public class HotelBookingService {
	
	public User checkAuth(HttpSession session) {
		User user = (User) session.getAttribute("Auth");
		return user;
	}
	
	public void login(User user,HttpSession session) {
		session.setAttribute("Auth", user);
	}
}
