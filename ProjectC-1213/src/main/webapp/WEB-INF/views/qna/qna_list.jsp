<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>QnA</h3>
<form action="list.qna" method="post">
<input type="hidden" name="curPage" value="1" /> 
<input type="hidden" name='id' /> <!-- detail 에 보내질 id  -->
<div id='list-top'>
	<div>
		<ul>
			<li>
				<select name='search' class='w-px90'>
					<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
					<option value="title" ${page.search eq 'title' ? 'selected' : '' }>제목</option>
					<option value="content" ${page.search eq 'content' ? 'selected' : '' }>내용</option>
					<option value="writer" ${page.search eq 'writer' ? 'selected' : '' }>작성자</option>
				</select>
			</li>		
			<li><input type="text" name='keyword' value="${page.keyword}" class='w-px300' /></li>
			<li><a class='btn-fill' onclick='$("form").submit()'>검색</a></li>
		</ul>
		<!-- 보여질 목록 개수 지정 -->
			<ul>
<%-- 				<li>
					<select name='pageList' class='w-px90' onchange="$('form').submit()" >
						<option value='10' ${page.pageList eq 10 ? 'selected' : '' }>10개씩</option>
						<option value='15' ${page.pageList eq 15 ? 'selected' : '' } >15개씩</option>
						<option value='20' ${page.pageList eq 20 ? 'selected' : '' } >20개씩</option>
						<option value='25' ${page.pageList eq 25 ? 'selected' : '' } >25개씩</option>
						<option value='30' ${page.pageList eq 30 ? 'selected' : '' } >30개씩</option>
					</select>
				</li> --%>
				<c:if test="${ not empty loginInfo }">
				<li><a class='btn-fill' href='new.qna'>질문하기</a></li>
				</c:if>
			</ul>	
	</div>
</div>
</form>
<div id='list'>
	<!-- 목록 형태 -->
	<c:if test="${page.viewType eq 'list' }">
	<table>
		<thead>
			<tr>
				<th class='w-px70'>글번호</th>
				<th>제목</th>
				<th class='w-px100'>작성일자</th>
				<th class='w-px100'>조회수</th>
				<th class='w-px80'>작성자</th>
				<th class='w-px80'>답변여부</th>
			</tr>
		</thead>
		<tbody>
			<!-- 조회된 목록이 없을 경우 정보 표시 -->
			<c:if test="${ empty page.list }">
				<tr>
					<td colspan="6">게시글 정보가 없습니다.</td>
				</tr>
			</c:if>
		
			<c:forEach items="${page.list }" var="vo">
				<tr>
					<td>${vo.no }</td>
					<td class='left'>
						<a onclick='go_detail(${vo.id})'>${vo.title }</a>
					</td>
					<td>${vo.writedate }</td>
					<td>${vo.readcnt }</td>
					<td>${vo.name }</td>
					<c:if test="${vo.reply_done eq '0' }">
						<td>미답변</td>
					</c:if>
					<c:if test="${vo.reply_done eq '1' }">
						<td>답변</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>

<script type="text/javascript">
	function go_detail(id) {
		$('[name = id]').val(id);
		$('form').attr('action', 'detail.qna');
		$('form').submit();
	}
</script>

</body>
</html>











