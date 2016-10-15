package com.feelthesound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.ISong;
import com.feelthesound.model.DAOs.SongDAO;

@Controller
public class HomeController {

	@Autowired
	SongDAO songDao;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		try {
			ISong lastAddedInSite = songDao.getLastAdded();

			System.out.println("Last added on site:" + lastAddedInSite.getName());

			model.addAttribute("song", lastAddedInSite);
		} catch (Exception e) {
			e.printStackTrace();

			return "index";
		}
		return "index";
	}
}
