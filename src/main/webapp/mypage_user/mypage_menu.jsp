<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage_menu</title>
	<link rel="stylesheet" href="${root}/layout/common.css"><%-- 
	<link rel="stylesheet" href="${root}/mypage_user/css/main.css"> --%>
	<link rel="stylesheet" href="${root}/mypage_user/css/mypage_menu.css">
</head>
<body>
	<section class="mypage-main">
		<div class="user-profile">
			<img src="${root}/mypage_user/img/profile.png" alt="회원 프로필 사진">
			<p>${user.full_name}</p>
		</div>
		
		<nav class="mypage-index">
			<a href="${root}/user/mypage/profile?id=${user.id}" class="user-info">회원 정보</a> 
			<a href="${root}/mypage_user/reserve/reserve.html" class="user-reserve">예약조회</a> 
			<a href="${root}/mypage_user/review/review.html" class="user-review">후기관리</a> 
			<a href="${root}/board/list" class="user-board">CS 문의</a>
		</nav>
	</section>
</body>
</html>