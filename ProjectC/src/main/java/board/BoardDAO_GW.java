package board;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO_GW {
	
	@Autowired @Qualifier("cteam")SqlSession sql;

	public int board_insert(BoardVO dto) {
		
		
		return sql.insert("board.mapper.board_insert_gw", dto);
	}

	public void board_clear() {
		sql.delete("board.mapper.board_clear_gw");		
	}
	
	public List<BoardVO> board_list() {
		
		return sql.selectList("board.mapper.board_list_gw");
		
	}
	
}
