package com.feelthesound.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.feelthesound.model.Song;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.DAOs.IUserDAO;

@Controller
public class UploadSongController {

	public static final String UPLOAD_LOCATION = "D:\\files\\";
	
	@Autowired
	IUserDAO userDao;
	
	@Autowired
	ISongDAO songDao;

	@RequestMapping(value = "/uploadMusic", method = RequestMethod.GET)
	public String showUploadPage() {
		return "uploadSong";
	}

	@RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("songName") String name, @RequestParam("artistName") String artistName,
			@RequestParam("janr") String genre, @RequestParam("file") MultipartFile multipartFile, ModelMap model,
			HttpSession httpSession) throws IOException {
		try {

			String[] path = multipartFile.getOriginalFilename().split("\\\\");
			String fileName = path[path.length - 1];
			FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));

			File song = new File(UPLOAD_LOCATION + fileName);
			FileCopyUtils.copy(multipartFile.getBytes(), song);

			User userInSession = (User) httpSession.getAttribute("user");
			int userId = userDao.getUserById(userInSession);
			
			System.out.println(name);
			System.out.println(artistName);
			System.out.println(genre);
			System.out.println(userId);
			System.out.println(fileName);
			System.out.println(UPLOAD_LOCATION);

			Song song1 = new Song(0, name, artistName, genre, userId, fileName);

			songDao.insertSong(song1);

			return "redirect:/profile";

		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}
}