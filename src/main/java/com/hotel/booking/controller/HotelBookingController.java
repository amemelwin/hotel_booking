package com.hotel.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.service.HotelBookingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HotelBookingController {
	

	@Autowired
	HotelBookingService hotelBookingService;
	
	@GetMapping("/")
	public String index(Model model,HttpSession session) {
		model.addAttribute("rooms",this.hotelBookingService.getRoom());
		model.addAttribute("Auth",this.hotelBookingService.checkAuth(session));
		return "screen/index";
	}
	

	@GetMapping("/hotel/room/get")
	public String getRooms() {
		System.out.println(this.hotelBookingService.getRoom());
		return "screen/index";
	}
	
	@GetMapping("/hotel/booking/get")
	public String getBooking() {
//		System.out.println(this.hotelBookingMapper.getBooking());
		return "screen/index";
	}

	@GetMapping("/login") // testing purpose get it will be post after 
	public String login(Model model,HttpSession session) {
		if(this.hotelBookingService.checkAuth(session) == null) {			
			return "screen/login";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout") // testing purpose get it will be post after 
	public String logout(Model model,HttpSession session) {
		this.hotelBookingService.login(null, session);
		return "redirect:/";
	}
	
	@PostMapping("/booking/create")
	public String booking(@ModelAttribute Room room, HttpSession session) {
		System.out.print(room);
		if(this.hotelBookingService.checkAuth(session) != null) {			
			return "screen/index";
		}else {
			return "redirect:/login";
		}
	}
	
//	@GetMapping("/testing")
//	public String test(Model model, HttpSession session) {
//		model.addAttribute("Auth",session.getAttribute("Auth"));
//		return "screen/text";
//	}
}
