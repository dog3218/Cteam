package com.hanul.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app_member.MemberVO;
import web_notice.NoticeDAO_MJ;
import web_notice.NoticePage;
import web_notice.NoticeVO;

@Controller
public class Web_NoticeController {
	
	@Autowired private NoticeDAO_MJ service;
	@Autowired private NoticePage page;
	
	// 공지사항 글 수정 저장처리 요청
	@RequestMapping ("/update.no")
	public String update(NoticeVO vo, Model model
			, HttpSession session, String attach) {
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		service.notice_update(vo);
		
		model.addAttribute("uri", "detail.no");
		model.addAttribute("id", vo.getId());
		return "redirect";
	}
	
	// 공지사항 글 수정 화면 요청
	@RequestMapping ("/modify.no")
	public String modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.notice_detail(id)) ;
		return "notice/noti_modify";
	}
	
	// 공지사항 글 삭제 처리 요청
	@RequestMapping ("/delete.no")
	public String delete (int id, HttpSession session, Model model) {
		
		// 해당 공지사항 글을 DB에서 삭제한 후 목록화면으로 연결
		service.notice_delete(id);
	//	return "redirect:list.no";
		
		model.addAttribute("uri", "list.no");
		model.addAttribute("page", page);
		return "redirect";
		
	}
	
	
	// 공지사항 상세화면 요청
	@RequestMapping ("/detail.no")
	public String detail(int id, Model model) {
		
		// 조회수 증가 처리
		service.notice_read(id);
		
		// 해당 공지사항 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.notice_detail(id) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "notice/noti_detail";
	}
	
	
	// 공지사항 글 신규 저장 처리 요청
	@RequestMapping ("/insert.no")
	public String insert(NoticeVO vo, HttpSession session ) {
		
		MemberVO member =  (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getEmail() );
		
		// 화면에서 입력한 정보를 DB에 신규 저장한 후 목록화면 연결
		service.notice_insert(vo);
		return "redirect:list.no";
	}
	
	// 공지사항 글쓰기 화면 요청
	@RequestMapping ("/new.no")
	public String notice() {
		return "notice/noti_new";
	}
	
	// 공지사항 목록화면 요청
	@RequestMapping ("/list.no")
	public String list(HttpSession session
			, String search, String keyword
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "list") String viewType
			, Model model) {
		session.setAttribute("category", "no");
		
		// DB에서 공지사항 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		page.setSearch(search);		// 검색 조건
		page.setKeyword(keyword);	// 검색어
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수
		page.setViewType(viewType);	// 게시판 형태
		model.addAttribute("page", service.notice_list(page) );
		return "notice/noti_list";
	}
}
