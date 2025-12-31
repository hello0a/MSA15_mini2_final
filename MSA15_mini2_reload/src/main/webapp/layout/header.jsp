<%@ include file="/layout/common.jsp" %>
<%@page import="reservation.dto.DesignerDTO"%>
<%@page import="reservation.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<header class="site-header">
		<div class="header-container">
			<!-- 로고 영역: 클릭 시 홈으로 이동 (임시로 index.jsp) -->
			<a href="${root}/main" class="logo-area"> <span class="logo-icon">✂️</span>
				<span class="site-title">망머샵 미용실</span>
			</a>

			<!-- 사용자 메뉴 영역 -->
			<div class="user-menu">
				<%
					UserDTO user = (UserDTO) session.getAttribute("user");
					DesignerDTO designer = (DesignerDTO) session.getAttribute("designer");
					boolean isLogin = (user != null || designer != null);
				%>
				<!-- 로그인 링크 -->
				
				<% if (user != null) { %>
					<a href="${root}/user/mypage" class="user-mypage">
					<span class="login-icon">👤</span>
					<span class="login-text"><%= user.getFull_name() %></span></a>
				<% } else if (designer != null) { %>
					<a href="${root}/designer/mypage" class="designer-mypage">
					<span class="login-icon">👤</span>
					<span class="login-text"><%= designer.getFull_name() %></span></a>
				<% } else { %>
					<a href="${root}/login" class="logn-main">
					<span class="login-icon">👤</span>
					<span class="login-text">로그인</span></a>
				<% } %>
			
				<% if (!isLogin) { %>
					<p class="menu-divider">|</p>
				<!-- <a href="/designer/mypage" class="login-link"> <span class="login-icon">👤</span>
					<span class="login-text">너다</span> 
				</a> <span class="menu-divider">|</span> -->
				<!-- 회원가입 링크 -->
					<a href="${root}/signup" class="login-link"> <span class="signup-text">회원가입</span></a>
				<% } %>
				
				<% if (isLogin) { %>
				    <span class="menu-divider">|</span>
				    <a href="${root}/logout" class="logout">
				        <span class="logout-text">로그아웃</span>
				    </a>
				<% } %>
								
			</div>
		</div>
	</header>
