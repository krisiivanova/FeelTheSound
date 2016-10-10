package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.DAOs.UserDAO;

@Controller
public class ProfileController {
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
<<<<<<< HEAD
	public String profile(@ModelAttribute("user") User user, Model model, HttpSession session) {
		
		String username = (String)session.getAttribute("username");
		if (!UserDAO.getInstance().hasUsername(username)) {
			return "404";
		}
		
		IUserDAO userDAO = UserDAO.getInstance();

		int countOfFollowers = userDAO.getUserFollowersCount(user);
		int countOfFollowing = userDAO.getUserFollowingCount(user);

		User userInSession = (User) session.getAttribute("user");
		session.setAttribute("user", userInSession);

		model.addAttribute("username", userInSession.getUsername());
=======
	public String profile(Model model, HttpSession session) {
		User userInSession = (User) session.getAttribute("user");
		UserDAO userDAO =  UserDAO.getInstance();
		
		int countOfFollowers = userDAO.getUserFollowersCount(userInSession);
		int countOfFollowing = userDAO.getUserFollowingCount(userInSession);
		
		userInSession.setPhoto(userDAO.getProfilePhoto(userInSession));
		
		System.out.println(userInSession);
		model.addAttribute("user", userInSession);
>>>>>>> d3bea165480cfe2a81670ef634080497931ec9ec
		model.addAttribute("followers", countOfFollowers);
		model.addAttribute("following", countOfFollowing);

		return "profile";
	}
}


