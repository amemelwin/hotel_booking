package com.hotel.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.booking.repository.HotelBookingMapper;

@Controller
public class HotelBookingController {
	
	@Autowired
	HotelBookingMapper hotelBookingMapper;
	
	@GetMapping("/")
	public String index() {
		System.out.println(this.hotelBookingMapper.getUser());
		return "screen/index";
	}
	
	@GetMapping("/hotel/room/get")
	public String getRooms() {
		System.out.println(this.hotelBookingMapper.getRoom());
		return "screen/index";
	}
}
