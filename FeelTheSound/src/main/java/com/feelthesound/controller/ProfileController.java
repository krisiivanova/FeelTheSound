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
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.DAOs.PlaylistDAO;
import com.feelthesound.model.DAOs.UserDAO;

@Controller
public class ProfileController {

	@Autowired
	ISongDAO songDao;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		try {
			User userInSession = (User) session.getAttribute("user");
			IUserDAO userDAO = UserDAO.getInstance();
			
			userInSession.setPhoto(userDAO.getProfilePhoto(userInSession));
			model.addAttribute("user", userInSession);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "profile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/songs")
	public ModelAndView getSubView(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = new ModelAndView("songsList");

		List<ISong> userSongs = songDao.getSongsByUser(userId);

		modelAndView.addObject("songs", userSongs);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/liked")
	public ModelAndView getLiked(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = new ModelAndView("songsList");

		System.out.println("USER ID : " + userId);

		List<ISong> userLikedSongs = songDao.getUserLikedSongs(userId);

		modelAndView.addObject("songs", userLikedSongs);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/myPlaylists")
	public ModelAndView getAllPlaylists(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = new ModelAndView("playlists");

		System.out.println("USER ID : " + userId);

		List<IPlaylist> playlists = PlaylistDAO.getInstance().getAllPlaylists(userId);

		modelAndView.addObject("playlists", playlists);

		return modelAndView;
	}
}
