package com.hotel.booking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;
import com.hotel.booking.repository.HotelBookingMapper;

import jakarta.servlet.http.HttpSession;

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
	
	public List<Room> getRoom() {
		return this.hotelBookingMapper.getRoom();
	}

	public List<User> getUser() {
		return this.hotelBookingMapper.getUser();
	}
	
	public List<Booking> getBooking(int userId){
		return this.hotelBookingMapper.getBooking(userId);
	}

	public User checkAuth(HttpSession session) {
		User user = (User) session.getAttribute("Auth");
		return user;
	}

	public void setAuth(User user, HttpSession session) {
		session.setAttribute("Auth", user);
	}
	public User login(String email, String password) {
		return this.hotelBookingMapper.login(email, password);
	}
	

	public void createUser(User user) {
		this.hotelBookingMapper.createUser(user);
	}
	// delivery agent for model alert
	public void msgDeliveryAgent(Model model,HttpSession session) {
		// screen/index.html => input 'order-success'
		model.addAttribute("orderSuccess",session.getAttribute("orderSuccess"));
		session.setAttribute("orderSuccess","");
	}
	
	// error
	public Map<String,String> formErrorExtractor(BindingResult result) {
		Map<String,String> errorMap =  new HashMap<String,String>();
		for(FieldError field :result.getFieldErrors()) {
			errorMap.put(field.getField(),field.getDefaultMessage());
		}
		return errorMap;	
	}
}
