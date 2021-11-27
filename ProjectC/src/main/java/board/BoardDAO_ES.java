package board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO_ES {
	@Autowired @Qualifier("cteam")SqlSession sql;

	
	public List<BoardVO> es_board_list() {
		List<BoardVO> list =  sql.selectList("es.board.mapper.es_board_list");
		
		ArrayList<BoardVO> dtos = new ArrayList<BoardVO>();
		for (int i = 0; i < dtos.size(); i++) {
			BoardVO vo = new BoardVO();
			vo.setEventnm(dtos.get(i).toString());
			vo.setOpar(dtos.get(i).toString());
			vo.setEventco(dtos.get(i).toString());
			vo.setEventstartdate(dtos.get(i).toString());
			vo.setEventenddate(dtos.get(i).toString());
			vo.setEventstarttime(dtos.get(i).toString());
			vo.setEventendtime(dtos.get(i).toString());
			vo.setChrgeinfo(dtos.get(i).toString());
			vo.setMnnst(dtos.get(i).toString());
			vo.setAuspcinstt(dtos.get(i).toString());
			vo.setPhonenumber(dtos.get(i).toString());
			vo.setSuprtinstt(dtos.get(i).toString());
			vo.setSeatnumber(dtos.get(i).toString());
			vo.setAdmfee(dtos.get(i).toString());
			vo.setEntncage(dtos.get(i).toString());
			vo.setDscntinfo(dtos.get(i).toString());
			vo.setAtpn(dtos.get(i).toString());
			vo.setHomepageurl(dtos.get(i).toString());
			vo.setAdvantkinfo(dtos.get(i).toString());
			vo.setPrkplceyn(dtos.get(i).toString());
			vo.setRdnmadr(dtos.get(i).toString());
			vo.setLnmadr(dtos.get(i).toString());
			vo.setLatitude(dtos.get(i).toString());
			vo.setLongitude(dtos.get(i).toString());
			vo.setReferencedate(dtos.get(i).toString());
			vo.setFilepath(dtos.get(i).toString());
			vo.setWriter(dtos.get(i).toString());
			vo.setOn_offline(dtos.get(i).toString());
		}
		return list;
	}
	public List<BoardVO> board_favorite() {
	      return sql.selectList("board.mapper.favorite_es");
	   }
	   
	   public List<BoardVO> board_review() {
	      return sql.selectList("board.mapper.review_es");
	   }

	   public List<BoardVO> board_read() {
	      return sql.selectList("board.mapper.read_es");
	   }
	   
	public int es_board_insert(BoardVO vo) {
		return 0;
	}
	public int es_board_update(BoardVO vo) {
		return sql.update("es.board.mapper.es_board_update", vo);
	}

	public int es_board_delete(int id) {
		return 0;
	}

	public BoardVO es_board_detail(int id) {
		return sql.selectOne("es.board.mapper.es_board_detail", id);
	}

}
