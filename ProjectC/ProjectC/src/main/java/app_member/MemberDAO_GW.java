package app_member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import app_board.BoardVO;


@Repository
public class MemberDAO_GW{
	
		@Autowired @Qualifier("cteam") SqlSession sql;
		List<BoardVO> nearEventdtos = new ArrayList<BoardVO>();
	
		
		//8. 로그인 : anLoginCommand에서 값을 넘겨받는다
		public MemberVO member_login(HashMap<String, String> map) {
			MemberVO vo =sql.selectOne("member.mapper.login_app_gw", map);
			return vo;
		}
		
		
		//회원전체 정보 가져오기
		public ArrayList<MemberVO> member_list() {
			List<MemberVO> list =  sql.selectList("member.mapper.memberList_app_gw");
			
			ArrayList<MemberVO> dtos = new ArrayList<MemberVO>();
			for(int i = 0; i < list.size(); i++){
				
			MemberVO memberVO = new MemberVO();
			memberVO.setNickname(list.get(i).getNickname());
			memberVO.setPassword(list.get(i).getPassword());
			memberVO.setAddress(list.get(i).getAddress());
			memberVO.setEmail(list.get(i).getEmail());
			memberVO.setIdnumber(list.get(i).getIdnumber());
			memberVO.setFilepath(list.get(i).getFilepath());
			memberVO.setName(list.get(i).getName());
			memberVO.setType(list.get(i).getType());
			memberVO.setJoindate(list.get(i).getJoindate());
			memberVO.setNaver(list.get(i).getNaver());
			memberVO.setKakao(list.get(i).getKakao());
			memberVO.setGoogle(list.get(i).getGoogle());
			
			dtos.add(memberVO);
					
			}
			return dtos;
		}
		
		//9.로그인 정보 가져오기
		public List<MemberVO> anSelectMember() {
			
			// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
			List<MemberVO> dtos = sql.selectList("member.mapper.memberList_gw");
			return dtos;
			
		}



		public int member_join(MemberVO vo) {
			System.out.println(vo.getEmail());
			return sql.insert("member.mapper.member_join_app_gw", vo);
		}
		public int member_id_chk(String email) {
			// TODO Auto-generated method stub
			return sql.selectOne("member_gw.mapper.id_check_app_gw", email);
		}
		
		
		//안드로이드에서 전송받은 위치와 DB에서 가져온 값을 비교해서 0.5이하의 값만 안드로이드로 보내줌
		public List<BoardVO> nearEventList(Double lang, Double lati) {
			
			List<BoardVO> list = sql.selectList("board.mapper.all_gm");
			nearEventdtos.clear();
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getLatitude()!=null||list.get(i).getLongitude()!=null) {
					
					Double distance = getDistance(lang, lati, 
					Double.parseDouble(list.get(i).getLatitude()), Double.parseDouble(list.get(i).getLongitude()));
					
					if(distance<0.125) {
						System.out.println(i+"까지의 거리 " + distance);
						nearEventdtos.add(list.get(i));
					}	
				}
			}
			for(int i = 0; i<nearEventdtos.size(); i++) {
				System.out.println(nearEventdtos.get(i).getRdnmadr()+", "+nearEventdtos.get(i).getLnmadr());
			}
			return nearEventdtos;
		}

		//두 점 간의 거리 구하기
				public double getDistance (Double x, Double y, Double x1,Double y1){
					System.out.println(x+", " + y + ", "+x1+", "+ y1);
					
					return Math.sqrt(Math.pow(Math.abs(x1-x), 2) + Math.pow(Math.abs(y1-y), 2));
					}


}












