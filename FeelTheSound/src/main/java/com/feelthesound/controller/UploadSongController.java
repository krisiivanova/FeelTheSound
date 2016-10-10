<<<<<<< HEAD
//package com.feelthesound.controller;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.feelthesound.model.Song;
//import com.feelthesound.model.User;
//import com.feelthesound.model.DAOs.SongDAO;
//import com.feelthesound.model.DAOs.UserDAO;
//
//@Controller
//public class UploadSongController {
//
//	private static final String UPLOAD_LOCATION = "C:\\Users\\Kristina Ivanova\\Desktop\\songs\\";
//
//	@RequestMapping(value = "/uploadMusic", method = RequestMethod.GET)
//	public String showUploadPage() {
//		return "uploadSong";
//	}
//
//	@RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
//	public String singleFileUpload(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile multipartFile,
//			ModelMap model, HttpSession httpSession) throws IOException {
//		
//		String[] path = multipartFile.getOriginalFilename().split("\\\\");
//		String fileName = path[path.length - 1];
//
//		File photo = new File(UPLOAD_LOCATION + fileName);
//		FileCopyUtils.copy(multipartFile.getBytes(), photo);
//
//		User userInSession = (User) httpSession.getAttribute("user");
//		httpSession.setAttribute("user", userInSession);
//
//		Song song = new Song(id, name, artist, genre, userId)
//		SongDAO.getInstance().insertSong(song)
//
//		return "redirect:/profile";
//	}
//}
=======
package com.feelthesound.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadSongController {

	private static final String UPLOAD_LOCATION = "C:\\Users\\user\\Desktop\\projectFeelTheSoud\\files\\";

	@RequestMapping(value = "/uploadMusic", method = RequestMethod.GET)
	public String showUploadPage() {
		return "uploadSong";
	}

	@RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, ModelMap model)
			throws IOException {
		String[] path = multipartFile.getOriginalFilename().split("\\\\");
		String fileName = path[path.length - 1];
		FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));

		return "uploadSong";
	}
}
>>>>>>> d3bea165480cfe2a81670ef634080497931ec9ec
