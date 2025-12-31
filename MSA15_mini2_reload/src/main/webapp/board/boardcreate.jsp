	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	<%@ include file="/layout/common.jsp" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<link rel="stylesheet" href="common.css">
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <title>게시글 작성</title>
	    <link rel="stylesheet" href="${root}/layout/common.css">
	    <link rel="stylesheet" href="${root}/board/css/boardcreate.css">
	</head>
	<body>
	<header>
	 	<jsp:include page="/layout/header.jsp" />
	</header>
	<section class="write-wrap">
	    <h1 class="title">게시글 작성</h1>
	
	    <form action="${root}/board/create" method="post">
	        <div class="form-row">
	            <label>매장 선택</label>
	  <select name="designerNo" id="designerNo" required>
	    <option value="">선택하세요</option>
	    <c:forEach var="shop" items="${shopList}">
	        <option value="${shop.designerNo}">${shop.shopName}</option>
	    </c:forEach>
	</select>
	
	
	        </div>
	
	        <div class="form-row">
	            <label>제목</label>
	            <input type="text" name="title" placeholder="제목을 입력하세요" required>
	        </div>
	
	        <div class="form-row">
	            <label>내용</label>
	            <textarea name="content" placeholder="내용을 입력하세요" required></textarea>
	        </div>
	
	        <div class="btn-area">
	            <button type="submit" class="btn btn-write">등록하기</button>
	            <button type="button" class="btn btn-cancel" 
	                    onclick="location.href='${root}/board/list'">
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
