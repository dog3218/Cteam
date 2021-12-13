<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- tiles 라이브러리를 사용할 수 있도록 선언 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${category eq 'info' }"><c:set var="title" value="공연정보"/> </c:when>
	<c:when test="${category eq 'co' }"><c:set var="title" value="커뮤니티"/> </c:when>
	<c:when test="${category eq 'no' }"><c:set var="title" value="공지사항"/> </c:when>
	<c:when test="${category eq 'qna' }"><c:set var="title" value="QnA"/> </c:when>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title } </title>
<style type="text/css">
	#wrap {display: flex; flex-direction: column; height: 100%; }
	/* flex 방향 지정 - flex-direction : column (가로형태) */
</style>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- bootstrap theme (sidebar) -->
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<link rel="stylesheet" type="text/css" href="css/common.css?v=<%=new java.util.Date().getTime() %>" />


<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- font-awesome -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<!-- bootstrap -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src='js/common.js'></script>   
</head>
<body>
	    <!-- #wrapper -->
        <div class="d-flex" id="wrapper">
                <tiles:insertAttribute name="header"/>  
                <!-- Page content-->
                <div class="container-fluid">
                    <tiles:insertAttribute name="content"/>
                </div>
            </div><!-- end Page content wrapper-->

        <!-- end #wrapper -->

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script type="text/javascript" src='js/scripts.js'></script>  
</body>
</html>