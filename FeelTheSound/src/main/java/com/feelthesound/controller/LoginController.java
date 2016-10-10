package com.feelthesound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;

@Controller
public class LoginController {
	private static final int MAX_INACTIVE_INTERVAL = 30 * 60; // 30 minutes

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpServletRequest request, Model model) {

		if (UserDAO.getInstance().isUserExisting(username, password)) {
			
			User user = UserDAO.getInstance().getUserByUsername(username);
			HttpSession session = request.getSession();
			
			session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
			
			session.setAttribute("username", user.getUsername());
			request.setAttribute("username", user.getUsername());
			
			model.addAttribute(user);
			
			return "profile";
		} else {
			model.addAttribute("ErrorMessage", "Invalid user! Wrong username or password!");
			return "/login";
		}
	}
}