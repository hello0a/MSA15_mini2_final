<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="reserve.DTO.ReservationDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 완료</title>
    <link rel="stylesheet" href="${root}/reserve/css/reservefinish.css">
    <link rel="stylesheet" href="${root}/layout/common.css">
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="reserve-complete">
    <h2>예약이 완료되었습니다!</h2>

	<c:forEach var="info" items="${reserveFinishInfo}">
    <div class="reserve-details">
        <p>시술: ${info.getStyleName()} / 금액: <fmt:formatNumber value="${info.getPrice()}" pattern="#,###" /></p>
        <p>날짜/시간: ${ info.getReserveDate() } ${ info.getReserveTime()  }</p>
        <p>전화번호: ${info.getPhoneNumber() }</p>
    </div>
	</c:forEach>
	
    <div class="actions">
        <a href="${root}/">
            <button>홈으로 이동</button>
        </a>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />

<%
    // 한 번만 보여주고 세션 제거
//    session.removeAttribute("reserveFinishInfo");
%>

</body>
</html>
