package app_auth;


public class AuthVO {
	private int no;
	private String content, filenam1, writer, writedate, title, checked;	
	
	public AuthVO() {
		
	}
	
	public AuthVO(int no, String content, String filenam1, String writer, String writedate, String title,
			String checked) {
		super();
		this.no = no;
		this.content = content;
		this.filenam1 = filenam1;
		this.writer = writer;
		this.writedate = writedate;
		this.title = title;
		this.checked = checked;
	}
	
	public String getFilenam1() {
		return filenam1;
	}
	public void setFilenam1(String filenam1) {
		this.filenam1 = filenam1;
	}	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
