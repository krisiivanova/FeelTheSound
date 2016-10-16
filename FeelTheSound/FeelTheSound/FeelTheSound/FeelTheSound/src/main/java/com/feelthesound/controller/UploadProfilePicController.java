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

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.IUserDAO;

@Controller
public class UploadProfilePicController {

	public static final String UPLOAD_LOCATION = "D:\\files\\";
	
	@Autowired
	IUserDAO userDao;
	

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String showUploadPage() {
		return "fileUpload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, ModelMap model,
			HttpSession httpSession) throws IOException {
		try {
			String[] path = multipartFile.getOriginalFilename().split("\\\\");
			String fileName = path[path.length - 1];

			File photo = new File(UPLOAD_LOCATION + fileName);
			FileCopyUtils.copy(multipartFile.getBytes(), photo);

			User userInSession = (User) httpSession.getAttribute("user");

			userDao.insertProfilePic(fileName, userInSession);

		} catch (Exception e) {
			e.printStackTrace();
			 return "profile";
		}

		return "redirect:/profile";
	}
}
