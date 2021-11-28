<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 비밀번호 확인 절차 후 정보 띄우기 -->
<h2>비밀번호 확인</h2>
<div id='confirm'>
<input type="password" placeholder="비밀번호" id='userpw'/>
<a class='btn-fill' onclick="go_modify()">확인</a>
</div>
<script type="text/javascript">
function go_modify(){
	if($('#userpw').val() == ''){
		alert('비밀번호를 입력하세요');
		$('#userpw').focus();
		return;
	}
	$.ajax({
		url : 'confirm_pw',
		data : {pw:$('#userpw').val() },
		success : function(response) {
			if ( response ) {
				location = 'modify.my';
			} else {
				alert("비밀번호가 일치하지 않습니다.")
			}
		}, error : function (req, text) {
			alert(text + ':' + req.status);
		}
	});
}




</script>
</body>
</html>