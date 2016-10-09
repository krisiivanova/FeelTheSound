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