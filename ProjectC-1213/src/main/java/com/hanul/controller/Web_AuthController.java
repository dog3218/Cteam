package com.hanul.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app_community.CommunityVO;
import app_member.MemberVO;
import web_auth.AuthDAO;
import web_auth.AuthPage;
import web_auth.AuthVO;
import web_common.CommonService;

@Controller
public class Web_AuthController {

	@Autowired
	private AuthDAO service;
	@Autowired
	private AuthPage page;
	@Autowired
	private CommonService common;
	
	//기획자 인증글 detail에서 인증하기 누르면 type 바뀜
	@RequestMapping("/check.auth")
	public String check(int no, String writer) {
		service.auth_check(no);		 
		service.auth_check(writer);		
		return"redirect:auth_list.admin";
	}
	

	// 기획자 인증 글 삭제 처리 요청
	@RequestMapping ("/delete.auth")
	public String delete (int no, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적인 영역에서 삭제
		AuthVO vo = service.auth_detail(no);
		if (vo.getFilename1() != null) {
			File file = new File( session.getServletContext().getRealPath("resources")
					+ "/" + vo.getFilename1() );
			if (file.exists()) file.delete();
		}
		
		// 해당 기획자 인증글을 DB에서 삭제한 후 목록화면으로 연결
		service.auth_delete(no);
	//	return "redirect:list.co";
		
		model.addAttribute("uri", "mypage.user");
		model.addAttribute("page", page);
		return "redirect";
	}

	// 기획자 인증 글 수정 저장처리 요청
	@RequestMapping("/update.auth")
	public String update(AuthVO vo, Model model, HttpSession session, MultipartFile file, String attach) {

		// 원글에 첨부 파일이 있는지
		AuthVO auth = service.auth_detail(vo.getNo());
		String uuid = session.getServletContext().getRealPath("resource") + "/" + auth.getFilepath1();

		// 파일을 첨부하지 않은 경우
		if (file.isEmpty()) {
			// 원래부터 첨부된 파일이 없는 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if (attach.isEmpty()) {
				// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
				if (auth.getFilepath1() != null) {
					File f = new File(uuid);
					if (f.exists())
						f.delete(); // 파일이 존재하면 파일 삭제
				}
			} else {
				// 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFilepath1(auth.getFilepath1());
			}
		} else {
			// 파일을 첨부한 경우
			vo.setFilepath1(auth.getFilepath1());

			// 원래 첨부 되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
			if (auth.getFilepath1() != null) {
				File f = new File(uuid);
				if (f.exists())
					f.delete(); // 파일이 존재하면 파일 삭제
			}

		}
		
		//화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		service.auth_update(vo);
		
		model.addAttribute("uri", "detail.auth");
		model.addAttribute("id", vo.getNo());
		return "redirect";
		

	}
	

	// 기획자 인증 글 수정 화면 요청
	@RequestMapping("/modify.auth")
	public String modify(int no, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정 화면에 출력
		model.addAttribute("vo", service.auth_detail(no));
		return "auth/auth_modify";
	}

	/*
	 * // 기획자 인증 첨부파일 다운로드 요청
	 * 
	 * @RequestMapping("/download.auth") public void download(int id, HttpSession
	 * session, HttpServletResponse response ) {
	 * 
	 * // 해당 글의 첨부파일 정보를 DB에서 조회해와 해당 파일을 서버로부터 다운로드 함. AuthVO vo =
	 * service.auth_detail(id); common.fileDownload(vo.getFilename1(),
	 * vo.getFilename1(), session, response); }
	 */
	// 기획자 인증 첨부파일 다운로드 요청
	@RequestMapping("/download.auth")
	public void download(int id, HttpSession session, HttpServletResponse response ) {
		
		// 해당 글의 첨부파일 정보를 DB에서 조회해와 해당 파일을 서버로부터 다운로드 함.
		AuthVO vo = service.auth_detail(id);
		common.fileDownload(vo.getFilename1(), vo.getFilepath1(), session, response);		
	}
	

	// 기획자 인증 상세화면 요청==>관리자
	@RequestMapping("/detail_admin.auth")
	public String detail_admin(int no, Model model) {

		// 기획자 인증글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.auth_detail(no));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "auth/auth_detail_admin";
	}
	
	// 기획자 인증 상세화면 요청==>일반유저
	@RequestMapping("/detail.auth")
	public String detail(int no, Model model) {
		
		// 기획자 인증글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.auth_detail(no));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "auth/auth_detail";
	}
	

	// 기획자 인증 글 신규 저장 처리 요청
	@RequestMapping("/insert.auth")
	public String insert(AuthVO vo, MultipartFile file, HttpSession session) {
		
		// 첨부된 파일이 있다면
		if ( ! file.isEmpty()) {
			vo.setFilename1(file.getOriginalFilename());
			vo.setFilepath1( common.fileUpload("auth", file, session) );
		}
		
		MemberVO member =  (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getEmail() );
		
		// 화면에서 입력한 기획자 인증 정보를 DB에 신규 저장한 후 목록화면 연결
		service.auth_insert(vo);
		
		return "redirect:mypage.user";
	}
	
	// 기획자 인증 글쓰기 화면 요청(글을 하나도 쓰지 않은 경우)
	@RequestMapping("/new.auth")
	public String auth_new() {
		return "auth/auth_new";
	}

	// 기획자 인증 목록화면 요청 ==> 관리자만 볼 수 있음
	@RequestMapping("/auth_list.admin")
	public String list(HttpSession session, String search, String keyword,
			@RequestParam(defaultValue = "10") int pageList, @RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "list") String viewType, Model model) {

		session.setAttribute("category", "admin");

		// DB에서 커뮤니티 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage); // 현재 페이지를 담음

		page.setSearch(search); // 검색 조건
		page.setKeyword(keyword); // 검색어
		page.setPageList(pageList); // 페이지당 보여질 글 목록 수
		page.setViewType(viewType); // 게시판 형태s
		model.addAttribute("page", service.auth_list(page));

		return "auth/auth_list_admin";

	}
	
	

}
