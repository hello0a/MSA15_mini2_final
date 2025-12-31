<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>보드 게시판</title>
	<link rel="stylesheet" href="${root}/layout/common.css">
    <link rel="stylesheet" href="${root}/board/css/boardlist.css">
</head>
<body>
<header>
	<jsp:include page="/layout/header.jsp" /> 
</header>
<section>

    <h1 class="title">C/S 고객문의 게시판</h1>

    <!-- 드롭다운 필터 -->
    <form id="shopForm" action="${root}/board/list" method="get">
        <label class="name" for="storeSelect">매장을 선택하세요 : </label>
        <select class="name2" id="storeSelect" name="storeSelect" onchange="document.getElementById('shopForm').submit()">
            <option value="">전체</option>
            <c:forEach var="shop" items="${shopList}">
                <option value="${shop.designerNo}" 
                    <c:if test="${param.storeSelect == shop.designerNo}">selected</c:if>>
                    ${shop.shopName}
                </option>
            </c:forEach>
        </select>
    </form>

    <!-- 선택 매장 안내 -->
    <div class="boardText">
        <span id="boardText">
            <c:choose>
                <c:when test="${not empty param.storeSelect}">
                    <c:forEach var="shop" items="${shopList}">
                        <c:if test="${shop.designerNo == param.storeSelect}">
                            ${shop.shopName} 매장 게시판입니다.
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    전체 매장 게시판입니다.
                </c:otherwise>
            </c:choose>
        </span>
    </div>

    <!-- 게시판 테이블 -->
    <div class="board-box">
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>내용</th>
                    <th>작성일자</th>
                    <th>매장명</th>
                </tr>
            </thead>
            <tbody>
<c:forEach var="board" items="${boardList}">
    <tr>
        <td>${board.no}</td>
        <td>
            <a href="${root}/board/read?no=${board.no}">
                ${board.title}
            </a>
        </td>
        <td>${board.writer}</td>
        <td>${board.content}</td>
        <td><fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd"/></td>
        <td>${board.shopName}</td>
    </tr>
</c:forEach>

                <c:if test="${empty boardList}">
                    <tr>
                        <td colspan="6">게시글이 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>

        <!-- 문의하기 버튼 -->
        <div class="btn-area">
            <button type="button" class="btn" onclick="location.href='${root}/board/create'">문의하기</button>
        </div>
    </div>
</section>
<footer>
	<jsp:include page="/layout/footer.jsp" />
</footer>
</body>
</html>
