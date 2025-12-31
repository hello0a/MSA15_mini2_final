<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MSA15_mini2_reload/layout/common.css">
<link rel="stylesheet" href="/MSA15_mini2_reload/mypage_designer/css/side-left.css">
<link rel="stylesheet" href="/MSA15_mini2_reload/mypage_designer/css/reserve.css">
<title>예약 리스트</title>
</head>
<body>

<header>
	<jsp:include page="/layout/header.jsp" />
</header>

<!-- ✅ DB 연결 -->
<sql:setDataSource var="db"
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul"
	user="hairshop"
	password="123456" />

<!-- ✅ 예약 목록 조회 -->
<sql:query var="reserveList" dataSource="${db}">
	SELECT
		r.no            AS reserve_no,
		s.name          AS style_name,
		r.date          AS reserve_date,
		r.time          AS reserve_time,
		u.full_name     AS user_name,
		r.price         AS price
	FROM reserved r
	JOIN users u   ON r.user_no = u.no
	JOIN style s   ON r.style_no = s.no
	ORDER BY r.date DESC, r.time DESC
</sql:query>

<main class="reserve-list">
	<div class="inner">
		<section class="left">
			<jsp:include page="/mypage_designer/side-left.jsp" />
		</section>

		<section class="right">
			<h1>예약 관리</h1>

			<div class="table-row-base">

				<!-- 헤더 -->
				<div class="table-row table-row--head">
					<div class="table-cell col-id">예약 ID</div>
					<div class="table-cell col-type">시술 종류</div>
					<div class="table-cell col-date">예약 날짜</div>
					<div class="table-cell col-time">예약 시간</div>
					<div class="table-cell col-name">예약자</div>
					<div class="table-cell col-pay">기본 금액</div>
				</div>

				<!-- ✅ DB 데이터 출력 -->
				<c:forEach var="r" items="${reserveList.rows}">
					<div class="table-row">
						<div class="table-cell col-id">${r.reserve_no}</div>
						<div class="table-cell col-type">${r.style_name}</div>

						<div class="table-cell col-date">
							<fmt:formatDate value="${r.reserve_date}" pattern="yyyy-MM-dd"/>
						</div>

						<div class="table-cell col-time">
							<fmt:formatDate value="${r.reserve_time}" pattern="HH:mm"/>
						</div>

						<div class="table-cell col-name">${r.user_name}</div>
						<div class="table-cell col-pay">
							<fmt:formatNumber value="${r.price}" pattern="#,###"/>원
						</div>
					</div>
				</c:forEach>

				<!-- 예약 없을 때 -->
				<c:if test="${empty reserveList.rows}">
					<div class="table-row">
						<div class="table-cell" style="text-align:center; width:100%;">
							예약 내역이 없습니다.
						</div>
					</div>
				</c:if>

			</div>
		</section>
	</div>
</main>

<footer>
	<jsp:include page="/layout/footer.jsp" />
</footer>

</body>
</html>
