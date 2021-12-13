<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>커뮤니티</h3>
<form method="post" enctype="multipart/form-data" action="insert.co">
	<table>
		<tr>
			<th class='w-px120'>말머리</th>
			<td class='left middle'><select name='subject'>
				<option value='' selected>--말머리--</option>
				<option value='공연후기' >공연후기</option>
				<option value='동행구해요' >동행구해요</option>
				<option value='자유' >자유</option>
			
			</select></td>
		</tr> 
		<tr>
			<th>제목</th>
			<td class='left middle'><input type="text" name='title' title='제목' class='chk' /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name='content' title='내용' class='chk' ></textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left middle'>
				<label>
					<input type="file" name='file' id='attach-file' />
					<a><i class="far fa-images"></i></a>
<!-- 					<a><img src='imgs/select.png' class='file-img' /></a> -->
				</label>
				<span id='file-name'></span>
				<!-- 이미지 파일인 경우 미리보기 적용 -->
				<span id="preview"></span>
				<a id='delete-file'><i class='font-img fas fa-minus-circle'></i></a>
			</td>
		</tr>
	</table>

</form>
<div class='btnSet'>
	<a class='btn-fill' onclick = ' if ( emptyCheck() ) $("form").submit() '>저장</a>
	<a class='btn-empty' href='list.co'>취소</a>
</div>

<script type="text/javascript" src='js/file_check.js?v<%=new Date().getTime()%>'></script>

</body>
</html>






