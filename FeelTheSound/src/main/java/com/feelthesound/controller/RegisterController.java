package com.feelthesound.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.validators.EmailValidator;
import com.feelthesound.model.validators.PasswordValidator;
import com.feelthesound.model.validators.UsernameValidator;

@Controller
public class RegisterController {
	
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String registerPage() {
		return "register";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "password2") String password2,
			@RequestParam(value = "email") String email, HttpServletRequest request, Model model) {

		boolean isValid = isValidReg(model, username, password, password2, email);
		if (isValid) {
			UserDAO.getInstance().saveUser(username, password2, email);
			
			return "profile";
		
		} else {
			return "register";
		}
	}
	
	private boolean isValidReg(Model model, String username, String password, String password2, String email) {
		if (username != null && !username.isEmpty() && password != null && !password.isEmpty() && password2 != null
				&& !password2.isEmpty() && email != null && !email.isEmpty()) {
			if (UserDAO.getInstance().hasThisEmail(email)) {
				model.addAttribute("ErrorRegMessage", "Email already exists.");
				return false;
			}

			if (UserDAO.getInstance().hasThisUsername(username)) {
				model.addAttribute("ErrorRegMessage", "Username already exists.");
				return false;
			}

			if (password.equals(password2)) {
				if (new UsernameValidator().validate(username)) {
					if (new PasswordValidator().validate(password)) {
						if (new EmailValidator().validate(email)) {
							return true;
						}
						model.addAttribute("ErrorRegMessage", EmailValidator.EMAIL_MESSAGE);
					} else {
						model.addAttribute("ErrorRegMessage", PasswordValidator.PASSWORD_MESSAGE);
					}
				} else {
					model.addAttribute("ErrorRegMessage", UsernameValidator.USERNAME_MESSAGE);
				}
			} else {
				model.addAttribute("ErrorRegMessage", "Your password doesn't match");
			}
		} else {
			model.addAttribute("ErrorRegMessage", "All fields are required!");
		}
		
		return false;
	}
}
