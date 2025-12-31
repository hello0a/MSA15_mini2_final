<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${root}/layout/common.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${root}/login/css/id_find.css">
	<title>아이디 찾기</title>
</head>

<body>
<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<div class="wrap">
	<div class="find-wrap">
		<h1 class="title">아이디 찾기</h1>

		<!-- 이름 -->
		<form action="${root}/id_find" method="post">
		<div class="input-box">
			<label>이름</label> 
			<input type="text" id="name" name="name" placeholder="이름을 입력하세요">
		</div>

		<!-- 이메일 -->
		<div class="input-box">
			<label>이메일</label> 
			<input type="email" id="email" name="email" placeholder="이메일을 입력하세요">
		</div>

		<!-- 아이디 찾기 버튼 -->
		<button type="submit" class="find-btn">아이디 찾기</button>

		<!-- 아이디 출력 -->
		<div class="result-box">
			<p>
				회원님의 아이디<br> <span>${id}</span> 입니다.
			</p>
		</div>
		</form>

		<!-- 하단 링크 -->
		<div class="bottom-links">
			<a href="${root}/login">로그인</a> |<a href="${root}/user/signup">회원가입</a>
		</div>
	</div>
	</div>
</body>
<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</html>