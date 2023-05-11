package com.hotel.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.system.handler.ErrorResponse;
import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.form.SignUpForm;
import com.hotel.booking.service.HotelBookingService;
import com.hotel.booking.service.CommonHelper;
import com.hotel.booking.service.HotelBookingApiService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${apiPrefix}")
@SuppressWarnings("rawtypes")

public class HotelBookingApiController {

	@Autowired
	HotelBookingService hotelBookingService;

	@Autowired
	HotelBookingApiService hotelBookingApiService;

	@Autowired
	CommonHelper commonHelper;

	@GetMapping("/users")
	public ResponseEntity getUsers() {
		List<User> usersList = this.hotelBookingApiService.getUsers();
		if (usersList.size() > 0) {
			return new ResponseEntity<>(usersList, HttpStatus.OK);
		}
		return new ErrorResponse("user not found").response();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity getUserById(@PathVariable(value = "id") int userId) {
		User user = this.hotelBookingApiService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ErrorResponse("Your User Id " + userId + " not found").response();
		}
	}

	@GetMapping("/rooms")
	public ResponseEntity getRooms() {
		List<Room> roomsList = this.hotelBookingApiService.getRooms();
		if (roomsList.size() > 0) {
			return new ResponseEntity<>(roomsList, HttpStatus.OK);
		}
		return new ErrorResponse("room not found").response();
	}

	@GetMapping("/rooms/{id}")
	public ResponseEntity getRoomById(@PathVariable(value = "id") int roomId) {
		Room room = this.hotelBookingApiService.getRoomById(roomId);
		if (room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else {
			return new ErrorResponse("Your Room Id " + roomId + " not found").response();
		}
	}

	@GetMapping("/bookings")
	public ResponseEntity getBookings() {
		List<Booking> bookingsList = this.hotelBookingApiService.getBookings();
		if (bookingsList.size() > 0) {
			return new ResponseEntity<>(bookingsList, HttpStatus.OK);
		}
		return new ErrorResponse("booking not found").response();
	}

	@GetMapping("/bookings/{id}")
	public ResponseEntity getBookingById(@PathVariable(value = "id") int bookingId) {
		Booking booking = this.hotelBookingApiService.getBookingById(bookingId);
		if (booking != null) {
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			return new ErrorResponse("Your Booking Id " + bookingId + " not found").response();
		}
	}

	@PostMapping("/signup")
	public ResponseEntity signUp(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return new ErrorResponse(this.commonHelper.formErrorExtractor1(result)).response();
		} else {
			this.hotelBookingService.createUser(signUpForm);
			User newUser = this.hotelBookingApiService.LatestUser();
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}
	}

	@PostMapping("/bookings/create")
	public ResponseEntity createBooking(@ModelAttribute Room room, HttpSession session) {
		User auth = this.commonHelper.checkAuth(session);
		if (auth != null) {
			// Check In Room
			if (this.hotelBookingService.checkInRoom(room.getId()) != null) {
				this.hotelBookingService.createBooking(auth.getId(), room.getId());
				this.hotelBookingService.updateRoom(room.getId(), 1);
				return new ResponseEntity<>(room, HttpStatus.CREATED);
			} else {
				return new ErrorResponse("Your Booking Room Id " + room.getId() + " can not be available now")
						.response();
			}
		} else {
			return new ErrorResponse("Your User Id  not found").response();
		}
	}
}
