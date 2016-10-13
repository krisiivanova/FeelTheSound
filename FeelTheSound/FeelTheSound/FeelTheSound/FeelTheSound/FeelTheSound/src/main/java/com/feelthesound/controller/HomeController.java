package com.feelthesound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feelthesound.model.ISong;
import com.feelthesound.model.DAOs.SongDAO;

@Controller
public class HomeController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
//		ISong lastAddedInSite = SongDAO.getInstance().getLastAdded();
//
//		System.err.println(lastAddedInSite.getArtist());
//		System.err.println(lastAddedInSite.getName());
//
//		model.addAttribute("lastAddedInSite", lastAddedInSite);
		
		return "index";
	}
}
