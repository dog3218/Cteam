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
</style>
</head>
<body>
<h2>기획자 인증</h2>
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td colspan="5" class='left'>${vo.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class='left'>${vo.writer}</td>
		<th class='w-px120'>등록일자</th>
		<td class='w-px120'>${vo.writedate }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class="left"> ${fn:replace(vo.content, crlf, '<br>')}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td colspan="5" class="left">${vo.filename1 }
			<c:if test="${! empty vo.filename1 }">
				<a id = 'preview'></a>
				<a href='download.auth?no=${vo.no}'><i class="fas fa-download font-img"></i></a>		
			</c:if>
		</td>
	</tr>
</table>
<div class='btnSet'>
	<c:if test="${loginInfo.email eq vo.writer }">		
		<a class='btn-fill' onclick= '$("form").attr("action", "modify.auth"); 
		$("form").submit()' >수정</a>
		<a class='btn-fill' onclick="go_mypage()">취소</a>
	</c:if>
</div>
<script type="text/javascript">
function go_mypage(){
	if(confirm("마이페이지로 돌아가시겠습니까??")){
		location.href="mypage";
		return true;
	}else{
		return false;
	}
}


</script>
</body>
</html>