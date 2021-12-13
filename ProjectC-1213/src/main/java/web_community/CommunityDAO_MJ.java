package web_community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import app_community.CommentVO;
import app_community.CommunityPage;
import app_community.CommunityVO;

@Repository
public class CommunityDAO_MJ{

	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public int community_insert(CommunityVO vo) {
		return sql.insert("community.mapper.insert_mj", vo);
	}

	public CommunityPage community_list(CommunityPage page) {
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("community.mapper.totalList_mj", page));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("community.mapper.list_mj", page));
		return page;

	}

	public CommunityVO community_detail(int id) {
		return sql.selectOne("community.mapper.detail_mj", id);
	}

	public int community_read(int id) {
		return sql.update("community.mapper.read_mj", id);
	}

	public int community_update(CommunityVO vo) {
		return sql.update("community.mapper.update_mj", vo);
	}

	public int community_delete(int id) {
		return sql.delete("community.mapper.delete_mj", id);
	}

	public int comment_insert(CommentVO vo) {
		return sql.insert("community.mapper.comment_insert_mj", vo);
	}

	public int comment_update(CommentVO vo) {
		return sql.update("community.mapper.comment_update_mj", vo);
	}

	public int comment_delete(int id) {
		return sql.update("community.mapper.comment_delete_mj", id);
	}

	public List<CommentVO> comment_list(int pno) {
		return sql.selectList("community.mapper.comment_list_mj", pno);
	}

}
