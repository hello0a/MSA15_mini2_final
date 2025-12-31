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
	<!-- css 코드 불러오기 -->
	<link rel="stylesheet" href="${root}/layout/common.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/side-left.css">
	<link rel="stylesheet" href="${root}/mypage_designer/css/profile.css">
	<title>마이페이지_디자이너용_회원정보</title>
</head>

<body>
	<header>
		<jsp:include page="/layout/header.jsp" />
	</header>
	<main class=designer-profile-main>
		<div class="inner">
			<section class="left">
				<jsp:include page="/mypage_designer/side-left.jsp" />
			</section>
				<form id="designer-profile-edit" action="${root}/designer/profile_edit" method="post">
			<section class="right">
				<div class="designer-profile">
					<img src="${root}/mypage_designer/img/dug.jpg" alt="디자이너 프로필 사진">
					<p>${designer.shop_name }</p>
				</div>
				<div class="designer-area">
					<div class="designer-info">
						<%-- <input type="hidden" name="id" value="${designer.id}" > --%>
						<div class="label-wrap">
							<label class="id">아이디</label> 
							<label class="pw">비밀번호</label> 
							<label class="name">이름</label> 
							<label class="birth">생년월일</label> 
							<label class="email">이메일</label> 
							<label class="phone">전화번호</label> 
							<label class="shopname">매장명</label> 
							<label class="gender">성별</label>
							<label class="nationaliy">국적</label>
							<label class="biznum">사업자번호</label>
							<label class="location">위치</label>
						</div>
	
						<div class="p-wrap">
							<p class="input-fix"><input type="text" id="id" name="id" value="${designer.id}" readonly /></p>
							<p class="input-fix"><input type="text" value="비밀번호" readonly /></p>
							<input type="hidden" name="password" value="${designer.password}" />
							<p><input id="name" name="name" value="${designer.full_name}" ></p>
							<p><input id="birth" name="birth" value="${designer.birth}" ></p>
							<p><input id="email" name="email" value="${designer.email}" ></p>
							<p><input id="phone" name="phone" value="${designer.phonenumber}" ></p>
							<p><input id="shopName" name="shopName" value="${designer.shop_name}" ></p>
							<p><input id="gender" name="gender" value="${designer.gender}" ></p>
							<p><input id="nationaliy" name="nation" value="${designer.nationality}" ></p>
							<p><input id="bizNum" name="bizNum" value="${designer.biz_num}" ></p>
							<p><input id="city" name="city" value="${designer.city}" ></p>
							<p><input id="district" name="district" value="${designer.district}" ></p>
							<p><input id="addrDetail" name="addrDetail" value="${designer.addr_detail}" ></p>
						</div>
					</div>
				<a class="designer-profile-edit" href="${root}/designer/profile?id=${designer.id}">수정 완료</a>
				</div>
			</section>
		</form>
		</div>
	</main>
	
	<script>
		function checkForm() {
			let form = document.getElementById("designer-profile-edit");
			
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