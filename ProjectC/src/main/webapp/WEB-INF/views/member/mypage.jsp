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
{ margin-top: 150px;
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
<h2>사용자 마이페이지</h2>
<div id="mypage">
<!-- 	<form method="post" action="list.co"></form> -->
	<ul>
		<li>
			<div id="modify"><a class='w-pct50' href="detail.my">정보수정</a></div>
			<div id="like"><a class='w-pct50' >좋아요</a></div>
			<div id="auth"><a class='w-pct50' href="mj_new.auth">기획자 인증하기</a></div>
			<div id="post">
			<select name="subject" onchange="location.href=this.value">
			<option value='' selected>내가 쓴 글</option>
			<c:if test="${loginInfo.type eq 'producer'}">
			<option value='post_info.my' ></option>
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