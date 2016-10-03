package com.feelthesound.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

@Controller
public class RegisterController {
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, HttpSession httpSession, BindingResult result) {

		try {
			if (result.hasErrors()) {
				return "register";
			}
			
			if (!new UserDAO().isUserExisting(user)) {
				new UserDAO().registerUser(user);
				httpSession.setAttribute("user", user);
					return "successfulRegistration";
				}
			
			return "";
			
		} catch (ConnectionException e) {
			return "redirect:/pages/404.html";
		} catch (UserException e) {
			return "";
		}
	}

	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String showLogged(Model model) {
		model.addAttribute(new User());
		return "register";
	}
}
