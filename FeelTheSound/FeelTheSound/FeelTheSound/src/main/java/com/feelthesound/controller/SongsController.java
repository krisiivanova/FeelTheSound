//package com.feelthesound.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.feelthesound.model.User;
//
//@Controller
//public class SongsController {
//	@RequestMapping(value = "/MySongs")
//	public ModelAndView getMySongs(@ModelAttribute("user") User user, HttpServletRequest request ){
//		ModelAndView modelAndView = new ModelAndView("jsp/table");
//		Integer userId = ParamUtil.getInteger(request, "userId");
//		
//		return null;
//	}
//}
