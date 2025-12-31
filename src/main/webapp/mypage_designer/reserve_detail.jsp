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
	<link rel="stylesheet" href="${root}/mypage_designer/css/reserve_detail.css">
	<title>예약 상세 정보</title>
</head>
<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class="reserve_detail_main">
		<div class="inner">
			<section class="left">
				<jsp:include page="${root}/mypage_designer/side-left.jsp" />
			</section>
			<section class="right">
				<div class="designer-profile">
					<img src="${root}/mypage_user/img/profile.png" alt="회원 프로필 사진">
					<p>${user.full_name}</p>
				</div>
				<div class="reserve-area">
					<div class="reserve-detail">
						<div class="label-wrap">
							<label class="id">예약 날짜</label> 
							<label class="pw">예약 시간</label> 
							<label class="name">시술 종류</label> 
							<label class="birth">생년월일</label> 
							<label class="phone">전화번호</label> 
							<label class="sex">성별</label>
							<label class="email">특이사항</label> 
						</div>
						<div class="p-wrap">
							<p>예약 날짜</p>
							<p>예약 시간</p>
							<p>시술 종류</p>
							<p>생년월일</p>
							<p>전화번호</p>
							<div class="button-wrap">
								<button>남</button>
								<button>여</button>
							</div>
							<textarea name="spec" rows="5">특이사항</textarea>
						</div>
					</div>
					<a class="reserve-edit" href="${root}/designer/mypage/reserve-edit?id=${user.id}">특이사항 변경</a>
				</div>
			</section>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>
</html>