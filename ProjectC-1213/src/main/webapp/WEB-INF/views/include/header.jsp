<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- 헤더시작 -->
    <!-- Subscribe Modal -->
    <div class="subscribe-newsletter-area">
        <div class="modal fade" id="subsModal" tabindex="-1" role="dialog" aria-labelledby="subsModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
<!--                     <div class="modal-body">
                        <h5 class="title">Subscribe to my newsletter</h5>
                        <form action="#" class="newsletterForm" method="post">
                            <input type="email" name="email" id="subscribesForm2" placeholder="Your e-mail here">
                            <button type="submit" class="btn original-btn">Subscribe</button>
                        </form> 
                    </div -->>
                </div>
            </div>
        </div>
    </div>

    <!-- ##### Header Area Start ##### -->
    <header class="header-area">

        <!-- Top Header Area -->
<!--         <div class="top-header">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    Breaking News Area
                    <div class="col-12 col-sm-8">
                        <div class="breaking-news-area">
                            <div id="breakingNewsTicker" class="ticker">
                                <ul>
                                    <li><a href="#">Hello World!</a></li>
                                    <li><a href="#">Hello Universe!</a></li>
                                    <li><a href="#">Hello Original!</a></li>
                                    <li><a href="#">Hello Earth!</a></li>
                                    <li><a href="#">Hello Colorlib!</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    Top Social Area
                    <div class="col-12 col-sm-4">
                        <div class="top-social-area">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Behance"><i class="fa fa-behance" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Linkedin"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->

        <!-- Logo Area -->
        <div class="logo-area text-center">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <a href=" <c:url value='/' /> " class="original-logo"><img src="photo/core-img/logo_transparent.png" alt=""></a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Nav Area -->
        <div class="original-nav-area" id="stickyNav">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Classy Menu -->
                    <nav class="classy-navbar justify-content-between">
                    
                    
                        <!-- Subscribe btn -->
<%--                         <c:if test="${empty loginInfo }">
                        <div class="signup-btn">
                            <a href="login">로그인/회원가입</a>
                        </div>
                        </c:if> --%>

                         <div>
                         	<ul>
	                        	<!-- 로그인을 하지 않은 경우 -->
	                        	<c:if test="${empty loginInfo }">
	                            <li><a href="login">로그인</a></li>
	                            <li><a href="member_join"></a></li>
	                            </c:if>
	                             <!-- 로그인을 한 경우 -->
	                             <c:if test="${!empty loginInfo }">
	                             <li><a href="mypage.admin">${loginInfo.name }님! 반갑습니다</a>&nbsp;&nbsp;&nbsp;<a href="logout" class="btn-fill">로그아웃</a></li>
	                             
	                             </c:if>
                             </ul>
                        </div> 

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu" id="originalNav">
                            <!-- close btn -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                    <li><a class="${category eq 'info' ? 'active' : ''}" href="list.bo">공연정보</a></li>
                                    <li><a class="${category eq 'co' ? 'active' : ''}" href="list.co">커뮤니티</a>
                                    </li>
                                    <li><a class="${category eq 'no' ? 'active' : ''}" href="list.no">공지사항</a>
                                    </li>
                                    <li><a class="${category eq 'qna' ? 'active' : ''}" href="list.qna">QnA</a></li>
                                     <!-- 로그인 한 경우 -->
				                    <c:if test="${loginInfo.type eq 'user' }">
				                    <li><a class="${category eq 'my' ? 'active' : ''}" href="mypage.user">마이페이지</a></li>
				                    </c:if>
				                    <!-- 관리자가 로그인 한 경우 -->
				                    <c:if test="${loginInfo.type eq 'admin'}">
				                    <li><a class="${category eq 'admin' ? 'active' : ''}" href="mypage.admin">마이페이지</a></li>
				                    </c:if>
				                    <!-- 기획자가 로그인 한 경우 -->
				                    <c:if test="${loginInfo.type eq 'producer'}">
				                    <li><a class=" ${category eq 'pro' ? 'active' : ''}" href="mypage.user">마이페이지</a></li>
				                    </c:if>
                                </ul>

                                <!-- Search Form  -->
<!--                                 <div id="search-wrapper">
                                    <form action="#">
                                        <input type="text" id="search" placeholder="Search something...">
                                        <div id="close-icon"></div>
                                        <input class="d-none" type="submit" value="">
                                    </form>
                                </div> -->
                            </div>
                            <!-- Nav End -->
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->