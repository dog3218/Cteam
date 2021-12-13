<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items="${list }" var="comment_vo">
	<div data-seq = '${comment_vo.id }' class='left' id="${comment_vo.id }">${comment_vo.name } [${comment_vo.writedate }]
		<!-- 로그인한 사용자가 작성한 댓글인 경우 수정/삭제 가능 -->
		<c:if test="${comment_vo.writer eq loginInfo.email }">
			<span style="float: right">
				<a class='btn-fill-s btn-modify-save' onclick="if (confirm ('해당 댓글을 수정하시겠습니까?')){comment_update()}">수정</a>
				<a class='btn-fill-s btn-delete-cancel' onclick="if (confirm ('정말 삭제하시겠습니까?') ){ comment_delete()}">삭제</a>
			</span>		
		</c:if>	
		<div class='original'>${ fn:replace( fn:replace( comment_vo.content, crlf, '<br>'), lf, '<br>' ) }</div>
	</div>
	<hr>
</c:forEach>
