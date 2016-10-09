package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;

@Controller
public class RegisterController {
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, Model model, HttpSession httpSession) {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			if (userDAO.isUserExisting(user)) {
				model.addAttribute("message", "This account already exists");
				return "failedRegistration";
			
			} else {
				userDAO.registerUser(user);
				httpSession.setAttribute("user", user);
				return "successfulRegistration";
			}

		} catch (Exception e) {
			model.addAttribute("message", "Invalid register");
			return "failedRegistration";
		}
	}

	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String showLogged(Model model) {
		model.addAttribute(new User());
		return "register";
	}
}
