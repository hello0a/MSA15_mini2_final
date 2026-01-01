<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 페이지</title>
    <!-- CSS 유지 -->
    <link rel="stylesheet" href="${root}/reserve/css/reserve.css">
    <link rel="stylesheet" href="${root}/layout/common.css">
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
    <h2>원하시는 예약 날짜와 시간, 시술을 선택해 주세요.</h2>

    <form action="${pageContext.request.contextPath}/reserve" method="post">

        <!-- 예약 날짜 -->
        <details>
            <summary>예약 날짜</summary>
            <div class="card-content">
                <input type="date" id="reserveDate" name="reserveDate" required>
            </div>
        </details>

        <!-- 예약 시간 -->
        <details>
            <summary>예약 시간</summary>
            <div class="card-content time-list">
                <label><input type="radio" name="time" value="10:00" required><span>10:00</span></label>
                <label><input type="radio" name="time" value="12:00"><span>12:00</span></label>
                <label><input type="radio" name="time" value="14:00"><span>14:00</span></label>
                <label><input type="radio" name="time" value="16:00"><span>16:00</span></label>
                <label><input type="radio" name="time" value="18:00"><span>18:00</span></label>
                <label><input type="radio" name="time" value="20:00"><span>20:00</span></label>
                <label><input type="radio" name="time" value="22:00"><span>22:00</span></label>
            </div>
        </details>

        <!-- 시술 선택 + 가격 -->
        <details>
            <summary>시술 선택</summary>
            <div class="card-content service">
                <!-- data-price 속성에 가격 넣기 -->
                <label><input type="checkbox" name="styleNo" value="1:컷:20000" data-price="20000"> 컷 (20,000원)</label>
                <label><input type="checkbox" name="styleNo" value="2:펌:50000" data-price="50000"> 펌 (50,000원)</label>
                <label><input type="checkbox" name="styleNo" value="3:염색:40000" data-price="40000"> 염색 (40,000원)</label>
                <p>⚠️ 모발 길이·손상도에 따른 추가 요금이 있을 수 있습니다.</p>
            </div>
        </details>

        <!-- 총 가격 표시 -->
        <div class="card-content">
            <p>총 시술 가격: <span id="totalPrice">0</span>원</p>
        </div>

        <input type="hidden" name="designerNo" value="${selectedDesignerNo}">
        <input type="hidden" name="store" value="${selectedStore}">

        <button type="submit" class="reserve-btn">예약하기</button>
    </form>
</div>

<jsp:include page="/layout/footer.jsp" />

<script>
window.addEventListener("DOMContentLoaded", function() {

    const dateInput = document.getElementById("reserveDate");
    const timeRadios = document.querySelectorAll('input[name="time"]');
    const serviceCheckboxes = document.querySelectorAll('input[name="service"]');
    const totalPriceSpan = document.getElementById("totalPrice");

    // 날짜 과거 선택 방지 + 시간 disable 처리
    const now = new Date();
    const todayStr = now.toISOString().split('T')[0];
    dateInput.setAttribute("min", todayStr);

    function updateTimeDisable() {
        const selected = dateInput.value;
        const currentHour = new Date().getHours();

        timeRadios.forEach(radio => {
            const hour = parseInt(radio.value.split(":")[0], 10);
            if (selected === todayStr && hour <= currentHour) {
                radio.disabled = true;
                radio.nextElementSibling.style.color = '#ccc';
            } else {
                radio.disabled = false;
                radio.nextElementSibling.style.color = '';
            }
        });
    }
    dateInput.addEventListener("change", updateTimeDisable);
    updateTimeDisable();

    // 시술 가격 계산 (checkbox 값들 읽어서 합산)
    function updateTotalPrice() {
        let total = 0;
        serviceCheckboxes.forEach(chk => {
            if (chk.checked) {
                total += parseInt(chk.dataset.price);
            }
        });
        // 숫자를 세자리마다 , 표시 (옵션)
        totalPriceSpan.textContent = total.toLocaleString();
    }

    serviceCheckboxes.forEach(chk => {
        chk.addEventListener("change", updateTotalPrice);
    });

});
</script>

</body>
</html>
