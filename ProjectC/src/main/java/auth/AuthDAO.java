package auth;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDAO {
	
	@Autowired @Qualifier("cteam") private SqlSession sql;

	public int auth_insert(AuthVO vo) {
		return sql.insert("auth.mapper.insert", vo);
	}

	public int auth_delete(int no) {
		return sql.update("auth.mapper.delete", no);
	}

	public int auth_update(AuthVO vo) {
		return sql.update("auth.mapper.update", vo);
	}
	public AuthVO auth_detail(int no) {
		return sql.selectOne("auth.mapper.detail", no);
	}

	public AuthPage auth_list(AuthPage page) {
		//전체 인증글 수 조회
		page.setTotalList(sql.selectOne("auth.mapper.totalList", page));
		
		//페이징 처리된 전체 인증글 조회
		page.setList(sql.selectList("auth.mapper.list", page));
		return page;
	}

	public AuthVO auth_check(int checked) {
		return sql.selectOne("auth.mapper.check", checked);
	}

}
