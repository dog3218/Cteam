package com.hanul.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app_member.MemberVO;

@Controller
public class Web_FormController {
	
	@RequestMapping("member_login")
	public String member() {
		System.out.println("member_login");
		return "member/login";
	}
	
	@RequestMapping("login_result")
	public String login(String id, String pw) {
		 
		//로그인 성공시 home으로 이동
		if(id.equals("1234")&& pw.equals("1234")) {
			System.out.println("로그인 성공");
			//return "home";//forward 방식
			return "redirect:/";//redirect방식
		}
		//로그인 실패시 다시 로그인 페이지
		else{
			System.out.println("로그인 실패");
			/* return "member/login"; *///forward방식
			return "redirect:member";//redirect방식
		}
	}
	
	@RequestMapping("join")
	public String join() {
		
		
		return "member/join";
	}
	
	@RequestMapping("joinRequest")
	public String members(HttpServletRequest request, Model model) {
		
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("gender", request.getParameter("gender"));
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("method", "HttpServletParameter방식");
		
		return "member/info";
	}
	
	@RequestMapping("joinParam")
	public String members(@RequestParam String name,
			@RequestParam String gender,
			@RequestParam  String email, Model model) {
		/*
		 * model.addAttribute("name", name); model.addAttribute("gender", gender);
		 * model.addAttribute("email", email);
		 */
		model.addAttribute("method", "@RequestParam방식");
			
		return "member/info";
	}
	/*
	 * @RequestMapping("joinParam") public String members(@RequestParam String name,
	 * 
	 * @RequestParam String gender,
	 * 
	 * @RequestParam String email, Model model) { model.addAttribute("name", name);
	 * model.addAttribute("gender", gender); model.addAttribute("email", email);
	 * 
	 * return "member/info"; }
	 */	
	@RequestMapping("joinDataObject")
	public String members(MemberVO vo, Model model) {
		model.addAttribute("member", vo);
		model.addAttribute("method", "데이터객체(VO)방식");
		return "member/info";
	}
	
	@RequestMapping("joinPath/{name}/{gender}/{email}")
	public String members(Model model,
			 @PathVariable String name,
			 @PathVariable String gender,
			 @PathVariable String email) {
			
		/*model.addAttribute("name", name);
		 *  model.addAttribute("gender", gender);
		 * model.addAttribute("email", email);*/
			model.addAttribute("method", "@PathVariable");
		return "member/info";
	}
	
}
