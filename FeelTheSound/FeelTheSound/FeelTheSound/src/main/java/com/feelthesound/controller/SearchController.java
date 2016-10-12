//package com.feelthesound.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.feelthesound.model.ISong;
//import com.feelthesound.model.DAOs.SongDAO;
//import com.google.gson.Gson;
//
//@Controller
//public class SearchController{
//	@RequestMapping(value = "/Search", method = RequestMethod.GET)
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/json");
//		response.setCharacterEncoding("UTF-8");
//		
//		String prefix = request.getParameter("prefix");
//		
//		List<ISong> songs = SongDAO.getInstance().getAllSongsWithPrefix(prefix);
//		
//		response.getWriter().print(new Gson().toJson(songs));
//	}
//}
