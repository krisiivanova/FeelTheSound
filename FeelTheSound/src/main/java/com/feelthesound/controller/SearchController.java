package com.feelthesound.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feelthesound.model.ISong;
import com.feelthesound.model.DAOs.SongDAO;

@Controller
public class SearchController {

	@Autowired
	SongDAO songDao;

	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public String searchPage() {
		return "search";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/text")
	public ModelAndView getSubView(@RequestParam("text") String text, Model model, HttpSession session) {
		ModelAndView modelAndView = null;

		try {
			modelAndView = new ModelAndView("searchListNotLogged");
			List<ISong> songList = songDao.getSongsBySearchText(text);
			modelAndView.addObject("songs", songList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}
}
