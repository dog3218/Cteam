package member;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO implements MemberService{
		@Autowired @Qualifier("cteam") SqlSession sql;
	
	// 생성자를 통해서 데이터베이스 드라이버를 선언해준다
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			
		}catch(NamingException e) {
			e.getMessage();
		}
		
	}	
	
	// 8. 회원가입 : anJionCommand에서 값을 넘겨받는다
	public int anJoin(String id, String nickname, String password, String address, String kind, String email, String idnumber, String filename, String name) {
		
		/*
		 * String query =
		 * "insert into member(id, nickname, password, address, kind, email, idnumber, filename, name) "
		 * + " values('" + id + "', '" + nickname + "', '" + password + "', '" + address
		 * + "', '" + kind + "', '" + email + "', '" + idnumber + "', '"+filename
		 * +"', '"+name+"')";
		 */
		
		
		return 0;
		
	}
	
		//8. 로그인 : anLoginCommand에서 값을 넘겨받는다
		public MemberVO member_login(HashMap<String, String> map) {
			
			return sql.selectOne("member.mapper.login", map);
		}
		
		
		//회원전체 정보 가져오기
		public ArrayList<MemberVO> member_list() {
			List<MemberVO> list =  sql.selectList("member.mapper.memberList");
			
			ArrayList<MemberVO> dtos = new ArrayList<MemberVO>();
			for(int i = 0; i < list.size(); i++){
			dtos.add(new MemberVO(list.get(i).getId()
					,list.get(i).getNickname()
					,list.get(i).getPassword()
					,list.get(i).getAddress()
					,list.get(i).getEmail()
					,list.get(i).getIdnumber()
					,list.get(i).getFilepath()
					,list.get(i).getFilename()
					,list.get(i).getName()
					,list.get(i).getKind()
					,list.get(i).getJoindate()
					
					));
			}
			return dtos;
		}
		
		//9.로그인 정보 가져오기
		public List<MemberVO> anSelectMember() {
			
			// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
			List<MemberVO> dtos = sql.selectList("member.mapper.memberList");
			return dtos;
			
		}

		@Override
		public int member_join(MemberVO vo) {
			return sql.insert("member.mapper.member_join", vo);
		}

		@Override
		public void member_delete(int id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void member_update(MemberVO vo) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int member_id_chk(String id) {
			// TODO Auto-generated method stub
			return sql.selectOne("member.mapper.id_check", id);
		}

		

}











