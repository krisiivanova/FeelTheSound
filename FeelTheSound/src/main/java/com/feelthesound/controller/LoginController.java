package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

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
public class LoginController {

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, HttpSession httpSession, BindingResult result) {
		try {
			if (new UserDAO().isUserExisting(user)) {
				httpSession.setAttribute("user", user);
					return "successfulLogin";
				}
			
			return "failedLogin";
			
		} catch (ConnectionException e) {
			return "redirect:/pages/404.html";
		} catch (UserException e) {
			System.out.println("4");
			return "loginFailed";
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String showLogged(Model model) {
		model.addAttribute(new User());
		return "login";
	}
}