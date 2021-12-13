package app_community;

import java.util.List;

import org.springframework.stereotype.Component;

import web_common.PageVO;

@Component
public class CommunityPage extends PageVO {
	
	private List<CommunityVO> list;
	
	public List<CommunityVO> getList(){
		return list;
	}
	
	public void setList(List<CommunityVO> list) {
		this.list = list;
	}

}
