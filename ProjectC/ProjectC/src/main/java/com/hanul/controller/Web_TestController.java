package com.hanul.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Web_TestController {
	
	//어떤 객체를 연결하려면 @RequestMapping을 선언하면
	//메소드 호출 시 first 요청이 있으면 아래 메소드를 동작시킴
	@RequestMapping("/first")
	public String first(Model model) {
		//날짜 포맷 형식으로 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String today = sdf.format(new Date());
		model.addAttribute("today",today);
		return "index";
	}
	
	@RequestMapping("/second")
	public ModelAndView second() {
		
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("a hh시 mm분 ss 초");
		String now = sdf.format(new Date());
		model.addObject("now", now); // 연결할 페이지 데이터를 어떤이름을 추가할 건지 입력
		model.setViewName("index");//응답할 페이지 지정
		
		return model;
	}
}

