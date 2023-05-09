package com.hotel.booking.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hotel.booking.entity.User;

@Mapper
public interface HotelBookingMapper {
	
	public List<User> getUser();
}
