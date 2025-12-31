<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- css 코드 불러오기 -->
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_user/css/review-detail.css">
	<title>마이페이지_회원용_후기변경</title>
</head>

<body>
	<header> 
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class=review-detail-main>
		<div class="inner">
			<section class="left">
				<jsp:include page="/mypage_user/mypage_menu.jsp" />
			</section>
			<section class="right">
				<div class="container">
					<div class="img-wrap">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
					</div>
					<div class="reserve-detail">
						<img src="${root}/mypage_user/img/1 (3).jpg"
							alt="프로필 사진">
						<p>디자이너 이름</p>
						<p>예약 날짜</p>
					</div>
					<textarea>고객 후기</textarea>
				</div>
				<a class="review-edit" href="${root}/user/mypage/review-edit?id=${designer.id}">수정하기</a>
			</section>
		</div>
	</main>
	<footer> 
		<jsp:include page="/layout/footer.jsp" />
	</footer>
	
</body>