package com.hanul.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import app_board.BoardVO;
import app_board_comment.Board_CommentVO;
import app_community.CommunityVO;
import app_favorite.FavoriteBoardVO;
import app_favorite.FavoriteCommunityVO;
import app_favorite.FavoriteVO;
import web_notice.NoticeVO;

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
   
   
   // 공지사항 조회하는 부분 
   @RequestMapping("/anNoticeSelect")
   public void anNoticeSelect(HttpServletResponse response, Model model, HttpSession session) {
      System.out.println("anNoticeSelecet");
      
      List<NoticeVO> list = sql.selectList("notice.mapper.notice_list_jy");
      
      
      Gson gson = new Gson();
       String json = gson.toJson(list);
       PrintWriter out;
       
       try {
          out = response.getWriter();
          out.println(json);
          
       } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
       
   }
   
   
   // 내가 쓴 커뮤니티 글 확인하기 
   @RequestMapping("/my_community")
   public void anMyCommunity(HttpServletResponse response, Model model, HttpSession session , HttpServletRequest req) {
      System.out.println("anMyCommunity");
      String email = req.getParameter("email");
      List<NoticeVO> list = sql.selectList("community.mapper.community_content_jy" , email);
      
      
      
      
      Gson gson = new Gson();
      String json = gson.toJson(list);
      PrintWriter out;
      
      try {
         out = response.getWriter();
         out.println(json);
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   
   // 내가 이 공연을 좋아요 했는 지 확인하는 것!----------------------------------------------------------------------------
   @RequestMapping("/infoFavoriteCheck")
   public void myInterestShow(Model model, HttpServletRequest req, HttpServletResponse response) {
      
      String post_id_board = req.getParameter("post_id_board");
      System.out.println("infoFavoriteCheck");
      
      
      
     
      
      
      List<FavoriteBoardVO> list= sql.selectList("app_favorite.mapper.info_Favorite_check_jy", post_id_board);
      
      for (int i = 0; i < list.size(); i++) {
        list.get(i).getLikemember();
        System.out.println(list.get(i).getLikemember());
        System.out.println(list.get(i).getPost_id_board());
     }
      
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

      
   }// infoFavoriteCheck
   
    

      // 내가 좋아하는 공연 리스트 조회하기 ----------------------------------------------------------------------------------
     @RequestMapping("/myFavoriteBoard")
     public void myFavoriteBoard ( Model model , HttpServletRequest req, HttpServletResponse response) {
        String likemember = req.getParameter("likemember");
        System.out.println(likemember);
        System.out.println("내가 좋아하는 공연 리스트 조회하기");
        System.out.println("myFavoriteBoard");
        
        List<BoardVO> list = sql.selectList("app_favorite.mapper.my_favorite_board_jy", likemember);
        
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
     
     
     //내가 좋아요 한 커뮤니티 -------------------------------------------------------------------------------
     @RequestMapping("/myFavoriteCommunity")
     public void myFavoriteCommunity ( Model model , HttpServletRequest req, HttpServletResponse response) {
        String likemember = req.getParameter("likemember");
        System.out.println(likemember);
        System.out.println("내가 좋아요 한 글 리스트 조회하기");
        System.out.println("myFavoriteCommunity");
        
        List<CommunityVO> list = sql.selectList("app_favorite.mapper.my_favorite_community_jy", likemember );
        
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
     
     
     
   
   // 공연 관심 삭제하는거------------------------------------------------------------------------------------
   
   
   @RequestMapping( value ="/myfavoriteboard/delete", method = {RequestMethod.GET, RequestMethod.POST})
   public void myFavoriteBoardDelete(Model model, HttpServletRequest req , HttpServletResponse response) {
      String likemember = req.getParameter("likemember");
      String target_type = req.getParameter("target_type");
      String post_id_board = req.getParameter("post_id_board");
      
      HashMap<String, String> map = new HashMap<String, String>();
     
      map.put("likemember" , likemember);
      map.put("target_type" , target_type);
      map.put("post_id_board" , post_id_board);
      
      int state = sql.delete("app_favorite.mapper.delete_my_favorite_board_jy", map);

      System.out.println("myfavorite_delete: " +state);
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
   
   // 좋아요 한 게시글 삭제하기---------------------------------------------------------------------
   @RequestMapping( value ="/myfavoritecommunity/delete", method = {RequestMethod.GET, RequestMethod.POST})
   public void myFavoriteCommunityDelete(Model model, HttpServletRequest req , HttpServletResponse response) {
      String likemember = req.getParameter("likemember");
      String target_type = req.getParameter("target_type");
      String post_id_community = req.getParameter("post_id_community");
      
      HashMap<String, String> map = new HashMap<String, String>();
      
      map.put("likemember" , likemember);
      map.put("target_type" , target_type);
      map.put("post_id_community" , post_id_community);
      
      int state = sql.delete("app_favorite.mapper.delete_my_favorite_community_jy", map);
      
      System.out.println("myfavorite_delete: " +state);
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
   
   
   // 내가 좋아요 "공연" 한 것 추가하기 -------------------------------------------------
   @RequestMapping( value = "/myfavoriteboard/insert", method = {RequestMethod.GET, RequestMethod.POST})
   public void myFavoriteBoardInsert(Model model, HttpServletRequest req , HttpServletResponse response) {
      
      String likemember = req.getParameter("likemember");
      String post_id_board = req.getParameter("post_id_board");
      String post_id_community = null;
      String target_type = req.getParameter("target_type");
      
     
      System.out.println(likemember);
      System.out.println(post_id_board);
    
      System.out.println(target_type);
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("likemember" , likemember);
      map.put("post_id_board" , post_id_board);
      map.put("post_id_community", post_id_community);
      map.put("target_type" , target_type);
      
      
      int state = sql.insert("app_favorite.mapper.insert_my_favorite_board_jy", map);
      
      System.out.println("myfavorite_insert: " +state);
      
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
   
   // 내가 좋아요 게시글 한 것 추가하기 -------------------------------------------------
   @RequestMapping( value = "/myfavoritecommunity/insert", method = {RequestMethod.GET, RequestMethod.POST})
   public void myFavoriteCommunityInsert(Model model, HttpServletRequest req , HttpServletResponse response) {
      
      String likemember = req.getParameter("likemember");
      String post_id_board = null;
      String post_id_community = req.getParameter("post_id_community");
      String target_type = req.getParameter("target_type");
   
      System.out.println(likemember);
    
      System.out.println(post_id_community);
      System.out.println(target_type);
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("likemember" , likemember);
      map.put("post_id_board" , post_id_board);
      map.put("post_id_community" ,post_id_community);
      map.put("target_type" , target_type);
      
      
      int state = sql.insert("app_favorite.mapper.insert_my_favorite_community_jy", map);
      
      System.out.println("myfavorite_insert: " +state);
      
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
      
      
   }// 끝나는 곳 
   
   
   @RequestMapping("/anInfoCommentSelect")
   public void InfoBoardCommentSelect (Model model, HttpServletRequest req, HttpServletResponse response) {
      String pno = req.getParameter("pno");
      System.out.println("공연의 코멘트 댓글 불러오기 !");
      System.out.println(pno);
      
      List<Board_CommentVO> list = sql.selectList("app_boardcomments.mapper.board_comment_select_jy", pno);
     
      
      
      for (int i = 0; i < list.size(); i++) {
           list.get(i).getContent();
           System.out.println(list.get(i).getContent());
      }
      
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
      
   } // 공연에 잇는 코멘트 불러오는거 끝
   
   
   
   
   // 공연 코멘트 추가하기 
   @RequestMapping( value = "/aninfocomment/insert", method = {RequestMethod.GET, RequestMethod.POST})
   public void infoCommentInsert(Model model, HttpServletRequest req , HttpServletResponse response) {
      
      String pno = req.getParameter("pno");
      String writer = req.getParameter("writer");
      String content = req.getParameter("content");
      
   
      System.out.println("공연의 코멘트 추가하기");
      System.out.println(pno);
    
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("pno" , pno);
      map.put("writer" , writer);
      map.put("content" ,content);
      
      
      
      int state = sql.insert("app_boardcomments.mapper.board_comment_insert_jy", map);
      
      System.out.println("Information_comment_insert: " +state);
      
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
      
      
   }// 끝나는 곳 
   
   
   @RequestMapping( value = "/aninfoComment/delete", method = {RequestMethod.GET, RequestMethod.POST})
   public void infoCommentDelete (Model model, HttpServletRequest req, HttpServletResponse response) {
      String writer = req.getParameter("writer");
      String pno = req.getParameter("pno");
      
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("writer", writer);
      map.put("pno", pno);
      
      int state = sql.delete("app_boardcomments.mapper.board_comment_delete_jy" , map);
      
      System.out.println("infoComment_Delete: " + state);
      
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
   
   @RequestMapping("/commuFavoriteCheck")
   public void myInterestCommu(Model model, HttpServletRequest req, HttpServletResponse response) {
      
      String post_id_community = req.getParameter("post_id_community");
      System.out.println("commuFavoriteCheck");
      
      
      
      
      
      
      List<FavoriteCommunityVO> list= sql.selectList("app_favorite.mapper.community_Favorite_check_jy", post_id_community);
      
      for (int i = 0; i < list.size(); i++) {
         list.get(i).getLikemember();
         System.out.println(list.get(i).getLikemember());
         System.out.println(list.get(i).getPost_id_board());
      }
      
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
      
      
   }// commuFavoriteCheck
   
   
}
   