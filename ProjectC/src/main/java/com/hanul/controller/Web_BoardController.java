package com.hanul.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app_board.BoardVO;
import app_member.MemberVO;
import web_board.BoardDAO_web_GW;
import web_board.BoardPage;

@Controller
public class Web_BoardController {
	
	@Autowired private BoardDAO_web_GW service;
	@Autowired private BoardPage page;
	
	// 공연정보 글 수정 저장처리 요청
	@RequestMapping ("/update.bo")
	public String board_update(BoardVO vo, Model model
			, HttpSession session, String attach) {
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		service.board_update(vo);
		
		model.addAttribute("uri", "detail.no");
		model.addAttribute("id", vo.getNo());
		return "redirect";
	}
	
	// 공연정보 글 수정 화면 요청
	@RequestMapping ("/modify.bo")
	public String board_modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.board_detail(id)) ;
		return "board/board_modify";
	}
	
	// 공연정보 글 삭제 처리 요청
	@RequestMapping ("/delete.bo")
	public String board_delete (int id, HttpSession session, Model model) {
		
		// 해당 공연정보 글을 DB에서 삭제한 후 목록화면으로 연결
		service.board_delete(id);
	//	return "redirect:list.no";
		
		model.addAttribute("uri", "list.no");
		model.addAttribute("page", page);
		return "redirect";
		
	}
	
	
	// 공연정보 상세화면 요청
	@RequestMapping ("/detail.bo")
	public String board_detail(int no, Model model) {
		System.out.println("상세화면 요청 번호: " +no);
		// 조회수 증가 처리
		service.board_read(no);
		
		// 해당 공연정보 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.board_detail(no) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "board/board_detail";
	}
	
	
	// 공연정보 글 신규 저장 처리 요청
	@RequestMapping ("/insert.bo")
	public String board_insert(BoardVO vo, HttpSession session ) {
		
		MemberVO member =  (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getEmail() );
		
		// 화면에서 입력한 정보를 DB에 신규 저장한 후 목록화면 연결
		service.board_insert(vo);
		return "redirect:list.bo";
	}
	
	// 공연정보 글쓰기 화면 요청
	@RequestMapping ("/new.bo")
	public String board_new() {
		return "board/new";
	}
	
	// 공연정보 목록화면 요청
	@RequestMapping ("/list.bo")
	public String board_list(HttpSession session
			, String search, String keyword
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "grid") String viewType
			, Model model) {
		session.setAttribute("category", "bo");
		
		// DB에서 공지사항 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		page.setSearch(search);		// 검색 조건
		page.setKeyword(keyword);	// 검색어
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수
		page.setViewType(viewType);	// 게시판 형태
		BoardPage boardPage = service.board_list(page);
		model.addAttribute("page",  boardPage);
		System.out.println(boardPage.getList().size());
		return "board/board_list";
	}
}
