package com.hanul.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app_community.CommentVO;
import app_community.CommunityPage;
import app_community.CommunityVO;
import app_member.MemberVO;
import web_common.CommonService;
import web_community.CommunityDAO_MJ;

@Controller
public class Web_CommunityController {
	
	@Autowired private CommunityDAO_MJ commu_gm;
	@Autowired private CommunityPage page;
	@Autowired private CommonService common;
	
	// 커뮤니티 글에 대한 댓글 목록조회 요청
	@RequestMapping ("/community/comment/list/{pno}")
	public String comment_list(@PathVariable int pno, Model model) {
		// 해당 글에 대한 댓글들을 DB에서 조회한다.
		model.addAttribute("list", commu_gm.comment_list(pno) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "community/comment/comment_list";
	}
	
	
	// 커뮤니티 글에 대한 댓글 저장처리 요청
	@ResponseBody
	@RequestMapping ("/community/comment/regist")
	public boolean comment_regist(CommentVO vo, HttpSession session) {
		// 작성자의 경우 member의 id 값을 담아야 하므로 로그인 정보 확인
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter(member.getEmail());
		
		// 화면에서 입력한 댓글 정보를 DB에 저장한 후 저장 여부를 반환
		return commu_gm.comment_insert(vo) == 1 ? true : false;
		// 반환결과가 1 이면 true 아니면 false
	}
	
	
	// 커뮤니티 글 수정 저장처리 요청
	@RequestMapping ("/update.co")
	public String update(CommunityVO vo, Model model
			, HttpSession session, MultipartFile file, String attach) {
		
		// 원 글에 첨부 파일이 있는지...
		CommunityVO community = commu_gm.community_detail(vo.getId());
		String uuid = session.getServletContext().getRealPath("resources") + "/" + community.getFilepath1();
		
		// 파일을 첨부하지 않은 경우
		if ( file.isEmpty() ) {
			// 원래부터 첨부된 파일이 없는 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if ( attach.isEmpty() ) {
				// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
				if ( community.getFilename1() != null) {
					File f = new File(uuid);
					if ( f.exists() ) f.delete();	// 파일이 존재하면 파일 삭제
				}
			} else {
				// 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFilename1( community.getFilename1() );
				vo.setFilepath1( community.getFilepath1() );
			}
		} else {
			// 파일을 첨부한 경우
			vo.setFilename1( community.getFilename1() );
			vo.setFilepath1( community.getFilepath1() );
			
			// 원래 첨부 되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
			if ( community.getFilename1() != null) {
				File f = new File(uuid);
				if ( f.exists() ) f.delete();	// 파일이 존재하면 파일 삭제
			}
		}
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		commu_gm.community_update(vo);
		
		model.addAttribute("uri", "detail.co");
		model.addAttribute("id", vo.getId());
		return "community/redirect";
	}
	
	// 커뮤니티 글 수정 화면 요청
	@RequestMapping ("/modify.co")
	public String modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", commu_gm.community_detail(id)) ;
		return "community/modify";
	}
	
	// 커뮤니티 글 삭제 처리 요청
	@RequestMapping ("/delete.co")
	public String delete (int id, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적인 영역에서 삭제
		CommunityVO vo = commu_gm.community_detail(id);
		if (vo.getFilename1() != null) {
			File file = new File( session.getServletContext().getRealPath("resources")
					+ "/" + vo.getFilepath1() );
			if (file.exists()) file.delete();
		}
		
		// 해당 커뮤니티 글을 DB에서 삭제한 후 목록화면으로 연결
		commu_gm.community_delete(id);
	//	return "redirect:list.co";
		
		model.addAttribute("uri", "list.co");
		model.addAttribute("page", page);
		return "community/redirect";
		
	}
	
	// 커뮤니티 첨부파일 다운로드 요청
	@RequestMapping ("/download.co")
	public void download(int id, HttpSession session, HttpServletResponse response ) {
		
		// 해당 글의 첨부파일 정보를 DB에서 조회해와 해당 파일을 서버로부터 다운로드 함.
		CommunityVO vo = commu_gm.community_detail(id);
		common.fileDownload(vo.getFilename1(), vo.getFilepath1(), session, response);		
	}
	
	
	// 커뮤니티 상세화면 요청
	@RequestMapping ("/detail.co")
	public String detail(int id, Model model) {
		
		// 조회수 증가 처리
		commu_gm.community_read(id);
		
		// 해당 커뮤니티 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", commu_gm.community_detail(id) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "community/detail";
	}
	
	
	// 커뮤니티 글 신규 저장 처리 요청
	@RequestMapping ("/insert.co")
	public String insert(CommunityVO vo, MultipartFile file, HttpSession session ) {
		
		// 첨부된 파일이 있다면
		if ( ! file.isEmpty()) {
			vo.setFilename1(file.getOriginalFilename());
			vo.setFilepath1( common.fileUpload("community", file, session) );
		}
		
		MemberVO member =  (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getEmail() );
		
		// 화면에서 입력한 정보를 DB에 신규 저장한 후 목록화면 연결
		commu_gm.community_insert(vo);
		return "redirect:list.co";
	}
	
	// 커뮤니티 글쓰기 화면 요청
	@RequestMapping ("/new.co")
	public String community() {
		return "community/new";
	}
	
	// 커뮤니티 목록화면 요청
	@RequestMapping ("/list.co")
	public String list(HttpSession session
			, String search, String keyword
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "list") String viewType
			, Model model) {
		
		session.setAttribute("category", "co");
		
		// DB에서 커뮤니티 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		
		page.setSearch(search);		// 검색 조건
		page.setKeyword(keyword);	// 검색어
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수
		page.setViewType(viewType);	// 게시판 형태
		model.addAttribute("page", commu_gm.community_list(page) );
		return "community/list";
	}
}
