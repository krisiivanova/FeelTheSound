package com.feelthesound.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feelthesound.model.ISong;
import com.feelthesound.model.DAOs.ISongDAO;

@Controller
public class HomeLoggedController {

	@Autowired
	ISongDAO songDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchText")
	public ModelAndView getSubView(@RequestParam("searchText") String searchText,Model model) {
		ModelAndView modelAndView = new ModelAndView("searchList");

		List<ISong> songList = songDao.getSongsBySearchText(searchText);
		
		modelAndView.addObject("songs",songList);
		return modelAndView;
	}
}
