package com.hanul.controller;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app_board.BoardDAO_GW;
import app_board.BoardVO;
import app_community.CommunityPage;
import app_community.CommunityVO;
import app_member.MemberVO;
import web_auth.AuthDAO;
import web_auth.AuthPage;
import web_board.BoardDAO_web_GW;
import web_board.BoardPage;
import web_common.CommonService;
import web_common.PageVO;
import web_community.CommunityDAO_MJ;
import web_member.MemberDAO_MJ;
import web_member.MemberPage;
import web_qna.QnaDAO_MJ;
import web_qna.QnaPage;
import web_qna.QnaVO;

@Controller
public class Web_MemberController {
   
   @Autowired private MemberDAO_MJ service;
   @Autowired private CommonService common;
   @Autowired private MemberPage page;
   @Autowired private AuthDAO auth_service;
   @Autowired private AuthPage auth_page;
   @Autowired private CommunityPage comm_page;
   @Autowired private BoardPage bo_page;
   @Autowired private QnaPage qna_page;
   @Autowired static String state;
   @Autowired private BoardDAO_web_GW bo_dao;
   @Autowired private CommunityDAO_MJ co_dao;
   @Autowired private QnaDAO_MJ qna_dao;
   
   private String naver_client_id = "jpLk2cBAgCpN07Z9Qx4B";
   private String kakao_client_id = "70abb9970adb1cad341591555e9378d4";

//-------------------------------------------------------------------------------------------------
   //관리자가 회원 디테일에서 회원 삭제
   @RequestMapping("/member_delte.admin")
   public String delete(String email, Model model) {
	   //해당 회원의 정보를 DB에서 삭제한 후 회원 목록 화면으로 연결
	   service.member_delete(email);
	   model.addAttribute("uri", "member.admin");
	   model.addAttribute("page", page);
	   return "member/redirect";
   }
   
   
	//관리자가 회원 리스트에서 회원 클릭하면 상세 정보 조회 및 화면 출력
	@RequestMapping("/member_detail.admin")
	public String member_detail(String email, Model model) {
		model.addAttribute("vo", service.member_detail(email));
		return "member/member_detail_admin";
	}
	
	
   //관리자 회원정보 리스트 띄워주기
   @RequestMapping("/member.admin")
   public String list_member(HttpSession session
			, String search, String keyword
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "list") String viewType
			, Model model) {
		
		session.setAttribute("category", "admin");
		
		// DB에서 회원 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		
		page.setSearch(search);		// 검색 조건
		page.setKeyword(keyword);	// 검색어
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수
		page.setViewType(viewType);	// 게시판 형태
		model.addAttribute("page", service.member_list(page) );
	   
	   return "member/list_member";
   }
 
      
   //관리자 마이페이지 띄워주기
   @RequestMapping("/mypage.admin")
   public String mypage_admin() {
	   return "member/mypage_admin";
   }
//--------------------------------------------------------------------------------------------------
  
   //회원탈퇴
   @RequestMapping("/quit.my")
   public String quit(String email, HttpSession session) {
		/*
		 * service.member_quit(email); session.removeAttribute("loginInfo");
		 */
	   
	   MemberVO member = (MemberVO) session.getAttribute("loginInfo");
	   email =member.getEmail();
	   int succ = service.member_quit(email);
	   session.removeAttribute("loginInfo");
	   
	   return"redirect:/";
   }
   
//--------------------------------------------------------------------------------
   
   //내가 쓴 글 목록 띄워주기-->QnA
	@RequestMapping("/post_qna.my")
	public String qna(HttpSession session, String search, String keyword, 
            @RequestParam(defaultValue = "10") int pageList,
            @RequestParam(defaultValue = "1") int curPage, Model model) {
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		String email = member.getEmail();

		qna_page.setCurPage(curPage);
		qna_page.setPageList(pageList);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("page", qna_page);
		
		model.addAttribute("page", service.qna_list(map));
		return "member/post_qna";
	}
	
	//내가 쓴 글 디테일 보여주기-->QnA
	@RequestMapping ("/detail_qna.my")
	public String detail(int id, Model model) {
		
		// 상세화면 요청 전 조회수 증가
		service.qna_read(id);
		
		// 선택한(id) 질문 정보를 DB에서 조회해 와 상세화면 출력
		model.addAttribute("vo", service.qna_detail(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", qna_page);
		
		return "member/post_qna_detail";
	}
	
	//내가 쓴 글 수정-->QnA
	@RequestMapping ("/modify_qna.my")
	public String modify (int id, Model model) {
		model.addAttribute("vo", service.qna_detail(id));
		return "member/post_qna_modify";
	}
	
	//내가 쓴 글 수정 저장처리-->QnA
	@RequestMapping ("/update_qna.my")
	public String update(QnaVO vo, HttpSession session, String attach, Model model) {
		
		// 화면에서 변경 입력한 정보를 DB에 변경 저장한 후 상세화면으로 연결
		service.qna_update(vo);		
		model.addAttribute("uri", "detail_qna.my");
		model.addAttribute("id", vo.getId());
		return "redirect";
		
	}
	
	//내가 쓴 글 삭제하고 post_qna로 연결-->QnA
	@RequestMapping ("/delete_qna.my")
	public String delete (int id, HttpSession session, Model model) {

		// 해당 QnA 정보를 DB에서 삭제한 후 목록화면으로 연결
		service.qna_delete(id);
		
		model.addAttribute("uri", "post_qna.my");
		model.addAttribute("page", qna_page);
		
		return "redirect:post_qna.my";
	}
	

//--------------------------------------------------------------------------------
	
	//내가 쓴 글 목록 띄워주기-->공연정보
	@RequestMapping("/post_bo.my")
	public String bo(HttpSession session, String search, String keyword,
            @RequestParam(defaultValue = "10") int pageList,
            @RequestParam(defaultValue = "1") int curPage, Model model) {
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		String email = member.getEmail();

		bo_page.setCurPage(curPage);
		bo_page.setPageList(pageList);
		bo_page.setViewType("grid");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("page", bo_page);		
		
		model.addAttribute("page", service.board_list(map));
		return "member/post_bo";
	}
	
	//내가 쓴 글 디테일 보여주기-->공연정보
	@RequestMapping ("/detail_bo.my")
	public String bo_detail(int id, Model model) {
		
		// 조회수 증가 처리
		service.board_read(id);
		
		// 해당 커뮤니티 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.board_detail(id) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", bo_page);
		return "member/post_bo_detail";
	}
	
	//내가 쓴 글 수정-->공연정보
	@RequestMapping ("/modify_bo.my")
	public String board_modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.board_detail(id)) ;
		return "member/post_bo_modify";
	}
	
	//내가 쓴 글 수정 저장처리-->공연정보
	@RequestMapping ("/update_bo.my")
	public String board_update(BoardVO vo, Model model
			, HttpSession session, String attach) {
		
		
	//화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		service.board_update(vo);
		
		model.addAttribute("uri", "detail_bo.my");
		model.addAttribute("id", vo.getNo());
		return "redirect";
	}
	
	//내가 쓴 글 삭제하고 post_bo으로 연결-->공연정보
	@RequestMapping ("/delete_bo.my")
	public String board_delete (int id, HttpSession session, Model model) {
		
		// 해당 공연정보 글을 DB에서 삭제한 후 목록화면으로 연결
		service.board_delete(id);
	//	return "redirect:list.no";
		
		model.addAttribute("uri", "post_bo.my");
		model.addAttribute("page", bo_page);
		return "redirect";
		
	}
	
//--------------------------------------------------------------------------------
	
	//내가 쓴 글 목록 띄워주기-->커뮤니티
	@RequestMapping("/post_comm.my")
	public String comm(HttpSession session, String search, String keyword,
            @RequestParam(defaultValue = "10") int pageList,
            @RequestParam(defaultValue = "1") int curPage, Model model) {
		
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		String email = member.getEmail();

		comm_page.setCurPage(curPage);
		comm_page.setPageList(pageList);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("page", comm_page);
		
		/* state="mypage"; */
		/* model.addAttribute("state", state); */
		
		model.addAttribute("page", service.community_list(map));
		return "member/post_comm";
	}
	
	//내가 쓴 글 디테일 보여주기-->커뮤니티
	@RequestMapping("/detail_comm.my")
	public String comm_detail(int id, Model model) {
		//조회수 증가 처리
		service.community_read(id);
		
		//해당 커뮤니티 글을 DB에서 조회해와 상세 화면에 출력
		model.addAttribute("vo", service.community_detail(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", comm_page);		
		return"member/post_comm_detail";
	}
	
	//내가 쓴 글 수정-->커뮤니티
	@RequestMapping("/modify_comm.my")
	public String comm_modify(int id, Model model) {
		//해당 글의 정보를 DB에서 조회해와 수정 화면에 출력
		model.addAttribute("vo", service.community_detail(id));
		return"member/post_comm_modify";
	}
	
	//내가 쓴 글 수정 저장처리-->커뮤니티
	@RequestMapping("/update_comm.my")
	public String comm_update(CommunityVO vo, Model model, HttpSession session, MultipartFile file, String attach) {
		// 원 글에 첨부 파일이 있는지...
		CommunityVO community = service.community_detail(vo.getId());
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
		service.community_update(vo);
		
		model.addAttribute("uri", "detail_comm.my");
		model.addAttribute("id", vo.getId());
		return "redirect";
		
	}
	
	//내가 쓴 글 삭제하고 post_comm으로 연결-->커뮤니티
	@RequestMapping("delete_comm.my")
	public String comm_delete (int id, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적인 영역에서 삭제
		CommunityVO vo = service.community_detail(id);
		if (vo.getFilename1() != null) {
			File file = new File( session.getServletContext().getRealPath("resources")
					+ "/" + vo.getFilepath1() );
			if (file.exists()) file.delete();
		}
		
		// 해당 커뮤니티 글을 DB에서 삭제한 후 목록화면으로 연결
		service.community_delete(id);
	//	return "redirect:list.co";
		
		model.addAttribute("uri", "post_comm.my");
		model.addAttribute("page", comm_page);
		return "redirect:post_comm.my";
	}
	
//--------------------------------------------------------------------------------
	
	//회원 정보 수정 후 저장 처리 요청
	@RequestMapping("/update.my")
	public String update(MemberVO vo, Model model) {
		service.member_update(vo);		
		model.addAttribute("uri", "detail.my");
		model.addAttribute("email", vo.getEmail());
		return"member/redirect";
	}

	//정보 수정화면 요청
	@RequestMapping("/modify.my")
	public String modify(String email, Model model) {
		MemberVO member = service.member_detail(email);
		model.addAttribute("vo",member );
		return "member/modify";
	}
	
	//비밀번호 확인 처리 요청
	@ResponseBody
	@RequestMapping("/confirm_pw")
	public boolean confirm(MemberVO vo,  HttpSession session, String pw) {
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setEmail(member.getEmail());
		
		String pwCh = member.getPassword();		
		boolean check;
		
//		if (pw == pwCh) {
		if (pw.equals(pwCh)) {
//			return "modify.my";
			return true;
			
		}else {
//			return "redirect:comfirm:my";
			return false;
		}
	}
	
	
	//정보수정 클릭 후 비밀번호 확인하기 창
	@RequestMapping("/confirm.my")
	public String confirm() {
		return "member/confirm";
	}
	
	//정보수정 클릭하면 내 정보 띄워주기
	@RequestMapping("/detail.my")
	public String detail(String email, Model model) {
		model.addAttribute("vo", service.member_detail(email));
		return "member/member_detail";
	}
      
	/*
	 * //회원 정보 수정 후 저장
	 * 
	 * @RequestMapping("/update.my") public String member_update(MemberVO vo, Model
	 * model, HttpSession session) { //화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
	 * service.member_update(vo); model.addAttribute("uri", "update.my");
	 * model.addAttribute("email", vo.getEmail()); return "member/redirect"; }
	 */

   
   //사용자, 기획자 마이페이지 화면(정보수정, 커뮤니티, 내가 쓴 글 탭) 요청
   @RequestMapping("/mypage.user" )
   public String mypage(Model model, HttpSession session) {
	   String email =( (MemberVO) session.getAttribute("loginInfo")).getEmail();
	   model.addAttribute("vo", auth_service.auth_list(email));
	   
	   return "member/mypage";
   }

	/*
	 * //회원 정보 수정 화면 요청
	 * 
	 * @RequestMapping("/list.my" ) public String member_modify(String email, Model
	 * model) { model.addAttribute("vo", service.member_detail(email)); return
	 * "member/member_detail"; }
	 */
   
   // 회원 가입 처리 요청
   @ResponseBody 
   @RequestMapping(value="/join", produces = "text/html; charset=utf-8")
   public String join(MemberVO vo, HttpServletRequest req, HttpSession session) {
      StringBuffer msg = new StringBuffer("<script>");
      if(service.member_join(vo)) {
         common.sendEmail(vo.getName(), vo.getEmail(), session);
         msg.append("alert('회원가입을 축하드립니다'); location='")
         .append(req.getContextPath()).append("'");         
         
         //msg.append("alert('회원가입을 축하드립니다'); location='login' ")
         
      }else {
         msg.append("alert('회원가입 실패'); location='member' ");
      }
      msg.append("</script>");
      return msg.toString();
   }
   
   
   // ID 중복 확인 요청
   @ResponseBody
   @RequestMapping("/id_check")
   public boolean id_check( String id ) {
      return service.member_id_check(id);
   }
   
   
   // 회원가입 페이지 화면 이동
   @RequestMapping("/member_join")
   public String member(HttpSession session) {
      // 타이틀에 회원가입 명시하기 위한 session
      session.setAttribute("category", "join");
      return "member/join";
   }
   
   
   // 카카오 로그인 요청
   @RequestMapping ("/kakaoLogin")
   public String kakaoLogin(HttpSession session) {
      // 카카오 로그인 연동 URL 생성
      String state = UUID.randomUUID().toString();
      
      session.setAttribute("state", state);

      StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
      url.append("&client_id=").append(kakao_client_id);
      url.append("&state=").append(state);
      url.append("&redirect_uri=http://localhost:8003/cteam/kakaocallback");
      return "redirect:" + url.toString();
   }
   
   // 네이버 로그인 요청
   @RequestMapping ("/naverLogin")
   public String naverLogin(HttpSession session) {
      // 네아로 연동 URL 생성
      String state = UUID.randomUUID().toString();
      
      session.setAttribute("state", state);
      
      StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
      url.append("&client_id=").append(naver_client_id);
      url.append("&state=").append(state);
      url.append("&redirect_uri=http://localhost:8003/cteam/navercallback");
      return "redirect:" + url.toString();
   }
   
   // 카카오 Callback 메소드 선언
   @RequestMapping("/kakaocallback")
   public String kakaocallback(@RequestParam(required = false) String code, String state, 
         @RequestParam(required = false) String error, HttpSession session) {
      
      // state 값이 맞지 않거나 error 가 발생하면 토큰 발급 불가
      if (!state.equals( session.getAttribute("state")) || error != null) {
         return "redirect:/";    // 메인 페이지로 이동
      }
      
     
      StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/token?grant_type=authorization_code");
      url.append("&client_id=").append(kakao_client_id);
//      url.append("&client_secret=a8kmL1fLNB");
      url.append("&code=").append(code);
//      url.append("&state=").append(state);
      
   //   common.requestAPI() 를 통해 출력받는 값의 형태가 json 이므로
   //   json 객체를 사용하여 값을 할당   (json 라이브러리 pom.xml 주입)
      JSONObject json = new JSONObject( common.requestAPI(url));
      
      String token = json.getString("access_token");
      String type = json.getString("token_type");
     
      url = new StringBuffer("https://kapi.kakao.com/v2/user/me");
      json = new JSONObject( common.requestAPI(url, type + " " + token) );
      
 
      if (! json.isEmpty()) {
//         json = json.getJSONObject("response");
         
         // 회원정보 DB 에 값을 담아서 관리 _ MemberVO
         MemberVO vo = new MemberVO();
         vo.setKakao(json.get("email").toString());         // 소셜 로그인 형태를 지정 ("kakao")
         vo.setEmail(json.get("email").toString());
         // 소셜 로그인을 했을 경우 해당 정보를 저장하여 소셜 구분을 위함.
         
         json = json.getJSONObject("kakao_account");
         vo.setKakao(json.getString("email"));
         vo.setName(json.getJSONObject("profile").getString("nickname"));
         
         // 네이버 최초 로그인인 경우 회원정보 저장 (insert)
         // 네이버 로그인 이력이 있어 회원정보가 있다면 변경 저장
         if (service.member_social_email(vo))
            service.member_social_update(vo);
         else
            service.member_social_insert(vo);
         
         session.setAttribute("loginInfo", vo);
      }
      
      
      
      return "redirect:/";   // 로그인 시 루트(home.jsp)로 이동
   }
   
   
   

   // 네이버 Callback 메소드 선언
   @RequestMapping("/navercallback")
   public String navercallback(@RequestParam(required = false) String code, String state, 
         @RequestParam(required = false) String error, HttpSession session) {
      
      // state 값이 맞지 않거나 error 가 발생하면 토큰 발급 불가
      if (!state.equals( session.getAttribute("state")) || error != null) {
         return "redirect:/";    // 메인 페이지로 이동
      }

      StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
      url.append("&client_id=").append(naver_client_id);
      url.append("&client_secret=a8kmL1fLNB");
      url.append("&code=").append(code);
      url.append("&state=").append(state);
      
   //   common.requestAPI() 를 통해 출력받는 값의 형태가 json 이므로
   //   json 객체를 사용하여 값을 할당   (json 라이브러리 pom.xml 주입)
      JSONObject json = new JSONObject( common.requestAPI(url));
      
      String token = json.getString("access_token");
      String type = json.getString("token_type");
     
      url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
      json = new JSONObject( common.requestAPI(url, type + " " + token) );
      
      // 문서페이지 > 튜토리얼 > Web 애플리케이션 문서를 보면 지금까지 진행한 부분을 확인 가능
      if (json.getString("resultcode").equals("00")) {
         json = json.getJSONObject("response");
         
         // 회원정보 DB 에 값을 담아서 관리 _ MemberVO
         MemberVO vo = new MemberVO();
         vo.setNaver(json.getString("email"));         // 소셜 로그인 형태를 지정 ("naver")
         vo.setEmail(json.getString("email"));
         // 소셜 로그인을 했을 경우 해당 정보를 저장하여 소셜 구분을 위함.
         vo.setNaver(json.getString("email"));
         vo.setName(json.getString("name"));
         
         // 네이버 최초 로그인인 경우 회원정보 저장 (insert)
         // 네이버 로그인 이력이 있어 회원정보가 있다면 변경 저장
         if (service.member_social_email(vo))
            service.member_social_update(vo);
         else
            service.member_social_insert(vo);
         
         session.setAttribute("loginInfo", vo);
      }
      
      return "redirect:/";   // 로그인 시 루트(home.jsp)로 이동
   }
   
   
   // 로그아웃 처리 요청
   @RequestMapping ("/logout")
   public String logout (HttpSession session) {
      // 세션에 담긴 로그인 정보를 삭제한다.
      session.removeAttribute("loginInfo");
      
      // 로그아웃 시 루트(home.jsp)로 이동
      return "redirect:/";   
   }
   
   
   // 로그인 처리 요청
   @ResponseBody   // 자바 객체를 Client(Http) 요청의 body 내용으로 매핑함(전송)
   @RequestMapping("/iotLogin")
   public Boolean login(String email, String pw, HttpSession session) {
      // 화면에서 전송한 이메일, 비밀번호가 일치하는 회원정보를 조회
      // 매개변수 2개를 HashMap 형태로 담아 service 전달
      System.out.println(email);
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("email", email);
      map.put("password", pw);
      MemberVO vo = service.member_login(map);
      session.setAttribute("loginInfo", vo);
      return vo == null ? false : true; // 결과값이 null 이면 false / null 아니면 true
   }

   //로그인 화면 요청
   @RequestMapping("/login")
   public String login(HttpSession session) {
      // title 태그에 사용할 수 있도록 지정(session)
      session.setAttribute("category", "login");
      return "member/login";
   }
   
}