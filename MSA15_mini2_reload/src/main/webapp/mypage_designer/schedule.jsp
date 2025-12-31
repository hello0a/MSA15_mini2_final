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
	<link rel="stylesheet" href="${root}/mypage_designer/css/schedule.css">
	<title>예약 스케줄</title>
</head>

<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class="schedule-main">
		<div class="inner">
			<section class="left">
				<jsp:include page="/mypage_designer/side-left.jsp" />
			</section>
			<section class="right">
				<div class="page-button">
				<div class="button-list">
				    <button class="button-btn">버튼1</button>
				    <button class="button-btn">버튼2</button>
				  </div>
				</div>
				<!-- calendar body -->
				<div class="calendar-body">
				  <!-- 요일 줄 -->
				  <div class="calendar-row weekday-row">
				    <div class="weekday">Mon</div>
				    <div class="weekday">Tue</div>
				    <div class="weekday">Wed</div>
				    <div class="weekday">Thu</div>
				    <div class="weekday">Fri</div>
				    <div class="weekday">Sat</div>
				    <div class="weekday">Sun</div>
				  </div>
				
				  <!-- 1주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell is-muted"><div class="value">25</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">26</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">27</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">28</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">29</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">1</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">2</div><div class="reservations"></div></div>
				  </div>
				
				  <!-- 2주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell"><div class="value">3</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">4</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">5</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">6</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">7</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">8</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">9</div><div class="reservations"></div></div>
				  </div>
				
				  <!-- 3주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell"><div class="value">10</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">11</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">12</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">13</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">14</div><div class="reservations"></div></div>
				    <div class="picker-cell is-outline-primary"><div class="value">15</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">16</div><div class="reservations"></div></div>
				  </div>
				
				  <!-- 4주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell"><div class="value">17</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">18</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">19</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">20</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">21</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">22</div><div class="reservations"><div class="reservation-item">11:00 박서연</div></div></div>
				    <div class="picker-cell is-selected"><div class="value">23</div><div class="reservations"></div></div>
				  </div>
				
				  <!-- 5주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell"><div class="value">24</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">25</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">26</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">27</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">28</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">29</div><div class="reservations"></div></div>
				    <div class="picker-cell"><div class="value">30</div><div class="reservations"></div></div>
				  </div>
				
				  <!-- 6주 -->
				  <div class="calendar-row week-row">
				    <div class="picker-cell"><div class="value">31</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">1</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">2</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">3</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">4</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">5</div><div class="reservations"></div></div>
				    <div class="picker-cell is-muted"><div class="value">6</div><div class="reservations"></div></div>
				  </div>
				</div>
			</section>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
	
	<script>
	const reservationsByDayNumber = {
	  2:  [{ time: "11:00", name: "박서연" }],
	  4:  [{ time: "10:30", name: "이준호" }, { time: "15:00", name: "김민지" }],
	  6:  [{ time: "09:30", name: "정다은" }],
	  9:  [{ time: "13:00", name: "최현우" }],
	  12: [{ time: "10:00", name: "김민지" }, { time: "14:30", name: "이준호" }],
	  15: [{ time: "11:00", name: "홍길동" }],
	  18: [{ time: "16:00", name: "한지민" }],
	  23: [{ time: "19:00", name: "윤서준" }]
	};
	
	function getOrCreateBox(cell) {
	  let box = cell.querySelector(".reservations");
	  if (!box) {
	    box = document.createElement("div");
	    box.className = "reservations";
	    cell.appendChild(box);
	  }
	  return box;
	}
	
	function renderReservations() {
	  const cells = document.querySelectorAll(".picker-cell");
	
	  cells.forEach(cell => {
	    const valueEl = cell.querySelector(".value");
	    if (!valueEl) return;
	
	    const dayNum = parseInt(valueEl.textContent.trim(), 10);
	    if (!Number.isFinite(dayNum)) return;
	
	    const list = reservationsByDayNumber[dayNum] || [];
	    const box = getOrCreateBox(cell);
	    box.innerHTML = "";
	
	    list.sort((a, b) => (a.time || "").localeCompare(b.time || ""));
	
	    const MAX_SHOW = 3;
	    list.slice(0, MAX_SHOW).forEach(r => {
	      const item = document.createElement("div");
	      item.className = "reservation-item";
	      item.textContent = `${r.time} ${r.name}`;
	      box.appendChild(item);
	    });
	
	    if (list.length > MAX_SHOW) {
	      const more = document.createElement("div");
	      more.className = "reservation-item";
	      more.style.opacity = "0.7";
	      more.style.fontWeight = "700";
	      more.textContent = `+${list.length - MAX_SHOW}건 더 있음`;
	      box.appendChild(more);
	    }
	  });
	}
	
	if (document.readyState === "loading") {
	  document.addEventListener("DOMContentLoaded", renderReservations);
	} else {
	  renderReservations();
	}
	</script>
</body>
</html>