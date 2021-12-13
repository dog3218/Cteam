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
			<th class='w-px120'>질문 제목</th>
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
			<th>질문 내용</th>
			<td class='left' colspan="5">
				${fn:replace(vo.content, crlf, '<br>')   }
			<!-- 내용 중 엔터에 해당되는 부분을 <br> 태그로 치환 -->
			</td>
		</tr>
		<!--답변 내용이 있을 경우 답변 내용 표시  -->
		<c:if test="${vo.reply_content ne null}">
		
		<tr>
			<th>답변자</th>
			<td>${vo.replyid }</td>
			<th class='w-px100'>답변일자</th>
			<td>${vo.writedate }</td>
<%-- 			<th class='w-px80'>조회수</th>
			<td>${vo.readcnt }</td> --%>
		</tr>
		<tr>
			<th>답변 내용</th>
			<td class='left' colspan="5">
				${fn:replace(vo.reply_content, crlf, '<br>')   }
			<!-- 내용 중 엔터에 해당되는 부분을 <br> 태그로 치환 -->
			</td>
		</tr>
		</c:if>
	</table>
	
	<div class='btnSet'>
		<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
		<c:if test="${vo.writer eq loginInfo.email}">
			<a class='btn-fill' onclick= '$("form").attr("action", "modify_qna.my"); 
		$("form").submit()'>수정</a>
			<a class='btn-fill' 
				onclick=" if(confirm('정말 삭제하시겠습니까?')) { href='delete_qna.my?id=${vo.id }'} ">삭제</a>
		</c:if>
		<!-- 로그인되어 있는 경우 답글 쓰기 가능 -->
		<c:if test="${! empty loginInfo }">		
			<a class='btn-fill' href='reply.qna?id=${vo.id }'>답변하기</a>
		</c:if>
	</div>
	
<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form method="post" action="post_qna.my">
	<input type="hidden" name='id' value="${vo.id }">					<!-- 글 id -->
	<input type="hidden" name='search' value ='${page.search }' /> 		<!-- 검색조건 -->
	<input type="hidden" name='keyword' value ='${page.keyword }' />	<!-- 검색어 -->
	<input type="hidden" name='curPage' value ='${page.curPage }' />	<!-- 현재 페이지 -->
	<input type="hidden" name='viewType' value ='${page.viewType }' />	<!-- 게시판 형태 -->
	<input type="hidden" name='pageList' value ='${page.pageList }' />	<!-- 페이지당 보여질 목록수 -->
</form>
	
</body>
</html>









