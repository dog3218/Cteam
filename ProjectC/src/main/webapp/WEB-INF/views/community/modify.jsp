<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시글 수정</h3>
<form action="update.co" method="post" enctype="multipart/form-data">
	<input type="hidden" name="attach" />
	<input type="hidden" name='id' value="${vo.id }" />
	<table>
		<tr>
			<th class='w-px120'>말머리</th>
			<td class='left middle'><select name='subject'>
				<option value='' selected>--말머리--</option>
				<option value='홍보글' >홍보글</option>
				<option value='동행구해요' >동행구해요</option>
				<option value='자유' >자유</option>
			</select></td>
		</tr> 
		<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type="text" name="title" class="chk" title='제목' value="${vo.title }" /> 
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name='content' class='chk' title='내용'>${vo.content }</textarea> 
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left middle'>
				<label>
					<input type="file" name='file' id='attach-file'>
					<a><img src='imgs/attach.png' class='file-img' /></a>
				</label>
				<span id='file-name'>${vo.filename1 }</span>
				<span id='preview'></span>   <!-- 미리보기 이미지가 나오는 곳 -->
				<a id='delete-file'><i class='fas fa-times font-img'></i></a>
			</td>
		</tr>
	</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick=" if (emptyCheck() ) {
		$('[name=attach]').val($('#file-name').text()); $('form').submit()}">저장</a>
	<a class='btn-empty'>취소</a>
</div>
<script type="text/javascript" src='js/file_check.js'></script>
<script type="text/javascript">
	if ( ${ ! empty vo.filename1} ) {
		// 첨부 파일이 있는 글의 경우 삭제버튼 보이게
		$('#delete-file').css('display', 'inline');
		// 첨부 파일이 이미지 파일인 경우 미리보기 되게
		if ( isImage ( '${vo.filename1}' ) )
			$('#preview').html('<img src="${vo.filepath1}" id="preview-img" /> ');
	}
</script>
</body>
</html>





