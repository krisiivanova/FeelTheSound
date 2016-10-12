package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.LikeDAO;
import com.feelthesound.model.DAOs.UserDAO;

@Controller
public class LikeController {
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public void like(@RequestParam(value = "songId") Integer songId, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			int userId = UserDAO.getInstance().getUserById(user);

			LikeDAO.getInstance().likeSong(songId, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
