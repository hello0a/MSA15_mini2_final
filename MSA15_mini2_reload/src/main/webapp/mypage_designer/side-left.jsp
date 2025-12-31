
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/layout/common.jsp" %>
<!-- <!DOCTYPE html>
<html lang="en"> -->

<!-- <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	css 코드 불러오기
	side.jsp 만들어서 include 하기 (왼쪽 사이드) : 마이페이지왼쪽 정렬까지 기능 붙이기
	<title>마이페이지_디자이너용_사이드</title>
</head>

<body> -->
<div class="container">
	<div class="user-profile">
		<img src="${root}/mypage_designer/img/dug.jpg" alt="회원 프로필 사진">
		<p>${designer.full_name}</p>
	</div>
	
	<nav class="mypage-index">
		<p>메뉴</p>
		<a href="${root}/designer/mypage" class="list">예약 스케줄</a>
		<a href="${root}/mypage-designer/reserve.jsp" class="list">예약 관리</a>
		<a href="${root}/mypage-designer/reserve_detail.jsp" class="list">상세보기</a>
		<a href="${root}/mypage_designer/reserve.jsp" class="list">고객 후기 관리</a>
<!-- 		<a href="/designer/reserve" class="list">예약 관리</a>
		<a href="/designer/reserve/detail" class="list">상세보기</a>
		<a href="/designer/review" class="list">고객 후기 관리</a> -->
		<a href="${root}/designer/profile" class="list">회원정보 수정</a>
	</nav>
</div>
<!-- </body>

</html> -->