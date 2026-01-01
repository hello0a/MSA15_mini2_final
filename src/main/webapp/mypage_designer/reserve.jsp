<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/side-left.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/reserve.css">
	<title>예약 리스트</title>
</head>
<body>

<header>
	<jsp:include page="/layout/header.jsp" />
</header>

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
					<div class="table-cell col-pay"> </div>
				</div>

				<!-- ✅ DB 데이터 출력 -->
				<c:forEach var="r" items="${reservations}">
					<div class="table-row">
						<div class="table-cell col-id">${r.no}</div>
						<div class="table-cell col-type">${r.serviceName}</div>
						<div class="table-cell col-date">
							<fmt:formatDate value="${r.date}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="table-cell col-time">
							${r.time}
						</div>

						<div class="table-cell col-name">${r.userName}</div>
						<div class="table-cell col-pay">
							<fmt:formatNumber value="${r.price}" pattern="#,###"/>원
						</div>
						<div class="table-cell col-name"><a href="/designer/reserve_detail?no=${r.no }">더보기</a></div>
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
