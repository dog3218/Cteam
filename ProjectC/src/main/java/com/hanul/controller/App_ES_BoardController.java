package com.hanul.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import app_board.BoardDAO_ES;
import app_board.BoardVO;

@Controller
public class App_ES_BoardController {
	@Autowired BoardDAO_ES dao_es;
	   
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
}