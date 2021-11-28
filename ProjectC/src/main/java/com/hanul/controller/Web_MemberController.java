package com.hanul.controller;

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

import app_member.MemberVO;
import web_common.CommonService;
import web_member.MemberDAO_MJ;

@Controller
public class Web_MemberController {
   
   @Autowired private MemberDAO_MJ service;
   @Autowired private CommonService common;
   
   private String naver_client_id = "jpLk2cBAgCpN07Z9Qx4B";
   private String kakao_client_id = "70abb9970adb1cad341591555e9378d4";
      
   //회원 정보 수정 후 저장
   @RequestMapping("/update.my")
   public String member_update(MemberVO vo, Model model, HttpSession session) {
      //화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
      service.member_update(vo);
      model.addAttribute("uri", "update.my");
      model.addAttribute("email", vo.getEmail());
      return "member/redirect";
   }
   
   //회원 정보 수정 화면 요청
   @RequestMapping("/list.my" )
   public String member_modify(String email, Model model) {
      model.addAttribute("vo", service.member_detail(email));
      return "member/member_detail";
   }
   
   
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
      url.append("&redirect_uri=http://localhost/iot/kakaocallback");
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
      url.append("&redirect_uri=http://localhost/iot/navercallback");
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