<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{text-decoration: none;}
</style>
</head>
<body>
	<h3>회원 상세 정보 페이지</h3>
	<table class='w-pct40'>
		<tr>
			<th class='w-px120'>이름</th>
			<td>${vo.name }</td>
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
			<th>주소</th>
			<td>${vo.address }</td>
		</tr>
		<tr>
			<th>가입날짜</th>
 			<td>${vo.joindate }</td>
		
		</tr>
	</table>
	<div class='btnSet'>
		<a class='btn-fill' href='member.admin'>회원목록</a>
		<a class='btn-fill' onclick="if (confirm ('정말 삭제하시겠습니까?') )
		 { href='member_delte.admin?email=${vo.email}'  } ">회원 삭제</a>
		
	</div>
	
</body>
</html>