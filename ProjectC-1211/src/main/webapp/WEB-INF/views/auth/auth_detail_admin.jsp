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
	a{text-decoration: none;}
</style>
</head>
<body>
<!-- 관리자가 보는 페이지 -->
<h2>기획자 인증</h2>
<table>
	<tr>
		<th class='w-px100'>제목</th>
		<td colspan="4" class='left'>${vo.title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class='left'>${vo.name}</td>
		<th class='w-px120'>등록일자</th>
		<td class='w-px120'>${vo.writedate }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class="left"> ${fn:replace(vo.content, crlf, '<br>')}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td colspan="5" class="left">${fn:substring(vo.filename1, fn:indexOf(vo.filename1, '_') +1, fn:length(vo.filename1)) }
<%-- 		<td colspan="5" class="left">${vo.filename1} --%>
			<c:if test="${! empty vo.filename1 }">
				<a id = 'preview'></a>
<%-- 				<a href='download.auth?no=${vo.no }'><i class="fas fa-download font-img"></i></a>	 --%>	
			</c:if>
		</td>
	</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill' href="auth_list.admin">목록으로</a>
	<a class='btn-fill' onclick="if (confirm ('정말 인증하시겠습니까?') )
	{ href='check.auth?no=${vo.no }&writer=${vo.writer }'  } ">인증하기</a>
	
	
<!-- 		<a class='btn-fill' onclick= '$("form").attr("action", "check.auth"); 
		$("form").submit()' >인증하기</a> -->
</div>

<!-- 이미지를 크게 볼 수 있도록 처리 (popup 형태) -->
<div id='popup-background'></div>
<div id='popup' class='center'></div>
<script type="text/javascript" src='js/file_check.js'></script>
<script type="text/javascript">
	$(function () {
		// 첨부된 파일이 이미지 파일인 경우 미리보기 되게끔..
		if ( ${ ! empty vo.filename1} ) { // 첨부 파일이 있다면
			if ( isImage ( '${vo.filename1}' )) { // 이미지 파일이면
				$('#preview').html('<img src="${vo.filename1}" id="preview-img" />' );
			}
		}
		comment_list();		// 댓글 목록 조회 함수 호출
	});

	$(document).on('click', '#preview-img', function () {
		$('#popup, #popup-background').css('display', 'block');
		$('#popup').html('<img src="${vo.filename1}" class="popup" /> ');
	}).on('click', '#popup-background', function () {
		$('#popup, #popup-background').css('display', 'none');
	})

</script>
</body>
</html>