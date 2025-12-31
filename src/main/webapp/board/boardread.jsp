<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/boardread.css">
</head>
<body>

<h2>C/S 고객문의 게시판</h2>

<section class="view-wrap">
    <div class="view-box">
        <div class="view-title">제목 : ${board.title}</div>

        <div class="view-info">
            <span>작성자 : ${board.writer}</span>
            <span>작성일 : <fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd"/></span>
        </div>

        <div class="view-info">
            <span>매장 : ${board.shopName}</span>
        </div>

        <div class="view-content">
            <c:out value="${board.content}" escapeXml="false"/>
        </div>
        <hr>





<div class="btn-area">
    <button type="button" class="btn" 
            onclick="location.href='${pageContext.request.contextPath}/board/list'">
        목록
    </button>

    <c:choose>
        <c:when test="${board.userNo == sessionScope.user.no}">
            <div class="btn-right">
                <a href="${pageContext.request.contextPath}/board/update?no=${board.no}" 
                   class="btn btn-update">수정하기</a>

                <button type="button" class="btn btn-delete" 
                        onclick="confirmDelete(${board.no})">
                    삭제하기
                </button>
            </div>
        </c:when>

        <c:otherwise>
            <div class="btn-right">
                <span style="color: red; font-weight: bold;">
                    이 게시글은 수정/삭제 권한이 없습니다.
                </span>
            </div>
        </c:otherwise>
    </c:choose>
</div>


<script>
function confirmDelete(no) {
    if (confirm("정말 삭제하시겠습니까?")) {
        location.href = '${pageContext.request.contextPath}/board/delete?no=' + no;
    }
}

function toggleReply() {
    const form = document.getElementById("replyForm");
    if (!form) return;
    form.style.display = (form.style.display === "none") ? "block" : "none";
}
</script>

</section>

</body>
</html>