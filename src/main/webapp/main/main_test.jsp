<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>메인화면</title>
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/main/css/main.css">
</head>

<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<div class=containerbox>
		<section class="sec1">
			<div class="container">
				<div>
					<h1>나 홀로 망머샵</h1>
					<br>
					<h3 class="desc">저희 ‘나 홀로 망머샵’은 예약 과정이 복잡해 불편을 겪는 고객과, 매장을 혼자
						운영하며 예약 관리에 어려움을 느끼는 1인 헤어 디자이너 분들을 돕기 위해 시작된 서비스입니다. 보다 쉽고 효율적인 예약
						관리 환경을 제공하여 고객과 디자이너 모두가 만족할 수 있는 더 나은 품질의 서비스를 만들어가고자 합니다.</h3>
				</div>
			</div>
		</section>
		<section class="sec2">
			<h2>샵 예약하기</h2>
		</section>
		<section class="sec3">
			<select class="shop" id="">
				<option value="">DB 연결 후 매장명 + 디자이너이름 + 위치(서울,강서구)</option>
			</select>
			<button class="btn">예약하기</button>
			</div>
		</section>
	</div>
<footer>
		<jsp:include page="/layout/footer.jsp" />
</footer>

</body>

</html>