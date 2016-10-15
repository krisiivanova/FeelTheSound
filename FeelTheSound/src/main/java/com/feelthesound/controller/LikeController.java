package com.feelthesound.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feelthesound.model.DAOs.LikeDAO;

@Controller
public class LikeController {
	
	@Autowired
	LikeDAO likeDao;

	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public void like(@RequestParam(value = "like") String like, @RequestParam(value = "songId") Integer songId,
			@RequestParam(value = "userId") Integer userId, HttpSession session) {

		try {
			if (like.equalsIgnoreCase("Like"))
				likeDao.likeSong(songId, userId);
			
			if (like.equalsIgnoreCase("Unlike"))
				likeDao.dislikeSong(songId, userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}