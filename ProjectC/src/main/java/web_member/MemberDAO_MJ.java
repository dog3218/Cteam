package web_member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import app_member.MemberVO;

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
	

}
