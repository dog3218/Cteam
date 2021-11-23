package com.hanul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import mook.MBoardServiceImpl;
import mook.MBoardVO;

@Controller
public class MookController {
	@Autowired private MBoardServiceImpl service;
	
	@RequestMapping("/all")
	public void listAll(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<MBoardVO> list = service.board_all();
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
	
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<MBoardVO> list = service.board_musical();
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
		List<MBoardVO> list = service.board_opera();
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
		List<MBoardVO> list = service.board_play();
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
		List<MBoardVO> list = service.board_exhibition();
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
		List<MBoardVO> list = service.board_concert();
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
}
