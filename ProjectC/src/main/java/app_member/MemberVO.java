package app_member;


public class MemberVO {
    private String  nickname, password, address, email, idnumber, filepath, name, type, joindate, naver, kakao, google;

    
    
	public MemberVO() {
		super();
	}



	public MemberVO(String nickname, String password, 
			String address, String email, String idnumber, 
			String filepath,
			String name, String type, String joindate, String naver, 
			String kakao, String google) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.address = address;
		this.email = email;
		this.idnumber = idnumber;
		this.filepath = filepath;
		this.name = name;
		this.type = type;
		this.joindate = joindate;
		this.naver = naver;
		this.kakao = kakao;
		this.google = google;
	}


	public MemberVO(String nickname, String password,
			String address, String email, String idnumber, String name) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.address = address;
		this.email = email;
		this.idnumber = idnumber;
		this.name = name;
	}


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getNaver() {
		return naver;
	}



	public void setNaver(String naver) {
		this.naver = naver;
	}



	public String getKakao() {
		return kakao;
	}



	public void setKakao(String kakao) {
		this.kakao = kakao;
	}



	public String getGoogle() {
		return google;
	}



	public void setGoogle(String google) {
		this.google = google;
	}
    
	
    
}