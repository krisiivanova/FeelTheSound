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
public class ProfileController {
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(@ModelAttribute("user") User user, Model model, HttpSession session) {
		UserDAO userDAO =  UserDAO.getInstance();
		
		int countOfFollowers = userDAO.getUserFollowersCount(user);
		int countOfFollowing = userDAO.getUserFollowingCount(user);

		User userInSession = (User) session.getAttribute("user");
		session.setAttribute("user", userInSession);
		
		model.addAttribute("username", userInSession.getUsername());
		model.addAttribute("followers", countOfFollowers);
		model.addAttribute("following", countOfFollowing);
		
		return "profile";
	}
}
