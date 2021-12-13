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

import app_board.BoardDAO_ES;
import app_board.BoardVO;
import app_member.MemberVO;
import web_auth.AuthVO;

@Controller
public class App_ES_BoardController {
	@Autowired
	BoardDAO_ES dao_es;
	@Autowired
	@Qualifier("cteam")
	private SqlSession sql;

	@RequestMapping("/favorite")
	public void es_favorite(HttpSession sesstion, Model model, HttpServletResponse res) {
		List<BoardVO> list = dao_es.board_favorite();

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = res.getWriter();
			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/review")
	public void es_review(HttpSession sesstion, Model model, HttpServletResponse res) {
		List<BoardVO> list = dao_es.board_review();

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = res.getWriter();
			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/read")
	public void es_read(HttpSession sesstion, Model model, HttpServletResponse res) {
		List<BoardVO> list = dao_es.board_read();

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = res.getWriter();
			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/readcnt")
	public void es_readcnt(String no, HttpServletResponse res) {
		int num = Integer.parseInt(no);
		System.out.println(num);
		dao_es.board_readcnt(num);

		Gson gson = new Gson();
		String json = gson.toJson(no);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = res.getWriter();
			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/promoOne")
	public void es_promo_one(HttpSession session, Model model, HttpServletResponse response, HttpServletRequest req) {
		System.out.println("promoOne");
		String email = req.getParameter("email");
		System.out.println(email);

		BoardVO vo = sql.selectOne("board.mapper.promo_one_es", email);
//			model.addAttribute("all", service.board_all());
		// System.out.println(vo.getWriter());

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

	@RequestMapping("promoUpdate")
	public void es_promo_update(HttpServletRequest req, Model model, HttpServletResponse response) {
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String writer = req.getParameter("writer");
		String eventnm = req.getParameter("eventnm");
		String eventstartdate = req.getParameter("eventstartdate");
		String eventenddate = req.getParameter("eventenddate");
		String eventstarttime = req.getParameter("eventstarttime");
		String eventendtime = req.getParameter("eventendtime");
		String mnnst = req.getParameter("mnnst");
		String auspcinstt = req.getParameter("auspcinstt");
		String phonenumber = req.getParameter("phonenumber");
		String suprtinstt = req.getParameter("suprtinstt");
		String entncage = req.getParameter("entncage");
		String atpn = req.getParameter("atpn");
		String prkplceyn = req.getParameter("prkplceyn");
		String rdnmadr = req.getParameter("rdnmadr");
		String filepath = req.getParameter("filepath");
		
		System.out.println(writer + eventnm);
		
		// 2. 찍어봅시다
		System.out.println(writer + ", " + eventnm + ", " + mnnst + ", " + entncage + ", " + atpn + ", " + prkplceyn);
		// MultipartRequest multi1 = (MultipartRequest)req;
		// MultipartFile file1 = multi1.getFile("filename");
		
		BoardVO vo = new BoardVO();
		vo.setWriter(writer);
		vo.setEventnm(eventnm);
		vo.setEventstartdate(eventstartdate);
		vo.setEventenddate(eventenddate);
		vo.setEventstarttime(eventstarttime);
		vo.setEventendtime(eventendtime);
		vo.setMnnst(mnnst);
		vo.setAuspcinstt(auspcinstt);
		vo.setPhonenumber(phonenumber);
		vo.setSuprtinstt(suprtinstt);
		vo.setEntncage(entncage);
		vo.setAtpn(atpn);
		vo.setPrkplceyn(prkplceyn);
		vo.setRdnmadr(rdnmadr);

		// 3. 안드로이드에서 보낸 파일 받기 : 파일을 보낸 경우에만 실행
		// 파일이름만 저장해 놓고 안드로이드에서 받아서 전체 경로를 완성한다
		String realImgPath = req.getSession().getServletContext().getRealPath("resources/");
		
		MultipartRequest multi = (MultipartRequest)req;
		if(filepath == null  || filepath.equals("")) {
			
			if(multi != null) {
				
				MultipartFile file = multi.getFile("filepath");
				filepath = file.getOriginalFilename();
				vo.setFilepath(filepath);
				
				System.out.println("filePath : " + filepath);
				
				// 이미지 파일을 서버에 저장
				try {
					file.transferTo(new File(realImgPath, filepath));
				} catch (Exception e) {
					e.getMessage();
				} 	
				
			}
	
			vo.setFilepath(filepath);

			int succ = sql.update("board.mapper.promo_update_es", vo);

			PrintWriter out;

			try {
				out = response.getWriter();
				out.println(succ + "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}