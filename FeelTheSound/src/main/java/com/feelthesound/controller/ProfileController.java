package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

@Controller
public class ProfileController {
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(@ModelAttribute("user") User user, Model model, HttpSession session) {
			try {
				int countOfFollowers = new UserDAO().getUserFollowersCount(user);
				int countOfFollowing = new UserDAO().getUserFollowingCount(user);
				
				User user1 = (User) session.getAttribute("user");
				model.addAttribute("username", user1.getUsername());
				model.addAttribute("followers", countOfFollowers);
				model.addAttribute("following", countOfFollowing);
				
				return "profile";
				
				
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "profile";
	}
}
