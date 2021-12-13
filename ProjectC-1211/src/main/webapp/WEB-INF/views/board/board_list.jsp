<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{text-decoration: none; color: black;}
</style>
</head>
<body>
<h3>공연 목록</h3>
<div id='list-top'>
	<form method="post" action="list.bo">
	<input type="hidden" name="curPage" value="1" />
	<input type="hidden" name='no' /> <!-- detail 에 보내질 id  -->
		<div>
			<!-- 검색 처리 -->
			<ul>
				<li>
					<select name="search" class='w-px90'>
						<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
						<option value="eventnm" ${page.search eq 'eventnm' ? 'selected' : '' }>공연명</option>
						<option value="eventco" ${page.search eq 'eventco' ? 'selected' : '' }>장르</option>
						<option value="writer" ${page.search eq 'writer' ? 'selected' : '' }>작성자</option>
					</select>
				</li>
				<li><input type="text" name="keyword" value="${page.keyword}" class='w-px300' /> </li>
				<li><a class='btn-fill' onclick='$("form").submit()'>검색</a></li>
			</ul>
			<!-- 보여질 목록 개수 지정 -->
			<ul>
				<li>
					<select name='pageList' class='w-px90' onchange="$('form').submit()" >
						<option value='10' ${page.pageList eq 10 ? 'selected' : '' }>10개씩</option>
						<option value='15' ${page.pageList eq 15 ? 'selected' : '' } >15개씩</option>
						<option value='20' ${page.pageList eq 20 ? 'selected' : '' } >20개씩</option>
						<option value='25' ${page.pageList eq 25 ? 'selected' : '' } >25개씩</option>
						<option value='30' ${page.pageList eq 30 ? 'selected' : '' } >30개씩</option>
					</select>
				</li>
				<c:if test="${loginInfo.type eq 'producer' or loginInfo.type eq 'admin' }">
					<li><a class='btn-fill' href='new.bo'>글쓰기</a></li>
				</c:if>
			</ul>	
		</div>
	</form>
</div>
<div id='list'>
	<!-- 그리드 형태 -->
	<c:if test="${page.viewType eq 'grid' }">
		<ul class='grid'>
		<!-- 조회된 목록이 없을 경우 정보 표시 -->
			<c:if test="${ empty page.list }">
				<div> 등록된 정보가 없습니다.</div>
			</c:if>
			<c:forEach items="${page.list }" var = "vo">
				<li>
					<div><img src="${vo.filepath }"></div>
					<div><a href='detail.bo?no=${vo.no}'>${vo.eventnm }</a></div>
					<div>${vo.eventco }</div>
					<div>${vo.eventstartdate } [ ${vo.readcnt } ]
						<span style="float:right">
						${ empty vo.filepath ? '' : "<img src='imgs/attach.png' class='file-img' />" }
						</span>
					</div>
				</li> 
			</c:forEach>
		</ul>
	</c:if>
	
</div>
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>

<script type="text/javascript">
	function go_detail(no) {
		$('[name = no]').val(no);
		$('form').attr('action', 'detail.bo');
		$('form').submit();
	}
</script>

</body>
</html>
