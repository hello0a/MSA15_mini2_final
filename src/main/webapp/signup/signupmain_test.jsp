<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8" />
	<title>회원가입 선택</title>
	<link rel="stylesheet" href="${root}/login/css/loginmain.css">
	</style>
</head>

<body>
	<div class="container">
		<h1 class="title">회원가입</h1>

		<div class="card-wrap">
			<a href="${root}/designer/signup" class="login-card owner"> <img src="${root}/login/img/owner.jpg" alt="매장용 회원가입">
				<h2>매장용 회원가입</h2>
				<p>미용실 직원 전용</p>
			</a> <a href="${root}/user/signup" class="login-card user"> <img src="${root}/login/img/user.jpg" alt="회원용 회원가입">
				<h2>회원용 회원가입</h2>
				<p>일반 고객 전용</p>
			</a>
		</div>
	</div>
</body>

</html>