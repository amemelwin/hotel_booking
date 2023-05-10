package com.hotel.booking.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;

@Mapper
public interface HotelBookingMapper {
	
	// get room booking
	public List<RoomBooking> getRoomBooking();
	
	// create booking
	public void createBooking(@Param("userId") int userId, @Param("roomId") int roomId);
	
	// update room
	public void updateRoom(@Param("roomId") int roomId,@Param("lendFlag") int lendFlag);
	
	public List<User> getUser();
	
	// Get room
	public List<Room> getRoom();
	
	// Get booking
	public List<Booking> getBooking();
	
}
