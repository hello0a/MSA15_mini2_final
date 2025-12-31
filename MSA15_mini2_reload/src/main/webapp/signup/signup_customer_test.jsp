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
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<link rel="stylesheet" href="${root}/layout/common.css">
		<link rel="stylesheet" href="${root}/signup/css/signup_customer.css">
		<title>회원가입</title>
	</head>

	<body>
		<jsp:include page="/layout/header.jsp" />
		<main class="signup-main">
			<div class="wrapper">
				<div class="title">회원가입</div>

				<form id="signupForm" action="${root}/user/signup" method="post" onsubmit="return checkForm()">
					<div class="input-group">
						<input type="text" name = "id" maxlength="12" placeholder="아이디 (영문+숫자 포함, 4~12자)" required>
					</div>

					<div class="input-group">
						<input type="password" name="pw" minlength="8" placeholder="비밀번호 (영문+숫자+특수문자 포함, 8자 이상)" required>
					</div>

					<div class="input-group">
						<input type="email" name="email" placeholder="이메일 주소(ex: example@gmail.com)">
					</div>

					<div class="input-group">
						<input type="text" name="name" placeholder="이름 (한글 포함, 2~5자)" required>
					</div>

					<div class="input-group">
						<input type="tel" name="phone" placeholder="휴대전화번호">
					</div>

					<div class="birth-group">
						<input type="text" name="birthYear" placeholder="년(4자)" maxlength="4"> 
						<input type="text" name="birthMonth" placeholder="월">
						<input type="text" name="birthDay" placeholder="일">
					</div>

					<div class="select-group">
						<label> <input type="radio" name="gender" value="M">
							<div class="radio-box">남자</div>
						</label> <label> <input type="radio" name="gender" value="F">
							<div class="radio-box">여자</div>
						</label>
						</div> 
					<div class="select-group">
						<label style="width: 50%;"> <input type="radio" name="nationality" value="local" checked>
							<div class="radio-box">내국인</div>
						</label> <label style="width: 50%;"> <input type="radio" name="nationality" value="foreign">
							<div class="radio-box">외국인</div>
						</label>
					</div>

					<button type="submit" class="btn-submit">회 원 가 입</button>
				</form>
			</div>
		</main>

		<script>
			$(document).ready(function () {
				$("#signupForm").submit(function (e) {
					console.log("가입 버튼 클릭됨");
				});
			});
			
			function checkForm() {
				let form = document.getElementById("signupForm");
				
				let regExpId = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,12}$/
				let regExpName = /^[가-힣]{2,5}$/
				let regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,}$/i
				let passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
				
			    let regExpYear = /^\d{4}$/;
			    let regExpMonth = /^(0?[1-9]|1[0-2])$/;
			    let regExpDay = /^(0?[1-9]|[12][0-9]|3[01])$/;
				
				let id = form.id.value
				let pw = form.pw.value
				let email = form.email.value
				let name = form.name.value
				
				let year = form.birthYear.value
				let month = form.birthMonth.value
				let day = form.birthDay.value
				
				
				if (!regExpId.test(id)) {
					alert('아이디를 형식에 맞게 입력해주세요')
					return false
				}
				if (!passwordPattern.test(pw)) {
					alert('비밀번호를 형식에 맞게 입력해주세요')
					return false
				}
				if (!regExpName.test(name)) {
					alert('이름을 형식에 맞게 입력해주세요')
					return false
				}
				if (!regExpEmail.test(email)) {
					alert('이메일을 형식에 맞게 입력해주세요')
					return false
				}
				
				
				if (!regExpYear.test(year)) {
					alert('년도는 숫자로 4자 입력해주세요')
					return false
				}
				if (!regExpMonth.test(month)) {
					alert('월은 1~12 사이로 입력해주세요')
					return false
				}
				if (!regExpDay.test(day)) {
					alert('일은 1~31 사이로 입력해주세요')
					return false
				}
			}
		</script>
		<jsp:include page="/layout/footer.jsp"/>
	</body>

	</html>