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
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${root}/login/css/login.css">
	<title>로그인</title>
</head>

<body>
	<form action="${root}/designer/login" method="post">
	<div class="login-wrap">
		<div class="logo">
			<span class="logo-icon">✂️</span>
		</div>
		<div class="login-container">
			<h1>매장용 로그인</h1>
			<div class="input-box">
				<label for="id">아이디</label> 
				<input type="text" id="id" name="id" placeholder="아이디 입력" value="${user.id}">
			</div>

			<div class="input-box">
				<label for="pw">비밀번호</label>
				<input type="password" id="pw" name="pw" placeholder="비밀번호 입력" value="${user.pw}">
			</div>

			<div class="save-id">
				<input type="checkbox" id="saveId"> 
				<label for="saveId">아이디 저장</label>
			</div>

			<button type="submit" class="login-btn">로그인</button>
			<div class="login-links">
				<a href="${root}/designer/id_find">아이디 찾기</a> | <a href="${root}/designer/signup">회원가입</a>
			</div>
		</div>
	</div>
	</form>
</body>

</html>