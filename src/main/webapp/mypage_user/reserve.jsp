<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/layout/common.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>회원 예약 내역</title>
    <link rel="stylesheet" href="${root}/mypage_user/css/reserve.css">
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
                        <p class="designer-name">${res.designerName}</p>
                    </div>
                    <div class="reserve-detail">
                        <p>날짜: <fmt:formatDate value="${res.date}" pattern="yyyy-MM-dd" /></p>
                        <p>시간: <fmt:formatDate value="${res.time}" pattern="HH:mm" /></p>
                        <p>시술: ${res.serviceName}</p>
                        <p>전화번호: ${res.phoneNumber}</p>
                        <p>가격: ${res.price}원</p>
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
