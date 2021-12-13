<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css"> 
#login { width: 70%; height: calc(100% - 50%); padding: 50px 0;	float: left; }

#useremail, #userpw {	width: 48%;	padding: 5px 10%;	margin-bottom: 10px }

.btn-fill { margin: 0 auto; width: 60.5%;height: 42px !important;
	line-height: 42px;	box-shadow: none !important; /* !important 우선순위 지정 */
}


img.social { width: 200px; 	height: 45px; }

/* img.social:first-child {margin-bottom:10px;} */

/* .login_f_left {float: left;} */

.join {	float: right;	width: 30%;	padding: 210px 0;
	background: #c2c2c2;
} /* height:calc(100% - 50%);  align-items: center;  width:30%; */

.join div {	position: absolute;	top: 50%;	left: 85%;
	transform: translate(-50%, -50%);
}
</style>
</head>

<body>

	<div style="width: 700px; border: 1px solid #ccc;" class='center'>
		<!-- height:calc(100% - 55%); common.css .center 추가 -->
		<div id='login'>
			<div style="height: 70px">
				<a href='<c:url value="/" />'><img src='imgs/exhibition_logo.png' /></a>
			</div>
			<div>
				<a href='naverLogin'><img src='imgs/naver_login1.png' class='social' /></a>
				<a href='kakaoLogin'><img src='imgs/kakao_login1.png' class='social' /></a>

				<div style='width: 80%; margin: 25px auto; border: 1px solid #ccc'></div>
				<!-- hr 효과 -->

				<input type="text" placeholder="이메일" id='useremail' autofocus value="1@naver.com"/> 
				<input type="password" placeholder="비밀번호" id='userpw' value="1"
						onkeypress = "if (event.keyCode == 13) go_login()" /><br>
			<!-- onkeypress : keyCode 13번(Enter Key)을 눌렀을 때 go_login() 처리 -->	
					<a class='btn-fill' onclick="go_login()">로그인</a>
			</div>
		</div>
		<div class='join'>
			<div>
				아직도 회원이<br>아니신가요?<br> <a class='btn-fill' href="#" onclick="go_join();">회원가입</a>
				<!-- 회원가입 누르면 회원가입으로 넘어가야함 -->
			</div>
		</div>
	</div>
	
<script type="text/javascript">
function go_login() {
	// 아이디 입력값이 없으면
	if($('#useremail').val() == '') {
		alert('아이디를 입력하세요!');
		$('#useremail').focus();
		return;
	// 비밀번호 입력값이 없으면 	
	} else if($('#userpw').val() == '' ) {
		alert('비밀번호를 입력하세요!');
		$('#userpw').focus();
		return;
	} 
	
	// login 을 위한 ajax 통신 설정 
	$.ajax({
		url : 'iotLogin',
		data : {email:$('#useremail').val(), pw:$('#userpw').val() },
		success : function(response) {
			if ( response ) {
				location = '<c:url value ="/" />';
			} else {
				alert("아이디나 비밀번호가 일치하지 않습니다.")
			}
		}, error : function (req, text) {
			alert(text + ':' + req.status);
		}
	});
	
	
}

function go_join(){
	if(confirm("회원가입을 하시겠습니까?")){
		location.href="member_join";
		return true;
	}else{
		return false;
	}
}



</script>	
	
</body>
</html>