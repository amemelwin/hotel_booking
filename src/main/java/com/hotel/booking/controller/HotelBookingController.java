package com.hotel.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.booking.entity.User;
import com.hotel.booking.repository.HotelBookingMapper;
import com.hotel.booking.service.HotelBookingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HotelBookingController {
	
	@Autowired
	HotelBookingMapper hotelBookingMapper;
	
	@Autowired
	HotelBookingService hotelBookingService;
	
	@GetMapping("/")
	public String index(Model model,HttpSession session) {
		model.addAttribute("Auth",this.hotelBookingService.checkAuth(session));
		return "screen/index";
	}
	

	@GetMapping("/hotel/room/get")
	public String getRooms() {
		System.out.println(this.hotelBookingMapper.getRoom());
		return "screen/index";
	}
	
	@GetMapping("/hotel/booking/get")
	public String getBooking() {
		System.out.println(this.hotelBookingMapper.getBooking());
		return "screen/index";
	}

	@GetMapping("/login") // testing purpose get it will be post after 
	public String login(Model model,HttpSession session) {
		User currentUser = hotelBookingMapper.getUser().get(1);
		this.hotelBookingService.login(currentUser, session);
//		model.addAttribute("user",this.hotelBookingService.checkAuth(session));
		return "screen/login";
	}
	

	
//	@GetMapping("/testing")
//	public String test(Model model, HttpSession session) {
//		model.addAttribute("Auth",session.getAttribute("Auth"));
//		return "screen/text";
//	}
}
