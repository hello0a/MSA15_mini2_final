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
<h3>답변</h3>

<c:if test="${comment != null}">
    <div class="reply-box">

        <!-- 답변 내용 -->
        <div class="reply-content">
            ${comment.content}
        </div>

        <!-- 답변 날짜 -->
        <div class="reply-date">
            <fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
        </div>

        <!-- 답변 아래 삭제 버튼 -->
        <form action="${pageContext.request.contextPath}/board/comment/delete"
              method="post" class="reply-delete-form">
            <input type="hidden" name="commentNo" value="${comment.no}">
            <input type="hidden" name="boardNo" value="${board.no}">
            <button type="submit" class="btn btn-delete"
                    onclick="return confirm('답변을 삭제하시겠습니까?');">
                답변 삭제
            </button>
        </form>

    </div>
</c:if>

<c:if test="${comment == null}">
    <div class="reply-empty">
        아직 등록된 답변이 없습니다.
    </div>

    <!-- 답변 추가 버튼 -->
    <button type="button" class="reply-btn" onclick="toggleReply()">
        답변 추가하기
    </button>

    <!-- 답변 작성 폼 -->
    <div id="replyForm" class="reply-form" style="display:none;">
        <form method="post"
              action="${pageContext.request.contextPath}/board/comment/write">
            <input type="hidden" name="boardNo" value="${board.no}">
            <textarea name="content" required></textarea>
            <button type="submit">답변 등록</button>
        </form>
    </div>
</c:if>
        
    </div>




<div class="btn-area">
    <button type="button" class="btn" 
            onclick="location.href='${pageContext.request.contextPath}/board/list'">
        목록
    </button>

    <div class="btn-right">
        <a href="${pageContext.request.contextPath}/board/update?no=${board.no}" 
           class="btn btn-update">수정하기</a>

        <button type="button" class="btn btn-delete" 
                onclick="confirmDelete(${board.no})">
            삭제하기
        </button>
    </div>
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