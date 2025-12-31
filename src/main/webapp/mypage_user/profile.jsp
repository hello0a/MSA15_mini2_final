<%@page import="reservation.dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/layout/common.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- css 코드 불러오기 -->
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_user/css/profile.css">
	<title>마이페이지_회원용_회원정보</title>
</head>

<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class="user-profile-main">
			<section class="left">
				<jsp:include page="/mypage_user/mypage_menu.jsp" />
			</section>
			<section class="right">
				<div class="info">
					<div class="label-wrap">
						<label class="id">아이디</label> 
						<label class="pw">비밀번호</label> 
						<label class="name">이름</label> 
						<label class="birth">생년월일</label> 
						<label class="email">이메일</label> 
						<label class="phone">전화번호</label> 
						<label class="sex">성별</label>
					</div>

					
					<div class="p-wrap">
						<p>${user.id }</p>
						<p><input id="password" name="password" value="비밀번호" /></p>
						<p><input id="name" name="name" value="${user.full_name }" /></p>
						<p><input id="birth" name="birth" value="${user.birth }" /></p>
						<p><input id="email" name="email" value="${user.email }" /></p>
						<p><input id="phonenumber" name="phonenumber" value="${user.phonenumber }" /></p>
						<p><input id="gender" name="gender" value="${user.gender }" /></p>
					</div>

<%-- 						<p>${user.id }</p>
						<p>${user.password }</p>
						<p>${user.full_name }</p>
						<p>${user.birth }</p>
						<p>${user.email }</p>
						<p>${user.phonenumber }</p>
						<p>${user.gender }</p>
 --%><!-- 						<div class="button-wrap">
							<button>남</button>
							<button>여</button>
						</div>
 -->
				</div>
				<a class="edit" href="${root}/user/mypage/profile_edit?id=${user.id}">회원정보 수정</a>
			</section>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>