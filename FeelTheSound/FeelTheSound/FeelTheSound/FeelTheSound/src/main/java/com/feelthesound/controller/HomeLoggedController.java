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

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.ISong;
import com.feelthesound.model.IUser;
import com.feelthesound.model.DAOs.IPlaylistDAO;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.exceptions.UserException;

@Controller
public class HomeLoggedController {

	@Autowired
	ISongDAO songDao;

	@Autowired
	IPlaylistDAO playlistDao;

	@Autowired
	IUserDAO userDao;

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchText")
	public ModelAndView getSubView(@RequestParam("searchText") String searchText, Model model, HttpSession session) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("searchList");
			List<ISong> songList = songDao.getSongsBySearchText(searchText);
			modelAndView.addObject("songs", songList);

			IUser user = (IUser) session.getAttribute("user");
			int userId = userDao.getUserById(user);

			List<IPlaylist> playlists = playlistDao.getAllPlaylists(userId);

			modelAndView.addObject("playlists", playlists);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}
}
