<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js'></script>
</head>
<body>
<h3>답글 쓰기</h3>
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
			<th>내용</th>
			<td class='left' colspan="5">
				${fn:replace(vo.content, crlf, '<br>')   }
			<!-- 내용 중 엔터에 해당되는 부분을 <br> 태그로 치환 -->
			</td>
		</tr>
	</table>

<form action="reply_insert.qna" method="post" enctype="multipart/form-data" >
	<!-- 답글에 필요한 root, step, indent 값을 hidden 으로 전달 -->
	<input type="hidden" name="id" value="${vo.id }" />
	<input type="hidden" name="root" value="${vo.root }" />
	<table> 
		
		<tr>
			<th>내용</th>
			<td><textarea name='reply_content' title='내용' class='chk' ></textarea></td> 
		</tr>
	</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<a class='btn-empty' href='list.qna'>취소</a>
</div>
</body>
</html>