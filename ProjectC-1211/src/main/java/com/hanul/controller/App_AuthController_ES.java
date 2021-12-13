package com.hanul.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;


import app_auth.AuthVO;

@Controller
public class App_AuthController_ES {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	//앱에서 기획자 인증 수정
	@RequestMapping("/authUpdate")
	public void auth_update_es(AuthVO vo, HttpSession session, HttpServletResponse res, Model model, HttpServletRequest req) {
		
		String writer = req.getParameter("writer");
		int imgCount = Integer.parseInt(req.getParameter("imgCount"));	
		
		System.out.println("writer : " + writer + ", imgCount : " + imgCount);
		
		String filenam1 = "", filenam2 = "", filenam3 = "";
		String realImgPath = req.getSession().getServletContext().getRealPath("resources/");
		
		MultipartRequest multi = (MultipartRequest)req;
		
		if(multi != null && imgCount > 0) {
			
			if(imgCount == 1) {
				
				MultipartFile file1 = multi.getFile("filename1");
				filenam1 = file1.getOriginalFilename();
				vo.setFilenam1(filenam1);
				
				System.out.println("fileName1 : " + filenam1);
				
				// 이미지 파일을 서버에 저장
				try {
					file1.transferTo(new File(realImgPath, filenam1));
				} catch (Exception e) {
					e.getMessage();
				} 	
				
			}else if(imgCount == 2) {
				
				MultipartFile file1 = multi.getFile("filename1");
				MultipartFile file2 = multi.getFile("filename2");
				filenam1 = file1.getOriginalFilename();
				filenam2 = file2.getOriginalFilename();
				vo.setFilenam1(filenam1 + "," + filenam2 );
				
				System.out.println("fileName1 : " + filenam1);
				System.out.println("fileName2 : " + filenam2);
				
				// 이미지 파일을 서버에 저장
				try {
					file1.transferTo(new File(realImgPath, filenam1));
					file2.transferTo(new File(realImgPath, filenam2));
				} catch (Exception e) {
					e.getMessage();
				} 	
				
			}else if(imgCount == 3) {
				
				MultipartFile file1 = multi.getFile("filename1");
				MultipartFile file2 = multi.getFile("filename2");
				MultipartFile file3 = multi.getFile("filename3");
				filenam1 = file1.getOriginalFilename();
				filenam2 = file2.getOriginalFilename();
				filenam3 = file3.getOriginalFilename();
				vo.setFilenam1(filenam1 + "," + filenam2 + "," + filenam3 );
				
				System.out.println("fileName1 : " + filenam1);
				System.out.println("fileName2 : " + filenam2);
				System.out.println("fileName3 : " + filenam3);
				// 이미지 파일을 서버에 저장
				try {
					file1.transferTo(new File(realImgPath, filenam1));
					file2.transferTo(new File(realImgPath, filenam2));
					file3.transferTo(new File(realImgPath, filenam3));
				} catch (Exception e) {
					e.getMessage();
				} 	
				
			}
			
		}		
		
		
		vo.setWriter(writer);
		
		int succ = sql.update("auth.mapper.auth_update_es", vo);
		
		
		PrintWriter out;
		
		try {
			out = res.getWriter();
			out.println(succ+"");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	
	// 인증글 조회하는 곳
	@RequestMapping("/authOne")
	public void authOne(HttpSession session, Model model, HttpServletResponse response,  HttpServletRequest req) {
		System.out.println("authOne");
		String email = req.getParameter("email");
		System.out.println(email);

		AuthVO vo = sql.selectOne("auth.mapper.auth_one_es" , email);
//		model.addAttribute("all", service.board_all());
		System.out.println("조회된 인증글 번호"+ vo.getNo());
		System.out.println("filename1" +  vo.getFilenam1());
		
		Gson gson = new Gson();
		String json = gson.toJson(vo);
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
	
	@RequestMapping("/authList")
	public void authList(HttpSession session, Model model, HttpServletResponse response, String email, HttpServletRequest req) {
		
		email = req.getParameter("email");
		System.out.println("기획자 인증 넘어오기");

		List<AuthVO> list = sql.selectList("auth.mapper.auth_list_es" , email);
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
