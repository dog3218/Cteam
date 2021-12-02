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

#auth
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
<h2>관리자 마이페이지</h2>
<div id="mypage">
<!-- 	<form method="post" action="list.co"></form> -->
	<ul>
		<li>
			<div id="auth"><a class='w-pct50' href="list.auth">기획자 인증글 목록</a></div>
		</li>
	</ul>
</div>
</body>
</html>