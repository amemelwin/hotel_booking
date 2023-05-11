package com.hotel.booking.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;
import com.hotel.booking.form.SignUpForm;

@Mapper
public interface HotelBookingMapper {

	// get room booking
	public List<RoomBooking> getRoomBooking();

	// create booking
	public void createBooking(@Param("userId") int userId, @Param("roomId") int roomId);

	// update room
	public void updateRoom(@Param("roomId") int roomId, @Param("lendFlag") int lendFlag);

	// cancel Booking
	public void cancelBooking(@Param("bookingId") int bookingId);

	// Get booking
	public List<Booking> getBooking(@Param("userId") int userId);
	
	// checkInRoom
	public Room checkInRoom(@Param("roomId") int roomId);
	
	// checkOutRoom
	public int checkOutRoom(@Param("bookingId") int bookingId,@Param("roomId") int roomId,@Param("userId") int userId);

	// Create User
	public void createUser(SignUpForm signUpForm);

	// Check Email Exist
	public User checkEmail(@Param("email") String email);

	// login
	public User login(@Param("email") String email, @Param("password") String password);
	
	//Api
	public List<User> getUsers();
	
	public User getUserById(int id);
	
	public List<Room> getRooms();
	
	public List<Booking> getBookings();

}
