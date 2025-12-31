<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- css 코드 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/boardupdate.css?v=1.0">
	<title>게시글 수정</title>

</head>
	<header>
	<jsp:include page="/layout/header.jsp" />
	</header>
<body>
<section class="write-wrap">
	<h1 class="title">게시글 수정</h1>

	<form action="${pageContext.request.contextPath}/board/update" method="post">
		<input type="hidden" name="no" value="${board.no}" />

		<div class="form-row">
			<label>매장</label>
			<select disabled>
    <option>${board.shopName}</option>
</select>

<!-- 실제 전송용 hidden input 추가 -->
<input type="hidden" name="designerNo" value="${board.designerNo}" />
		</div>

		<div class="form-row">
			<label>제목</label>
			<input type="text" name="title" value="${board.title}" required>
		</div>

		<div class="form-row">
			<label>작성자</label>
			<input type="text" value="${board.writer}" readonly>
		</div>

		<div class="form-row">
			<label>내용</label>
			<textarea name="content" required>${board.content}</textarea>
		</div>

		<!-- 버튼 영역 -->
		<div class="btn-area">
			<button type="submit" class="btn">수정완료</button>
			<button type="button" class="btn" 
			        onclick="location.href='${pageContext.request.contextPath}/board/read?no=${board.no}'">
				취소
			</button>
		</div>
	</form>
</section>
	<footer>
	<jsp:include page="/layout/footer.jsp" />
	</footer>

</body>

</html>