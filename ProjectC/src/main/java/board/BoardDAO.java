package board;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements BoardService{
	
	@Autowired @Qualifier("cteam")SqlSession sql;
//	@Autowired BoardVO vo;	

	public int board_insert(BoardVO dto) {
		
		
		return sql.insert("board.mapper.board_insert", dto);
	}

	@Override
	public void board_clear() {
		sql.delete("board.mapper.board_clear");		
	}
	
}