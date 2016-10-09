package com.feelthesound.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/editProfile")
public class EditProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public String showEditPage() {
		return "edit";
	}
}
