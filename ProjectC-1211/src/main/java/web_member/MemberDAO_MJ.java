package web_member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import app_board.BoardVO;
import app_community.CommunityPage;
import app_community.CommunityVO;
import app_member.MemberVO;
import web_board.BoardPage;
import web_qna.QnaPage;
import web_qna.QnaVO;

@Repository
public class MemberDAO_MJ {

	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	
	public boolean member_join(MemberVO vo) {
		return sql.insert("member.mapper.join_mj", vo) == 1 ? true : false;	
	}

	
	public int member_update(MemberVO vo) {
		return sql.update("member.mapper.update_mj", vo);
	}

	
	public int member_delete(String email) {
		return sql.delete("member.mapper.delete", email);
	}
	
	public int member_quit(String email) {
		return sql.delete("member.mapper.quit", email);
	}

	
	public boolean member_id_check(String email) {
		return (Integer) sql.selectOne("member.mapper.id_check_mj",email) == 0 ? true : false;
	}

	
	public MemberVO member_login(HashMap<String, String> map) {
		System.out.println(map.get("email"));
		return sql.selectOne("member.mapper.member_login_mj", map);
	}

	
	public boolean member_social_email(MemberVO vo) {
		return (Integer) sql.selectOne("member.mapper.social_email_mj", vo) == 0 ? false : true;
	}


	public boolean member_social_insert(MemberVO vo) {
		return sql.insert("member.mapper.social_insert_mj", vo) == 0 ? false : true;
	}

	
	public boolean member_social_update(MemberVO vo) {
		return sql.update("member.mapper.social_update_mj", vo) == 0 ? false : true;
	}

	
	public MemberVO member_detail(String email) {
		return sql.selectOne("member.mapper.detail_mj", email);
	}
	
	public MemberPage member_list(MemberPage page){
		//전체 회원목록 조회
		page.setTotalList(sql.selectOne("member.mapper.totalList", page));
		
		//페이징 처리된 전체 회원목록 조회
		page.setList(sql.selectList("member.mapper.list", page));
		return page;
	}

//-----------------------------------------------------------------------------------------------
	//내가 쓴 글-->커뮤니티
	public CommunityPage community_list(HashMap<String, Object> map) {
		CommunityPage page=(CommunityPage) map.get("page");
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("member.mapper.my_comm_total", map));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("member.mapper.my_comm_list", map));

		return page;
	}
	
	//내가 쓴 글 조회수 증가-->커뮤니티
	public int community_read(int id) {
		return sql.update("member.mapper.my_comm_read", id);
	}
	
	//내가 쓴 글로 들어가서 디테일 보기-->커뮤니티
	public CommunityVO community_detail(int id) {
		return sql.selectOne("member.mapper.my_comm_detail", id);
	}
	
	
	//내가 쓴 글로 들어가서 수정저장-->커뮤니티
	public int community_update(CommunityVO vo) {
		return sql.update("member.mapper.my_comm_update", vo);
	}
	
	//내가 쓴 글로 들어가서 삭제-->커뮤니티
	public int community_delete(int id) {
		return sql.delete("member.mapper.my_comm_delete", id);
	}
	
//-----------------------------------------------------------------------------------------------
	//내가 쓴 글-->공연홍보글
	public BoardPage board_list(HashMap<String, Object>map) {
		BoardPage page =(BoardPage) map.get("page");
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("member.mapper.my_bo_total", map));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("member.mapper.my_bo_list", map));
		
		return page;
	}
	
	//내가 쓴 글 조회수 증가 처리-->공연홍보
	public int board_read(int no) {
		return sql.update("member.mapper.my_bo_read", no);
	}
	
	//내가 쓴 글로 들어가서 디테일 보기-->공연홍보
	public BoardVO board_detail(int id) {
		return sql.selectOne("member.mapper.my_bo_detail", id);
	}
	
	//내가 쓴 글로 들어가서 수정저장-->공연홍보
	public int board_update(BoardVO vo) {
		return sql.update("board.mapper.", vo);
	}
	
	//내가 쓴 글로 들어가서 삭제-->공연홍보
	public int board_delete(int id) {
		return sql.delete("board.mapper.", id);
	}

//-----------------------------------------------------------------------------------------------	
	
	//내가 쓴 글-->QnA
	public QnaPage qna_list(HashMap<String, Object>map) {
		QnaPage page = (QnaPage) map.get("page");
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("member.mapper.my_qna_total", map));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("member.mapper.my_qna_list", map));
		return page;
	}
	
	public QnaVO qna_detail(int id) {
		return sql.selectOne("member.mapper.my_qna_detail", id);
	}

	public void qna_update(QnaVO vo) {
		sql.update("member.mapper.my_qna_update", vo);
		
	}

	public void qna_delete(int id) {
		sql.delete("member.mapper.my_qna_delete", id);
		
	}

	public void qna_read(int id) {
		sql.update("member.mapper.my_qna_read", id);
		
	}
	
	
}
