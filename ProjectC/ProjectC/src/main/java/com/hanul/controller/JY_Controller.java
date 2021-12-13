package com.hanul.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JY_Controller {

   @Autowired @Qualifier("cteam") SqlSession sql;
   
   @RequestMapping(value="/memberDelete", method = {RequestMethod.GET, RequestMethod.POST})
   public void memberDelete( Model model, HttpServletRequest req , HttpServletResponse response ) {
      
      String email = req.getParameter("email");
      
      System.out.println("삭제할 "+email + " 값 받아옴" );
      int succ = sql.delete("member.mapper.member_delete_jy", email);
      
      PrintWriter out;
      
      try {
         out = response.getWriter();
         out.println(succ + " 삭제 성공");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      
   }
   
}
   