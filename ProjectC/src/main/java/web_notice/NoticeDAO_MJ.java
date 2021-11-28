package web_notice;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO_MJ {
	
	@Autowired @Qualifier("cteam") private SqlSession sql;

	public int notice_insert(NoticeVO vo) {
		return sql.insert("notice.mapper.insert_mj", vo);
	}

	public NoticePage notice_list(NoticePage page) {
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("notice.mapper.totalList_mj", page));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("notice.mapper.list_mj", page));
		return page;
	}

	public NoticeVO notice_detail(int id) {
		return sql.selectOne("notice.mapper.detail_mj", id);
	}

	public int notice_read(int id) {
		return sql.update("notice.mapper.read_mj", id);
	}

	public int notice_update(NoticeVO vo) {
		return sql.update("notice.mapper.update_mj", vo);
	}

	public int notice_delete(int id) {
		return sql.delete("notice.mapper.delete_mj", id);
	}

}
