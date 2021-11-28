<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항 글쓰기</h3>
<form method="post" enctype="multipart/form-data" action="insert.no">
	<table>
		<tr>
			<th>제목</th>
			<td class='left middle'><input type="text" name='title' title='제목' class='chk' /></td>
		</tr>
		<tr> 
			<th>내용</th>
			<td><textarea name='content' title='내용' class='chk' ></textarea></td>
		</tr>
	</table>

</form>
<div class='btnSet'>
	<a class='btn-fill' onclick = ' if ( emptyCheck() ) $("form").submit() '>저장</a>
	<a class='btn-empty' href='list.no'>취소</a>
</div>

<script type="text/javascript" src='js/file_check.js?v<%=new Date().getTime()%>'></script>

</body>
</html>






