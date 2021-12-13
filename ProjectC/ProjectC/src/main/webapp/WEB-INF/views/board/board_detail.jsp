<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#popup { width: 450px; height: 450px; border: 2px solid #777; display: none; }
	.popup { width: 100%; height: 100%;}
	
	.comment { margin: 0 auto; padding-top: 20px; width: 500px;}
	#comment_regist { width: 100%;}
	#comment_regist span { width: 50%; float: left; }
	
	textarea#comment { width: 96%; height: 60px; margin-top: 5px; resize: none; }
</style>
 

</head>
<body>
<h2>공연정보</h2>
<table>
<tr>
		<td colspan="6" ><img src="/app/imgs/${vo.filepath }" /></td>
	</tr>
	<tr>
		<th class='w-px120'>제목</th>
		<td colspan="5" class='left'>${vo.eventnm }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class='left'>${vo.writer }</td>
		<th class='w-px120'>작성일자</th>
		<td class='w-px120'>${vo.referencedate }</td>
		<th class='w-px80'>조회수</th>
		<td class='w-px80'>${vo.readcnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class="left"> ${fn:replace(vo.eventco, crlf, '<br>')}</td>
	</tr>
</table>

<div class='btnSet'>
	<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
	<c:if test="${loginInfo.email eq vo.writer }">		
		<a class='btn-fill' onclick= '$("form").attr("action", "modify.bo"); 
		$("form").submit()' >수정</a>
		<a class='btn-fill' onclick="if (confirm ('정말 삭제하시겠습니까?') )
		 { href='delete.bo?no=${vo.no }'  } ">삭제</a>
	</c:if>
</div>


<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form method="post" action="list.bo">
	<input type="hidden" name='no' value="${vo.no }">					<!-- 글 id -->
	<input type="hidden" name='search' value ='${page.search }' /> 		<!-- 검색조건 -->
	<input type="hidden" name='keyword' value ='${page.keyword }' />	<!-- 검색어 -->
	<input type="hidden" name='curPage' value ='${page.curPage }' />	<!-- 현재 페이지 -->
	<input type="hidden" name='viewType' value ='${page.viewType }' />	<!-- 게시판 형태 -->
	<input type="hidden" name='pageList' value ='${page.pageList }' />	<!-- 페이지당 보여질 목록수 -->
</form>

</body>
</html>









