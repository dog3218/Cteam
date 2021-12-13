package com.hanul.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app_member.MemberVO;
import web_qna.QnaDAO_MJ;
import web_qna.QnaPage;
import web_qna.QnaVO;

@Controller
public class Web_QnaController {
	
	@Autowired private QnaDAO_MJ service;
	@Autowired private QnaPage page;
	
	//답글 저장 처리 요청
	@RequestMapping("/reply_insert.qna")
	public String reply_insert(QnaVO vo, HttpSession session, Model model) {
		// 로그인 된 사용자의 id를 가져와 글쓴이(writer)에 담기 위한 처리
		vo.setReplyid( ( (MemberVO) session.getAttribute("loginInfo")).getEmail() );
		vo.setRoot(vo.getId());
		// 화면에서 입력한 정보를 DB에 저장한 후 화면으로 연결(출력)
		service.qna_reply_insert(vo);
		
		model.addAttribute("uri", "list.qna");
		model.addAttribute("page", page);
		return "redirect";
		
	}
	
	// 공지글 답글 작성 화면 요청
	@RequestMapping ("/reply.qna")
	public String reply(int id, Model model) {
		//원글의 상세 정보를 DB에서 조회하여 답글 화면에 출력
		model.addAttribute("vo", service.qna_detail(id));
		return "qna/qna_reply";
	}
	
	// 질문 수정 저장 처리 요청
	@RequestMapping ("/update.qna")
	public String update(QnaVO vo, HttpSession session, String attach) {
		
		// 화면에서 변경 입력한 정보를 DB에 변경 저장한 후 상세화면으로 연결
		service.qna_update(vo);		
		return "redirect:detail.qna?id=" + vo.getId();
	}
	
	
	// 질문 수정 화면 요청
	@RequestMapping ("/modify.qna")
	public String modify (int id, Model model) {
		model.addAttribute("vo", service.qna_detail(id));
		return "qna/qna_modify";
	}
	
	// 질문 삭제처리 요청
	@RequestMapping ("/delete.qna")
	public String delete (int id, HttpSession session, Model model) {
		
		// 질문에 대한 모든 정보 조회
//		QnaVO qna = service.qna_detail(id);		
		// 해당 공지글 정보를 DB에서 삭제한 후 목록화면으로 연결
		service.qna_delete(id);
		model.addAttribute("uri", "list.qna");
		model.addAttribute("page", page);
		
		return "redirect";
	}
	
	
	// 질문 상세화면 요청
	@RequestMapping ("/detail.qna")
	public String detail(int id, Model model) {
		
		// 상세화면 요청 전 조회수 증가
		service.qna_read(id);
		
		// 선택한(id) 질문 정보를 DB에서 조회해 와 상세화면 출력
		model.addAttribute("vo", service.qna_detail(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		
		return "qna/qna_detail";
	}
	
	
	// 신규 질문 저장 처리 요청
	@RequestMapping ("/insert.qna")
	public String insert (QnaVO vo, HttpSession session, Model model) {
		System.out.println(vo.getTitle());
		
//		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
//		vo.setWriter(member.getId());
		
		// 로그인 된 사용자의 id를 가져와 글쓴이(writer)에 담기 위한 처리
		vo.setWriter( ( (MemberVO) session.getAttribute("loginInfo")).getEmail() );
		
		// 화면에서 입력한 정보를 DB에 저장한 후 화면으로 연결(출력)
		service.qna_insert(vo);
		model.addAttribute("uri", "list.qna");
		model.addAttribute("page", page);
		
		return "redirect"; // 리턴 시 공지사항 목록 화면으로 이동 처리
	}
	
	
	// 신규 공지사항 입력 화면 요청
	@RequestMapping ("/new.qna")
	public String notice() {
		return "qna/qna_new";
	}
	
	
	@RequestMapping ("/list.qna")
	public String list(HttpSession session, Model model
			 , @RequestParam (defaultValue = "1") int curPage 
			 , String search, String keyword) {
		
		// 공지글 처리 중 임의로 로그인해 두기 **주석 빼야할지 말아야할지
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("id",
		 * "admin"); map.put("password", "admin"); session.setAttribute("loginInfo",
		 * member.member_login(map));
		 */
		
		session.setAttribute("category", "qna");
		// DB에서 공지글 목록을 조회해와 목록화면에 출력
//		model.addAttribute("list", service.notice_list());
		
		// 현재 페이지에 대한 정보를 담음.
		page.setCurPage(curPage);
		// 검색 조건과 검색어 정보를 담음.
		page.setSearch(search);
		page.setKeyword(keyword);
		
		
		// DB에서 공지글 목록을 조회한 후 목록화면에 출력
		model.addAttribute("page", service.qna_list(page));
		
		return "qna/qna_list";
	}
}
