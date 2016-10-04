package com.feelthesound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.ConnectionException;

@Controller
public class ProfileController {
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(@ModelAttribute("followers") String followers, User user, Model model){
		
		try {
			int count = new UserDAO().getUserFollowersCount(user);
			model.addAttribute("followers", count);
			return "profile";
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
