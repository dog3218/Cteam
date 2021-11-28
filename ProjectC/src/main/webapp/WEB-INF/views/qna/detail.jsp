<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>QnA</h3> 
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td class='left' colspan="5">${vo.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${vo.name }</td>
			<th class='w-px100'>작성일자</th>
			<td>${vo.writedate }</td>
			<th class='w-px80'>조회수</th>
			<td>${vo.readcnt }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td class='left' colspan="5">
				${fn:replace(vo.content, crlf, '<br>')   }
			<!-- 내용 중 엔터에 해당되는 부분을 <br> 태그로 치환 -->
			</td>
		</tr>
	</table>
	<div class='btnSet'>
		<a class='btn-fill' 
		href='list.no?curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>목록으로</a>
		<c:if test="${vo.writer eq loginInfo.id }">
			<a class='btn-fill' href='modify.qna?id=${vo.id }'>수정</a>
			<a class='btn-fill' 
				onclick=" if(confirm('정말 삭제하시겠습니까?')) { href='delete.qna?id=${vo.id }'} ">삭제</a>
		</c:if>
		<!-- 로그인되어 있는 경우 답글 쓰기 가능 -->
		<c:if test="${! empty loginInfo }">		
			<a class='btn-fill' href='reply.qna?id=${vo.id }'>답글쓰기</a>
		</c:if>
	</div>
	
</body>
</html>









