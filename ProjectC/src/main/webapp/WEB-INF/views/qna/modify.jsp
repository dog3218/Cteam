<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js'></script>
</head>
<body>
<h3>QnA 수정</h3>
<form method="post" enctype="multipart/form-data" action="update.qna">
	<input type="hidden" name='id' value="${vo.id }" />
	<input type="hidden" name='attach' />
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td><input type="text" name='title' title='제목' 
				value="${vo.title }" class='chk' /></td>
		</tr> 
		<tr>
			<th>내용</th>
			<td><textarea name='content' class='chk' title='내용'>${vo.content}</textarea></td>
		</tr>
	
	</table>
</form>
<div class='btn-Set'>
	<a class='btn-fill' 
	onclick =" if ( emptyCheck() ) { $('[name=attach]').val($('#file-name').text() );  $('form').submit()  } " >저장</a>
	<a class='btn-empty' href='detail.qna?id=${vo.id}'>취소</a>

</div>
















</body>
</html>