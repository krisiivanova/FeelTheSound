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
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.IPlaylistDAO;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.validators.PlaylistValidator;

@Controller
public class PlaylistController {

	@Autowired
	ISongDAO songDao;

	@Autowired
	IPlaylistDAO playlistDao;

	@RequestMapping(value = "/Playlist", method = RequestMethod.GET)
	public String viewCreatePlaylist(Model model) {
		return "createPlaylist";
	}

	@RequestMapping(value = "/CreatePlaylist", method = RequestMethod.POST)
	public String createPlaylist(@RequestParam(value = "name") String name, HttpSession session, Model model) {
		try {
			User user = (User) session.getAttribute("user");

			if (validPlaylistName(model, name, user)) {
				playlistDao.addPlaylist(name, user);
				model.addAttribute("Message", "Playlist was created successfully");
				return "profile";
			}
		} catch (Exception e) {
			model.addAttribute("Message", "Playlist with this title already exist!");
		}

		return "createPlaylist";
	}

	@RequestMapping(value = "/deleteSong", method = RequestMethod.POST)
	public void deleteSong(@RequestParam(value = "songId") Integer songId, HttpSession session, Model model) {
		try {

			songDao.deleteSongFromPlaylist(songId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/AddSongToPlaylist", method = RequestMethod.GET)
	public void addSongToPlaylist(@RequestParam(value = "songId") Integer songId,
			@RequestParam(value = "userId") Integer userId, @RequestParam(value = "playlistId") Integer playlistId,
			HttpSession session) {
		try {
			System.out.println("Songs id : " + songId);
			System.out.println("User id : " + userId);
			System.out.println("Playlist id : " + playlistId);

			playlistDao.addSongInPlaylist(playlistId, songId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validPlaylistName(Model model, String name, User user) {
		if (name.isEmpty() || name == null) {
			model.addAttribute("Message", "Playlist's title is empty");
			return false;
		}

		if (new PlaylistValidator().validate(name)) {
			try {
				if (!playlistDao.ifPlaylistExist(user, name)) {
					return true;
				} else {
					model.addAttribute("Message", "Playlist with this name already exist!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			model.addAttribute("Message", PlaylistValidator.PLAYLIST_MESSAGE);
		}

		return false;
	}

	@RequestMapping(value = "/MyPlaylist", method = RequestMethod.GET)
	public ModelAndView getMyPlaylist(@RequestParam(value = "playlistId") String playlistId, HttpSession session,
			Model model) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("songsPlaylist");

			Integer id = Integer.valueOf(playlistId);

			List<ISong> songs = songDao.getSongsInPlaylist(id);

			modelAndView.addObject("songs", songs);

			System.out.println("Playlist " + playlistId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/DeletePlaylist", method = RequestMethod.POST)
	public ModelAndView deletePlaylist(@RequestParam(value = "playlistId") Integer playlistId, HttpSession session,
			Model model) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("playlists");

			playlistDao.deletePlaylist(playlistId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}
}
