<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a { text-decoration:none;
	color: black;}
a.hover {color: black; font-weight: bolder;}
#mypage
{ margin-top: 50px;
  position: relative;
}

#modify, #like, #post, #auth
{ width: 30%;
  height: 100px;
  padding: 20px 0;
  border: 1px solid black;
  margin-bottom: 10px;
  margin: 0px atuo;
  line-height: 50px;
  display:inline-block;	
  vertical-align:middle;
  	color: black;
}

#mypage > ul > li {
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
}


</style>
</head>
<body>
<h2>마이페이지</h2>
<div id="mypage">
<!-- 	<form method="post" action="list.co"></form> -->
	<ul>
		<li>
			<div id="modify"><a class='w-pct50' href="detail.my?email=${loginInfo.email}">정보수정</a></div>
			<div id="like"><a class='w-pct50' >좋아요</a></div>
			
			
			<c:choose>
				<c:when test="${loginInfo.email eq vo.writer && vo.checked eq 'N' }">
					<div id="auth"><a class='w-pct50' href="detail.auth?no=${vo.no}">기획자 인증 확인중</a></div>
				</c:when>
				<c:when test="${loginInfo.type eq 'producer'}">
					<div id="auth">기획자 인증 성공</div>
<!-- 					<div id="auth"><a class='w-pct50'>기획자 인증 성공</a></div> -->
				</c:when>
				<c:otherwise>
					<div id="auth"><a class='w-pct50' href="new.auth">기획자 인증관리</a></div>						
				</c:otherwise>
			</c:choose>
			
<%-- 			<c:forEach items="${page.list }" var="vo">			 --%>
<%-- 				<c:if test="${vo.checked eq 'N'}"> --%>
<!-- 					<div id="auth"><a class='w-pct50' href="new.auth">기획자 인증관리</a></div> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${loginInfo.email eq vo.writer}"> --%>
<!-- 					<div id="auth"><a class='w-pct50' href="detail.auth">기획자 인증 확인중</a></div> -->
<%-- 				</c:if> --%>
<%-- 			</c:forEach> --%>
<%-- 				<c:if test="${loginInfo.type eq 'producer'}"> --%>
<!-- 					<div id="auth"><a class='w-pct50'>기획자 인증 성공</a></div> -->
<%-- 				</c:if> --%>
			<div id="post">내가 쓴 글
			<select name="subject" onchange="location.href=this.value">
			<option value='' selected>--선택하세요-- </option>
			<c:if test="${loginInfo.type eq 'producer'}">
			<option value='post_bo.my' >공연정보</option>
			</c:if>
			<option value='post_comm.my'>커뮤니티</option>
			<option value='post_qna.my'>QnA</option>
			</select>
			
			
			</div>

		</li>
	</ul>
</div>
</body>
</html>