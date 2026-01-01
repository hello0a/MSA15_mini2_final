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
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<form id="designer-profile-edit" action="${root}/designer/reserve_detail" method="post">
			<section class="right">
				<div class="user-profile">
					<img src="${root}/mypage_user/img/profile.png" alt="회원 프로필 사진">
					<p>${reservations.userName}</p>
				</div>
				<div class="reserve-area">
				<input type="hidden" id="no" name="no" value="${reserveNo}" >
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
							<p>${reservations.date }</p>
							<p>${reservations.time }</p>
							<p>${reservations.serviceName }</p>
							<p>${reservations.birth }</p>
							<p>${reservations.phoneNumber }</p>
							<div class="button-wrap">
								<button type="button" class="${reservations.gender == 'M' ? 'active' : ''}">남</button>
								<button type="button" class="${reservations.gender == 'F' ? 'active' : ''}">여</button>
							</div>
							<textarea id="etc" name="etc" rows="5">${reservations.etc }</textarea>
						</div>
					</div>
					<button type="submit" class="reserve-edit">특이사항 변경</button>
				</div>
			</section>
			</form>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>
</html>