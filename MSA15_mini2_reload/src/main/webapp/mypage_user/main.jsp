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
	<!-- css 코드 불러오기 -->
	<!-- side.jsp 만들어서 include 하기 (왼쪽 사이드) : 마이페이지왼쪽 정렬까지 기능 붙이기 -->
	<link rel="stylesheet" href="${root}/layout/common.css">
	<title>마이페이지_회원용_메인</title>
</head>

<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	
	<main>
		<jsp:include page="/mypage_user/mypage_menu.jsp" />
	</main>
	
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>

</html>