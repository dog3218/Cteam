package web_board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import app_board.BoardVO;

@Repository
public class BoardDAO_web_GW{

	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public int board_insert(BoardVO vo) {
		return sql.insert("board.mapper.insert", vo);
	}

	public BoardPage board_list(BoardPage page) {
		//전체 게시글 수 조회
		page.setTotalList(sql.selectOne("board.mapper.list_cnt_web_gw", page));
		
		//페이징 처리된 전체 게시글 조회
		page.setList(sql.selectList("board.mapper.board_list_gw", page));
		return page;
	}

	public BoardVO board_detail(int id) {
		return sql.selectOne("board.mapper.board_detail", id);
	}

	public int board_read(int no) {
		return sql.update("board.mapper.board_read_gw", no);
	}

	public int board_update(BoardVO vo) {
		return sql.update("board.mapper.", vo);
	}

	public int board_delete(int id) {
		return sql.delete("board.mapper.", id);
	}

	public int comment_insert(BoardVO vo) {
		return sql.insert("board.mapper.", vo);
	}

	public int comment_update(BoardVO vo) {
		return sql.update("board.mapper.", vo);
	}

	public int comment_delete(int id) {
		return sql.update("board.mapper.", id);
	}

	public List<BoardVO> comment_list(int pno) {
		return sql.selectList("board.mapper.", pno);
	}

}
