package auth;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class AuthPage extends PageVO{
	
	private List<AuthVO> list;

	public List<AuthVO> getList() {
		return list;
	}

	public void setList(List<AuthVO> list) {
		this.list = list;
	}
	

}
