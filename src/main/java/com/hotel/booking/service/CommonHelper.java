package com.hotel.booking.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hotel.booking.entity.User;

import jakarta.servlet.http.HttpSession;

@Service
public class CommonHelper {
	// error
	public Map<String, String> formErrorExtractor(BindingResult result) {
		Map<String, String> errorMap = new HashMap<String, String>();
		for (FieldError field : result.getFieldErrors()) {
			errorMap.put(field.getField(), field.getDefaultMessage());
		}
		return errorMap;
	}

	public User checkAuth(HttpSession session) {
		User user = (User) session.getAttribute("Auth");
		return user;
	}

	public void setAuth(User user, HttpSession session) {
		session.setAttribute("Auth", user);
	}

	// delivery agent for model alert
	public void msgDeliveryAgent(Model model, HttpSession session) {
		// screen/index.html => input 'order-success'
		model.addAttribute("orderSuccess", session.getAttribute("orderSuccess"));
		model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
		session.removeAttribute("orderSuccess");
		session.removeAttribute("errorMessage");
	}
}
