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
	public String profile(Model model, HttpSession session) {
		User userInSession = (User) session.getAttribute("user");
		UserDAO userDAO =  UserDAO.getInstance();
		
		int countOfFollowers = userDAO.getUserFollowersCount(userInSession);
		int countOfFollowing = userDAO.getUserFollowingCount(userInSession);
		
		userInSession.setPhoto(userDAO.getProfilePhoto(userInSession));
		
		System.out.println(userInSession);
		model.addAttribute("user", userInSession);
		model.addAttribute("followers", countOfFollowers);
		model.addAttribute("following", countOfFollowing);
		
		return "profile";
	}
}
