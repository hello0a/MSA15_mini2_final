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
	<link rel="stylesheet" href="${root}/mypage_user/css/review.css">
	<title>마이페이지_회원용_후기관리</title>
</head>

<body>
	<header> 
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class="review-main">
		<div class="inner">
			<section class="left">
				<jsp:include page="/mypage_user/mypage_menu.jsp" />
			</section>
			<section class="right">
				<div class="review-container">
					<a class="review-card" href="${root}/mypage_user/review-detail.jsp">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
							<h5 class="card-title">매장명</h5>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</a>
					<a class="review-card" href="${root}/mypage_user/review-detail.jsp">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
							<h5 class="card-title">매장명</h5>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</a>
					<a class="review-card" href="${root}/mypage_user/review-detail.jsp">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
							<h5 class="card-title">매장명</h5>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</a>
				</div>
			</section>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	 </footer>
</body>