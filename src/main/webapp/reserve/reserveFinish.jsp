<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="reserve.DTO.ReservationDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>예약 완료</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/reservefinish.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/layout/common.css">
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<%
    // 세션에서 꺼내오기
    ReservationDTO dto = (ReservationDTO) session.getAttribute("reserveFinishInfo");
    if (dto == null) {
        // 세션에 없으면 홈으로
//         response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>

<div class="reserve-complete">
    <h2>예약이 완료되었습니다!</h2>

    <div class="reserve-details">
        <p>시술: <%= dto.getServices() %></p>
        <p>날짜/시간: <%= dto.getReserveDate() %> <%= dto.getReserveTime() %></p>
        <p>전화번호: <%= dto.getPhoneNumber() %></p>
    </div>

    <div class="actions">
        <a href="<%= request.getContextPath() %>/index.jsp">
            <button>홈으로 이동</button>
        </a>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />

<%
    // 한 번만 보여주고 세션 제거
    session.removeAttribute("reserveFinishInfo");
%>

</body>
</html>
