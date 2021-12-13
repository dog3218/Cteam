<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>QnA</h3>
	<div id='list-top'>
		<form action="post_qna.my" method="post">
			<input type="hidden" name="curPage" value="1" />
			<input type="hidden" name='id' /> <!-- detail 에 보내질 id  -->
<%-- 			<div>
			<!-- 보여질 목록 개수 지정 -->
			<ul>
				<li>
				<select name='pageList' class='w-px90' onchange="$('form').submit()">
						<option value='10' ${page.pageList eq 10 ? 'selected' : '' }>10개씩</option>
						<option value='15' ${page.pageList eq 15 ? 'selected' : '' }>15개씩</option>
						<option value='20' ${page.pageList eq 20 ? 'selected' : '' }>20개씩</option>
						<option value='25' ${page.pageList eq 25 ? 'selected' : '' }>25개씩</option>
						<option value='30' ${page.pageList eq 30 ? 'selected' : '' }>30개씩</option>
				</select>
				</li>

			</ul>
			</div> --%>
		</form>
	</div>
	<div id='list'>
		<!-- 목록 형태 -->
		<c:if test="${page.viewType eq 'list' }">
			<table>
				<thead>
					<tr>
						<th class='w-px70'>번호</th>
						<th>제목</th>
						<th class='w-px100'>작성자</th>
						<th class='w-px100'>작성일자</th>
					</tr>
				</thead>
				<tbody>
					<!-- 조회된 목록이 없을 경우 정보 표시 -->
					<c:if test="${ empty page.list }">
						<tr>
							<td colspan="6">게시글 정보가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach items="${page.list}" var="vo">
						<tr>
							<td>${vo.no}</td>
							<td class='left'><a onclick='go_detail(${vo.id})'>${vo.title }</a>
							</td>
							<td>${vo.name }</td>
							<td>${vo.writedate }</td>
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
		$('form').attr('action', 'detail_qna.my');
		$('form').submit();
	}
</script>
	
</html>








