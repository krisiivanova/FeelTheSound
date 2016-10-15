package com.feelthesound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.UserException;

@Controller
public class LoginController {
	private static final int MAX_INACTIVE_INTERVAL = 30 * 60; // 30 minutes
	
	@Autowired
	UserDAO userDao;

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpServletRequest request, Model model) {
		try {
			if (userDao.isUserExisting(username, password)) {
				User user = userDao.getUserByUsername(username);
				request.getSession(false).invalidate();
				
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

				session.setAttribute("username", user.getUsername());
				session.setAttribute("user", user);
				request.setAttribute("username", user.getUsername());
				
				model.addAttribute(user);

				return "redirect:/profile";

			} else {
				model.addAttribute("ErrorMessage", "Invalid user! Wrong username or password!");
				return "/login";
			}
			
		} catch (Exception e) {
			return "/login";
		}
	}
}