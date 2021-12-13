<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- tiles 라이브러리를 사용할 수 있도록 선언 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <c:choose>
   <c:when test="${category eq 'info' }"><c:set var="title" value="공연정보"/> </c:when>
   <c:when test="${category eq 'co' }"><c:set var="title" value="커뮤니티"/> </c:when>
   <c:when test="${category eq 'no' }"><c:set var="title" value="공지사항"/> </c:when>
   <c:when test="${category eq 'qna' }"><c:set var="title" value="QnA"/> </c:when>
</c:choose>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Culture Show-K</title>
<style type="text/css">
   #wrap {display: flex; flex-direction: column; height: 100%; }
   /* flex 방향 지정 - flex-direction : column (가로형태) */
</style>


<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/common.css?v=<%=new java.util.Date().getTime() %>" />
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

<!--       <script src="js/jquery/jquery-2.2.4.min.js"></script> -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- font-awesome -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<!-- bootstrap -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src='js/common.js'></script>
</head>
<body>


 <!-- 프리로더 (타일즈 최상위 레이아웃에 넣어도 되고 헤더에 넣어도 됨) -->
    <!-- Preloader -->
    <div id="preloader">
        <div class="preload-content">
            <div id="original-load"></div>
        </div>
    </div>

    <tiles:insertAttribute name="header"/>


    <!-- ##### Blog Wrapper Start ##### -->
    <div class="blog-wrapper section-padding-100 clearfix">
        <div class="container">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
    <!-- ##### Blog Wrapper End ##### -->
    <tiles:insertAttribute name="footer"/>


        <!-- jQuery (Necessary for All JavaScript Plugins) -->
        <!--3.6.0 기능 구현을 위한 코드  -->
	<script src="https://code.jquery.com/jquery-migrate-1.4.1.js"></script>
        <!-- Popper js -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap js -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Plugins js -->
        <script src="js/plugins.js"></script>
        <!-- Active js -->
        <script src="js/active.js"></script>
        <!-- Swiper JS -->
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

        <script>
        var swiper2 = new Swiper(".instaSwiper", {
            slidesPerView: 10,
            slidesPerGroup: 1,
            autoHeight: true,
            loop: true,
            loopFillGroupWithBlank: true,
            autoplay: {
              delay: 2500,
              disableOnInteraction: false,
            },

          });
        </script>


<!--         Bootstrap core JS
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        Core theme JS
        <script type="text/javascript" src='js/scripts.js'></script>  -->
</body>
</html>