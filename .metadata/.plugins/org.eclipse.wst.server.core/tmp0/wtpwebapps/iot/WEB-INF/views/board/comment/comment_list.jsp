<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items='${list }' var='vo'>
	<div data-sequnce='${vo.id }' class='left'>${vo.name } [${vo.writedate }]
		<!--�α����� ����ڰ� �ۼ��� ����� ��� ����/���� ����  -->
		<c:if test='${vo.writer eq loginInfo.id }'>
			<span style="float: right; ">
				<a class='btn-fill-s btn-modify-save'>����</a>
				<a class='btn-fill-s btn-delete-cancel'>����</a>
			</span>
		</c:if>
	
		<div class='original'>${ fn:replace( fn:replace(vo.content, crlf, '</br>' ), lf, '</br>' )   }</div>
	</div>
	<hr>
</c:forEach>
