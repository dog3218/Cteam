package app_board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO_GM {
	
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	
	public List<BoardVO> board_all() {
		List<BoardVO> dtos = sql.selectList("board.mapper.all_gm");
		return dtos;
	}

	
	public List<BoardVO> board_musical() {
		List<BoardVO> dtos = sql.selectList("board.mapper.musical_gm");
		return dtos;
	}
	
	
	public List<BoardVO> board_opera() {
		List<BoardVO> dtos = sql.selectList("board.mapper.opera_gm");
		return dtos;
	}
	
	
	public List<BoardVO> board_play() {
		List<BoardVO> dtos = sql.selectList("board.mapper.play_gm");
		return dtos;
	}
	
	public List<BoardVO> board_exhibition() {
		List<BoardVO> dtos = sql.selectList("board.mapper.exhibition_gm");
		return dtos;
	}
	
	public List<BoardVO> board_concert() {
		List<BoardVO> dtos = sql.selectList("board.mapper.concert_gm");
		return dtos;
	}
	
	public void board_insert(BoardVO vo) {
		// TODO Auto-generated method stub

	}

	public void board_delete(int id) {
		// TODO Auto-generated method stub

	}

	public void board_update(BoardVO vo) {
		// TODO Auto-generated method stub

	}


	public BoardVO board_detail(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}


}
