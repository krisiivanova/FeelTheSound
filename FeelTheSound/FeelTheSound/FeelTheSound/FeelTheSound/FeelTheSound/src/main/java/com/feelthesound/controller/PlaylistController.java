package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.PlaylistDAO;
import com.feelthesound.model.validators.PlaylistValidator;

@Controller
public class PlaylistController {

	@RequestMapping(value = "/Playlist", method = RequestMethod.GET)
	public String viewCreatePlaylist(Model model) {
		return "createPlaylist";
	}

	@RequestMapping(value = "/CreatePlaylist", method = RequestMethod.POST)
	public String createPlaylist(@RequestParam(value = "name") String name, HttpSession session, Model model) {
		try {
			User user = (User) session.getAttribute("user");

			if (validPlaylistName(model, name, user)) {
				IPlaylist pl = PlaylistDAO.getInstance().addPlaylist(name, user);
				System.err.println("--------------" + pl.getId());

				model.addAttribute("Message", "Playlist was created successfully");
				return "profile";
			}
		} catch (Exception e) {
			model.addAttribute("Message", "Playlist with this title already exist!");
			return "index";
		}

		return "createPlaylist";
	}

	@RequestMapping(value = "/AddSongToPlaylist", method = RequestMethod.POST)
	public void addSongToPlaylist(@RequestParam(value = "songId") Integer songId,
			@RequestParam(value = "userId") Integer userId, @RequestParam(value = "playlistId") Integer playlistId,
			HttpSession session) {
		try {

			PlaylistDAO.getInstance().addSongInPlaylist(playlistId, songId);
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
			if (!PlaylistDAO.getInstance().ifPlaylistExist(user, name)) {
				return true;
			} else {
				model.addAttribute("Message", "Playlist with this name already exist!");
			}
		} else {
			model.addAttribute("Message", PlaylistValidator.PLAYLIST_MESSAGE);
		}

		return false;
	}
}
