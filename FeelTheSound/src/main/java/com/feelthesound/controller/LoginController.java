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
public class LoginController {
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model, HttpSession httpSession) {
		try {
			if (UserDAO.getInstance().isUserExisting(user)) {
				httpSession.setAttribute("user", user);
					return "redirect:/profile";
				}
			
			model.addAttribute("message", "Invalid username or password");
			return "failedLogin";
			
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username or password");
			return "failedLogin";
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String showLogged(Model model) {
		model.addAttribute(new User());
		return "login";
	}
}