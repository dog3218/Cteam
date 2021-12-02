<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a{text-decoration: none;}
</style>
</head>
<body>
<!-- 회원정보 보여주기 후 비번 확인 절차로 넘어가기 -->
<h3>회원 정보</h3>
		<table class='w-pct40'>
			<tr>
				<th class='w-px120'>성명</th>
				<td >${vo.name }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${vo.email}</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>${vo.nickname }</td>
			</tr>
			
			<tr>
				<th>회원유형</th>
				<td>${vo.type }</td>
			</tr>
			
			<tr>
				<th>가입날짜</th>
				<td>${vo.joindate }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${vo.address}</td>
			</tr>
		</table>
	
	<div class='btnSet'>
		<a class='btn-fill' onclick = 'go_modify()'>정보 수정</a>
		<a class='btn-empty' href='<c:url value="/" />'>취소</a>		
	</div>
	
<script type="text/javascript">
function go_modify(){
	if(confirm("회원 정보를 수정하시겠습니까?")){
		location.href="confirm.my";
		return true;
	}else{
		return false;
	}

}





</script>


</body>
</html>