package com.hanul.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app_community.CommunityPage;
import web_board.BoardDAO_web_GW;
import web_board.BoardPage;
import web_community.CommunityDAO_MJ;

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
	
	@Autowired private CommunityPage comm_page;
	@Autowired private BoardPage bo_page;
	@Autowired private BoardDAO_web_GW bo_dao;
	@Autowired private CommunityDAO_MJ co_dao;
	
	@RequestMapping("/")
	public String Intro(Model model) {
		
		comm_page.setCurPage(1);
		model.addAttribute("page", co_dao.community_list(comm_page));
		
		bo_page.setCurPage(1);
		bo_page.setViewType("grid");
		model.addAttribute("page",  bo_dao.board_list(bo_page));

		
		return "home";
	}
}
