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
    <title>회원 예약 내역</title>
    <link rel="stylesheet" href="${root}/mypage_user/css/reserve.css">
    <link rel="stylesheet" href="${root}/layout/common.css">
</head>
<body>
<header>
    <jsp:include page="/layout/header.jsp" />
</header>
<main class="reserve-main">
    <div class="inner">
        <section class="left">
            <jsp:include page="/mypage_user/mypage_menu.jsp" />
        </section>
        <section class="right">
            <h2>예약 내역</h2>
            <c:if test="${not empty errorMsg}">
                <p style="color:red">${errorMsg}</p>
            </c:if>
            <c:if test="${empty reservations}">
                <p>예약 내역이 없습니다.</p>
            </c:if>

            <c:forEach var="res" items="${reservations}">
                <div class="reserve">
                    <div class="designer-profile">
	                    <div class="user-profile">
							<img src="/mypage_user/img/1 (3).jpg" alt="회원 프로필 사진">			
						</div>
                        <%-- <p class="designer-name">${res.designerName}</p> --%>
                        <p class="designer-name">${res.designerName}</p>
                    </div>
                    <div class="reserve-detail">
                        <p><span>날짜:</span> <span><fmt:formatDate value="${res.date}" pattern="yyyy-MM-dd" /></span></p>
                        <p><span>시간:</span> <span>${res.time}</span></p>
                        <p><span>시술:</span> <span>${res.serviceName}</span></p>
                        <p><span>전화번호:</span> <span>${res.phoneNumber}</span></p>
                        <p><span>가격:</span> <span><fmt:formatNumber value="${res.price}" pattern="#,###" />원</span></p>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>
</main>
<footer>
    <jsp:include page="/layout/footer.jsp" />
</footer>
</body>
</html>
