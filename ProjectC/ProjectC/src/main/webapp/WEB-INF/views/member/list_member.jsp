<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{text-decoration: none;}
</style>
</head>
<%-- <jsp:include page="/WEB-INF/views/include/header.jsp" /> --%>
<body>
<!-- 	<div id='content'> -->
	<h3>[회원 리스트]</h3>
		<div id ='list-top'>
			<form method="post" action="member.admin">
				<div>
					<ul>
						<li>회원 유형</li>
						<li>
							<select name='member_name' onchange="$('form').submit()">
								<option value="all" ${vo.type eq 'all' ? 'selected' : '' }>전체</option>
								<option value="user" ${vo.type eq 'user' ? 'selected' : ''}>사용자</option>								
								<option value="producer" ${vo.type eq 'producer' ? 'selected' : ''}>기획자</option>								
								<option value="admin" ${vo.type eq 'admin' ? 'selected' : ''}>관리자</option>																
							</select>
						</li>
					</ul>				
				</div>
			</form>
		</div>
		<table>
			<thead>
				<tr class='w-px100'>
					<th>이름</th>
					<th>이메일</th>
					<th>닉네임</th>
					<th>회원유형</th>
					<th>주소</th>
					<th>가입날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="vo">
					<tr>
						<td>${vo.name }</td>
						<td><a href="member_detail.admin?email=${vo.email}">${vo.email }</a></td>
						<td>${vo.nickname }</td>
						<td>${vo.type }</td>
						<td>${vo.address }</td>
						<td>${vo.joindate }</td>
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
<!-- 	</div> -->
</body>
<%-- <jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</html>







