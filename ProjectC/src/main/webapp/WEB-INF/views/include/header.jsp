<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light"><a href=" <c:url value='/' /> "> <img src="imgs/exhibition_logo.png" /></a></div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3 
                    ${category eq 'info' ? 'active' : ''}" href="list.info">공연정보</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3
                    ${category eq 'co' ? 'active' : ''}" href="list.co">커뮤니티</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3
                    ${category eq 'no' ? 'active' : ''}" href="list.no">공지사항</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3
                    ${category eq 'qna' ? 'active' : ''}" href="list.qna">QnA</a>
                    <!-- 로그인 한 경우 -->
                    <c:if test="${loginInfo.type eq 'user' }">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3
                    ${category eq 'my' ? 'active' : ''}" href="list.my">마이페이지</a>
                    </c:if>
                    <c:if test="${loginInfo.type eq 'admin'}">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3
                    ${category eq 'admin' ? 'active' : ''}" href="mypage.admin">마이페이지</a>
                    </c:if>
 <!--                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
                   <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a> -->
                </div>
            </div> <!-- end Sidebar -->

            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">탭 닫기/열기</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                            	<!-- 로그인을 하지 않은 경우 -->
                            	<c:if test="${empty loginInfo }">
                               		<li class="nav-item active"><a class="nav-link" href="login">로그인</a></li>
                                	<li class="nav-item"><a class="nav-link" href="member_join">회원가입</a></li>
                               </c:if>
                               <!-- 로그인을 한 경우 -->
                            	<c:if test="${!empty loginInfo }">
                               		<li class="nav-item active">${loginInfo.name }	님</li>
                                	<li class="nav-item"><a class="nav-link" href="logout">로그아웃</a></li>
                               </c:if>
                            </ul>
                        </div>
                    </div>
                </nav>