<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/side-left.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/review.css">
	<title>매장 리뷰</title>
</head>
<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class=designer-review-main>
		<div class="inner">
			<section class="left">
				<jsp:include page="/mypage_designer/side-left.jsp" />
			</section>
			<section class="right">
				<div class="review-container">
					<div class="review-card">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
							<div class="user-reserve">
								<img src="${root}/mypage_designer/img/profile.png"
									alt="프로필 사진">
								<p>고객 이름</p>
								<p>시술 종류</p>
							</div>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</div>
					<div class="review-card">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
						<div class="user-reserve">
								<img src="${root}/mypage_designer/img/profile.png"
									alt="프로필 사진">
								<p>고객 이름</p>
								<p>시술 종류</p>
							</div>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</div>
					<div class="review-card">
						<img src="${root}/mypage_user/img/장원영.jpeg" alt="후기 사진">
						<div class="card-body">
						<div class="user-reserve">
								<img src="${root}/mypage_designer/img/profile.png"
									alt="프로필 사진">
								<p>고객 이름</p>
								<p>시술 종류</p>
							</div>
							<textarea class="card-text">고객 후기</textarea>
							<p class="date">이용 날짜</p>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>
</html>