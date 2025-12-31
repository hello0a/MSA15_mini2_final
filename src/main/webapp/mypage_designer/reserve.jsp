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
	<link rel="stylesheet" href="${root}/mypage_designer/css/reserve.css">
	<title>예약 리스트</title>
	<style>
	
	</style>
</head>
<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	
	<main class="reserve-list">
		<div class="inner">
			<section class="left">
				<jsp:include page="${root}/mypage_designer/side-left.jsp" />
			</section>
			<section class="right">
				<h1>예약 관리</h1>
				<div class="table-row-base">
				  <!-- 헤더(컬럼명) -->
				  <div class="table-row table-row--head">
				    <div class="table-cell col-id">예약 ID</div>
				    <div class="table-cell col-type">시술 종류</div>
				    <div class="table-cell col-date">예약 날짜</div>
				    <div class="table-cell col-name">예약자</div>
				    <div class="table-cell col-pay">기본 금액</div>
				    <div class="table-cell col-action">상세 정보</div>
				  </div>
				
				  <!-- row 1 -->
				  <div class="table-row">
				    <div class="table-cell col-id">123456</div>
				    <div class="table-cell col-type">컷</div>
				    <div class="table-cell col-date">12/02</div>
				    <div class="table-cell col-name">박서연</div>
				    <div class="table-cell col-pay">50,000</div>
				    <div class="table-cell col-action">
				      <a class="link-reserve-detail" href="#">더보기</a>
				    </div>
				  </div>
				</div>
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</section>
		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>
</html>