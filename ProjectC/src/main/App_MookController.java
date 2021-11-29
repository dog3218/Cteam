package com.hanul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import app_board.BoardDAO_GM;
import app_board.BoardVO;
import app_community.CommunityVO;

@Controller
public class App_MookController {
	@Autowired private BoardDAO_GM dao_gm;
	@Autowired @Qualifier("cteam") SqlSession sql;
	
	@RequestMapping("/all")
	public void listAll(HttpSession session, Model model, HttpServletResponse response) {
		List<BoardVO> list = dao_gm.board_all();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
	}
	
	@RequestMapping("/musical")
	public void listMusical(HttpSession session, Model model, HttpServletResponse response) {
	
	
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_musical();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
	}
	
	@RequestMapping("/opera")
	public void listOpera(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_opera();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@RequestMapping("/play")
	public void listPay(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_play();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@RequestMapping("/exhibition")
	public void listExhibition(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_exhibition();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@RequestMapping("/concert")
	public void listConcert(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_concert();
//		model.addAttribute("all", service.board_all());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	@RequestMapping("/CommuInsert")
	public void commuInsert (HttpServletRequest req ,HttpSession session, Model model, HttpServletResponse response) {
		
		String email = req.getParameter("email");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String subject = req.getParameter("subject");
		
		System.out.println(email);
		CommunityVO vo = new CommunityVO();
		vo.setWriter(email);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setSubject(subject);
		
		
		int success = sql.insert("app_community.mapper.insert_show_gm", vo);
		
		Gson gson = new Gson();
		String json = gson.toJson(success);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println(email);
		System.out.println(title+"이 담겨서 옴");
		
		
		
	}
}
