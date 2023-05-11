package com.hotel.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.system.handler.ErrorResponse;
import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.service.HotelBookingService;

@RestController
@RequestMapping(path= "${apiPrefix}")
@SuppressWarnings("rawtypes")

public class HotelBookingApiController {

	@Autowired
	HotelBookingService hotelBookingService;
	
	@GetMapping("/hotel/users")
	public ResponseEntity getUsers() {
		List<User> usersList = this.hotelBookingService.getUsers();
		if(usersList.size()>0) {
			return new ResponseEntity<>(usersList,HttpStatus.OK);
		}
		return new ErrorResponse("user not found").response();		 
	}
	
	 @GetMapping("/hotel/users/{id}")
	    public ResponseEntity getUsersById(@PathVariable(value = "id") int userId)  {
	        User user = this.hotelBookingService.getUserById(userId);
	    	if (user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ErrorResponse("Your User Id "+userId+" not found").response();
			}
		}        	
	@GetMapping("/hotel/rooms")
	public ResponseEntity getRooms() {
		List<Room> roomsList = this.hotelBookingService.getRooms();
		if(roomsList.size()>0) {
			return new ResponseEntity<>(roomsList,HttpStatus.OK);
		}
		return new ErrorResponse("room not found").response();	
	}
	
	@GetMapping("/hotel/bookings")
	public ResponseEntity getBookings() {
		List<Booking> bookingsList = this.hotelBookingService.getBookings();
		if(bookingsList.size()>0) {
			return new ResponseEntity<>(bookingsList,HttpStatus.OK);
		}
		return new ErrorResponse("booking not found").response();	
	}
	
	
}
