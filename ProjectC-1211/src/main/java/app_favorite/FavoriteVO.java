package app_favorite;

public class FavoriteVO {
	private String likemember, target_type;
	private int post_id_board,  post_id_community , no;
	
	
	public String getLikemember() {
		return likemember;
	}
	public void setLikemember(String likemember) {
		this.likemember = likemember;
	}
	public String getTarget_type() {
		return target_type;
	}
	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPost_id_board() {
		return post_id_board;
	}
	public void setPost_id_board(int post_id_board) {
		this.post_id_board = post_id_board;
	}
	public int getPost_id_community() {
		return post_id_community;
	}
	public void setPost_id_community(int post_id_community) {
		this.post_id_community = post_id_community;
	}
	
	
}
