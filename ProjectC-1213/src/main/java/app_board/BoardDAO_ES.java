package app_board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO_ES {
	@Autowired
	@Qualifier("cteam")
	SqlSession sql;

	public List<BoardVO> board_favorite() {
		return sql.selectList("board.mapper.favorite_es");
	}

	public List<BoardVO> board_review() {
		return sql.selectList("board.mapper.review_es");
	}

	public List<BoardVO> board_read() {
		return sql.selectList("board.mapper.read_es");
	}

	public void board_readcnt(int no) {
		sql.update("board.mapper.readcnt_es", no);
	}

}
