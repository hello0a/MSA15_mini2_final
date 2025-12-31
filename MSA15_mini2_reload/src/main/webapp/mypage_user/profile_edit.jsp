<%@page import="reservation.dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/layout/common.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- css 코드 불러오기 -->
	
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_user/css/profile.css">
	<title>마이페이지_회원용_회원정보</title>
</head>

<body>
	<header> 
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class="user-profile-main">
			<section class="left">
				<jsp:include page="/mypage_user/mypage_menu.jsp" />
			</section>
			<section class="right">
			<form id="profile-edit" action="${root}/user/mypage/profile_edit" method="post" onsubmit="return checkForm()">
				<div class="info">
					<div class="label-wrap">
						<label class="id">아이디</label> 
						<label class="pw">비밀번호</label> 
						<label class="name">이름</label> 
						<label class="birth">생년월일</label> 
						<label class="email">이메일</label> 
						<label class="phone">전화번호</label> 
						<label class="sex">성별</label>
					</div>

				
					<div class="p-wrap">
						<p class="input-fix"><input type="text" id="id" name="id" value="${user.id}" readonly /></p>
						<p class="input-fix"><input type="text" value="비밀번호" readonly /></p>
						<input type="hidden" name="password" value="${user.password}" />
						<p class="input-color"><input  id="name" name="name" value="${user.full_name }" required/></p>
						<p class="input-color"><input  id="birth" name="birth" value="${user.birth }" required/></p>
						<p class="input-color"><input  id="email" name="email" value="${user.email }" required/></p>
						<p class="input-color"><input  id="phonenumber" name="phonenumber" value="${user.phonenumber }" required/></p>
						<p class="input-color"><input id="gender" name="gender" value="${user.gender }" required/></p>
 				</div>
				</div>
				<button class="edit" type="submit">수정 완료</button>
			</form>
			</section>
	</main>
	<script>
		function checkForm() {
			let form = document.getElementById("profile-edit");
			
			let regExpName = /^[가-힣]{2,5}$/
			let regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,}$/i
			let regExpBirth = /^\d{4}-\d{2}-\d{2}$/;
			let regExpphonenumber = /^\d{3}-\d{4}-\d{4}$/;
			let regExpGender =  /^(F|M)$/;
			
			let email = form.email.value
			let name = form.name.value
			let birth = form.birth.value
			let phonenumber = form.phonenumber.value
			let gender = form.gender.value
			
			if (!regExpName.test(name)) {
				alert('이름을 형식에 맞게 입력해주세요')
				return false
			}
			if (!regExpEmail.test(email)) {
				alert('이메일을 형식에 맞게 입력해주세요')
				return false
			}
			if (!regExpBirth.test(birth)) {
				alert('YYYY-MM-DD 형식에 맞게 입력해주세요')
				return false
			}
			if (!regExpphonenumber.test(phonenumber)) {
				alert('010-XXXX-XXXX 형식에 맞게 입력해주세요')
				return false
			}
			if (!regExpGender.test(gender)) {
				alert('F 혹은 M 만 입력해주세요')
				return false
			}
			return true
		}
		</script>
	
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	 </footer>
</body>