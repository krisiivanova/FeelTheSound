package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.DAOs.LikeDAO;

@Controller
public class LikeController {
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public void like(@RequestParam(value = "songId") Integer songId, @RequestParam(value = "userId") Integer userId,
			HttpSession session) {
		try {
			LikeDAO.getInstance().likeSong(songId, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}