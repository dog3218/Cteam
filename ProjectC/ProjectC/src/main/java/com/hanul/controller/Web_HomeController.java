package com.hanul.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class Web_HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(Web_HomeController.class);
	
	@RequestMapping("/error")
	public String error (HttpServletRequest req, Model model) {
		
		Throwable error = (Throwable) req.getAttribute("javax.servlet.error.exception"); 
		StringBuffer msg = new StringBuffer();
		
		while( error != null ) {
			msg.append("<p>").append(error.getMessage() ).append("</p>");
			error = error.getCause();	// exception 이 발생한 근본적인 원인을 리턴
		}
		
		model.addAttribute("msg", msg.toString());
		
		int code = (Integer) req.getAttribute("javax.servlet.error.status_code");
		return "error/" + (code == 404 ? 404 : "common");
	}
	
	
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model, HttpSession session) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * // session.setAttribute("category", ""); session.removeAttribute("category");
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * return "home"; }
	 */
	
	@RequestMapping("/")
	public String Intro() {
		
		
		return "home";
	}
}
