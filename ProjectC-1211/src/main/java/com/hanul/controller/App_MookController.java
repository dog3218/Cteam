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

import app_board.BoardDAO_GM;
import app_board.BoardVO;
import app_community.CommentVO;
import app_community.CommunityVO;

@Controller
public class App_MookController {
	@Autowired private BoardDAO_GM dao_gm;
	@Autowired @Qualifier("cteam") SqlSession sql;
	
	@RequestMapping("/all")
	public void listAll(HttpSession session, Model model, HttpServletResponse response) {
		List<BoardVO> list = dao_gm.board_all();
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
	
	@RequestMapping("/musical")
	public void listMusical(HttpSession session, Model model, HttpServletResponse response) {
	
	
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_musical();
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
	
	@RequestMapping("/opera")
	public void listOpera(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_opera();
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
	
	@RequestMapping("/play")
	public void listPay(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_play();
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
	
	@RequestMapping("/exhibition")
	public void listExhibition(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_exhibition();
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
	
	@RequestMapping("/concert")
	public void listConcert(HttpSession session, Model model, HttpServletResponse response) {
		
//		session.setAttribute("category", "cu"); 
		// 카테고리 어트리뷰트에 cu 를 설정
		
//		List<CustomerVO> list = service.customer_list();
		
//		for (CustomerVO vo : list ) {
//			System.out.println(vo.getName());
//		}
		
//		model.addAttribute("list", list);
		List<BoardVO> list = dao_gm.board_concert();
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
	
	@RequestMapping("/CommentInsert")	
	public void commentInsert(HttpServletRequest req ,HttpSession session, Model model, HttpServletResponse response) {
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		int pno = Integer.parseInt(req.getParameter("pno"));
		
		CommentVO vo = new CommentVO();
		vo.setWriter(writer);
		vo.setContent(content);
		vo.setPno(pno);
		
		int success = sql.insert("community.mapper.insert_comment_gm", vo);
		
		Gson gson = new Gson();
		String json = gson.toJson(success);
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
	
	@RequestMapping("/comment_Delete")
	public void comment_Delete(HttpServletRequest req ,HttpSession session, HttpServletResponse response) {
		System.out.println("delete 완료");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		int state = sql.delete("community.mapper.delete_comment_gm", id);
		System.out.println(state);
		Gson gson = new Gson();
		String json = gson.toJson(state);
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
	
	@RequestMapping("/community_Delete")
	public void community_Delete(HttpServletRequest req ,HttpSession session, HttpServletResponse response) {
		System.out.println("delete 완료");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		int state = sql.delete("community.mapper.delete_community_gm", id);
		System.out.println(state);
		Gson gson = new Gson();
		String json = gson.toJson(state);
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
	
	@RequestMapping("/community_Modify_Nofile")
	public void community_Modify_Nofile(HttpServletRequest req ,HttpSession session, HttpServletResponse response) {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		
		CommunityVO vo = new CommunityVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(email);
		vo.setSubject(subject);
		
		
		
		int state = sql.update("community.mapper.modify_nofile_community_gm", vo);
		System.out.println("state: "+state);
		Gson gson = new Gson();
		String json = gson.toJson(state);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("수정 완료");
		
	}
	
	@RequestMapping("/community_Modify")
	public void community_Modify(HttpServletRequest req ,HttpSession session, HttpServletResponse response) {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		//String filepath1 = req.getParameter("filepath1");
		String filename1 = "", filepath1="";
		
		CommunityVO vo = new CommunityVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(email);
		vo.setSubject(subject);
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("filepath1");
		
		if(file != null) {
			filename1 = file.getOriginalFilename();
			System.out.println("fileName : " + filename1);
			if(file.getSize() > 0) {
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println("realpath : " + realImgPath);
				System.out.println("fileSize : " + file.getSize());
				
				try {
					file.transferTo(new File(realImgPath, filename1));
				} catch (Exception e) {
					e.getMessage();
				} 
			}
		}
		
		vo.setFilename1(filename1);
		filepath1 = "resources/"+vo.getFilename1();
		vo.setFilepath1(filepath1);
		
		int state = sql.update("community.mapper.modify_community_gm", vo);
		
		Gson gson = new Gson();
		String json = gson.toJson(state);
		PrintWriter out;
		// 클라이언트에게 응답
		try {
			out = response.getWriter();
			out.println(json);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("수정 완료");
		
	}
	
	@RequestMapping("/CommuInsert")
	public void commuInsert(HttpServletRequest req ,HttpSession session, Model model, HttpServletResponse response) {
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String filepath1 = req.getParameter("filepath1");
		
		CommunityVO vo = new CommunityVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(email);
		vo.setSubject(subject);
		
		String filename1="";
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("imgrealpath");
		
		if(file != null) {
			filename1 = file.getOriginalFilename();
			System.out.println("fileName : " + filename1);
			if(file.getSize() > 0) {
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println("realpath : " + realImgPath);
				System.out.println("fileSize : " + file.getSize());
				
				try {
					file.transferTo(new File(realImgPath, filename1));
				} catch (Exception e) {
					e.getMessage();
				} 
			}
		}
		vo.setFilename1(filename1);
		filepath1 = "resources/"+vo.getFilename1();
		vo.setFilepath1(filepath1);
		
		int success = sql.insert("community.mapper.insert_show_gm", vo);
		
		Gson gson = new Gson();
		String json = gson.toJson(success);
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
	
	@RequestMapping("/comment_List")
    public void commentList(HttpServletRequest req, HttpSession session, Model model, HttpServletResponse response, String pno) {
		
		//subject = req.getParameter("subject");
		
		List<CommentVO> list = sql.selectList("community.mapper.comment_select_gm", pno);
		
		Gson gson = new Gson();
  	  	String json = gson.toJson(list);
  	  	PrintWriter out;
  	  
  	  	try {
  	  		out = response.getWriter();
  	  		out.println(json);
  		  
  	  	} catch (IOException e) {
  	  		e.printStackTrace();
  	  	}
    }//comment_select_gm
	
	@RequestMapping("/comm_List")
    public void communityList(HttpServletRequest req, HttpSession session, Model model, HttpServletResponse response, String subject) {
		
		//subject = req.getParameter("subject");
		
		List<CommunityVO> list = sql.selectList("community.mapper.community_select_gm", subject);
		
		
		
		Gson gson = new Gson();
  	  	String json = gson.toJson(list);
  	  	PrintWriter out;
  	  
  	  	try {
  	  		out = response.getWriter();
  	  		out.println(json);
  		  
  	  	} catch (IOException e) {
  	  		e.printStackTrace();
  	  	}
    }//community_select_gm
	
	
}
