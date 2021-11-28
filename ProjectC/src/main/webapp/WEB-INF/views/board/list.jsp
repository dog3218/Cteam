<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//js/jquery-3.5.1.js"></script>
</head>
<body>
	<form action="board.bo" method="post">
		<input type="submit" value="DB에 저장"/>
		<input type="hidden" name="command" value="boarddb"/>
		<table>
			<thead>
			</thead>
			<tbody>
			</tbody>
			
		</table>
	</form>
</body>
</html>