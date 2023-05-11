package com.hotel.booking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.RoomBooking;
import com.hotel.booking.entity.User;
import com.hotel.booking.form.LoginForm;
import com.hotel.booking.form.SignUpForm;
import com.hotel.booking.service.CommonHelper;
import com.hotel.booking.service.HotelBookingService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HotelBookingController {
	@Autowired
	CommonHelper commonHelper;

	@Autowired
	HotelBookingService hotelBookingService;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		this.commonHelper.msgDeliveryAgent(model, session);
		model.addAttribute("rooms", this.hotelBookingService.getRoomBooking());
		model.addAttribute("Auth", this.commonHelper.checkAuth(session));
		return "screen/index";
	}

	@GetMapping("/booking/history")
	public String history(Model model, HttpSession session) {
		User auth = this.commonHelper.checkAuth(session);
		if (auth == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("Auth", auth);
			model.addAttribute("bookingHistorys", this.hotelBookingService.getBooking(auth.getId()));
			return "screen/history";
		}
	}

	@GetMapping("/login") // testing purpose get it will be post after
	public String login(Model model, HttpSession session) {
		if (this.commonHelper.checkAuth(session) == null) {
			LoginForm newForm = new LoginForm();
			newForm.setEmail((String) session.getAttribute("email"));
			session.removeAttribute("email");
			model.addAttribute("loginForm", newForm);
			return "screen/login";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/login") // testing purpose get it will be post after
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			Map<String, String> errorMap = this.commonHelper.formErrorExtractor(result);

			for (Map.Entry<String, String> error : errorMap.entrySet()) {
				model.addAttribute(error.getKey() + "_error", error.getValue());
			}
			return "screen/login";
		}
		User authUser = this.hotelBookingService.login(loginForm.getEmail(), loginForm.getPassword());
		if (authUser != null) {
			this.commonHelper.setAuth(authUser, session);
			return "redirect:/";
		} else {
			model.addAttribute("login_error", "username or password does not match!");
			return "screen/login";
		}
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		SignUpForm signUpForm = new SignUpForm();
		model.addAttribute("signUpForm", signUpForm);
		return "screen/signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult result, Model model,
			HttpSession session) {
		// form field check null
		if (result.hasErrors()) {
			return "screen/signup";
		}
		// check password and confirm password
		if (!signUpForm.getConfirmPassword().equals(signUpForm.getPassword())) {
			model.addAttribute("confirm_error", "The password and confirmation password do not match.");
			return "screen/signup";
		}
		// check email duplicate
		if (this.hotelBookingService.checkEmail(signUpForm.getEmail()) != null) {
			model.addAttribute("email_error", "Your email is already exist!");
			return "screen/signup";
		}

		this.hotelBookingService.createUser(signUpForm);
		session.setAttribute("email", signUpForm.getEmail());
		return "redirect:/login";
	}

	@GetMapping("/logout") // testing purpose get it will be post after
	public String logout(Model model, HttpSession session) {
		this.commonHelper.setAuth(null, session);
		return "redirect:/";
	}

	@PostMapping("/booking/create")
	public String booking(@ModelAttribute Room room, HttpSession session) {
		User auth = this.commonHelper.checkAuth(session);
		System.out.println(room.getId());
		if (auth != null) {
			// Check In Room
			if(this.hotelBookingService.checkInRoom(room.getId())!=null) {
				this.hotelBookingService.createBooking(auth.getId(), room.getId());
				this.hotelBookingService.updateRoom(room.getId(), 1);
				session.setAttribute("orderSuccess", "Room " + room.getId() + " を予約しました。");
			}else {
				session.setAttribute("orderSuccess", "申し訳ございません。予約をできませんでした！");
			}
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/booking/cancel")
	public String cancelBooking(@ModelAttribute RoomBooking room, HttpSession session) {
		User auth = this.commonHelper.checkAuth(session);
		if (auth != null) {
			this.hotelBookingService.cancelBooking(room.getBookingId());
			this.hotelBookingService.updateRoom(room.getId(), 0);
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

}
