<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items="${list }" var="vo">
	<div data-seq = '${vo.id }' class='left'>${vo.name } [${vo.writedate }]
		<!-- 로그인한 사용자가 작성한 댓글인 경우 수정/삭제 가능 -->
		<c:if test="${vo.writer eq loginInfo.id }">
			<span style="float: right">
				<a class='btn-fill-s btn-modify-save'>수정</a>
				<a class='btn-fill-s btn-delete-cancel'>삭제</a>
			</span>		
		</c:if>	
		<div class='original'>${ fn:replace( fn:replace( vo.content, crlf, '<br>'), lf, '<br>' ) }</div>
	</div>
	<hr>
</c:forEach>