package com.feelthesound.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;

@Controller

public class UploadProfilePicController {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String showUploadPage() {
		return "fileUpload";
	}

	public static final String UPLOAD_LOCATION = "C:\\Users\\user\\Desktop\\projectFeelTheSoud\\files";

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, ModelMap model,
			HttpSession httpSession) throws IOException {
		String[] path = multipartFile.getOriginalFilename().split("\\\\");
		String fileName = path[path.length - 1];

		User user1 = (User) httpSession.getAttribute("user");
		user1.setPhoto(fileName);

		UserDAO userDAO = new UserDAO();
		userDAO.insertProfilePic(user1);

		FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));

		return "redirect:/profile";
	}
}
