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
			<section class="right">
				<div class="designer-profile">
					<img src="${root}/mypage_designer/img/dug.jpg" alt="디자이너 프로필 사진">
					<p>${designer.shop_name }</p>
				</div>
				<div class="designer-area">
					<div class="designer-info">
						<div class="label-wrap">
							<label class="id">아이디</label> 
							<label class="pw">비밀번호</label> 
							<label class="name">이름</label> 
							<label class="birth">생년월일</label> 
							<label class="email">이메일</label> 
							<label class="phone">전화번호</label> 
							<label class="shopname">매장명</label> 
							<label class="sex">성별</label>
							<label class="nationaliy">국적</label>
							<label class="biznum">사업자번호</label>
							<label class="location">위치</label>
						</div>
	
						<div class="p-wrap">
							<p>${designer.id }</p>
							<p>비밀번호</p>
							<p>${designer.full_name }</p>
							<p>${designer.birth }</p>
							<p>${designer.email }</p>
							<p>${designer.phonenumber }</p>
							<p>${designer.shop_name }</p>
							<p>${designer.gender }</p>
							<p>${designer.nationality }</p>
							<p>${designer.biz_num }</p>
							<div>
								<p>${designer.city} ${designer.district} ${designer.addr_detail }</p>
							</div>
						</div>
					</div>
				<a class="designer-profile-edit" href="${root}/designer/profile_edit?id=${designer.id}">회원정보 수정</a>
				</div>
			</section>

		</div>
	</main>
	<footer>
		<jsp:include page="/layout/footer.jsp" />
	</footer>
</body>