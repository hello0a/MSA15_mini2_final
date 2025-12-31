package board.Servlet;

import java.io.IOException;

import board.DAO.commentdao;
import board.DTO.commentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/comment/write")
public class CommentCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2️⃣ 파라미터 받기
        String boardNoStr = request.getParameter("boardNo");
        String content = request.getParameter("content");

        // 유효성 체크
        if (boardNoStr == null || content == null || content.trim().isEmpty()) {
            response.sendRedirect(
                request.getContextPath() + "/board/list"
            );
            return;
        }

        int boardNo = Integer.parseInt(boardNoStr);

        // 3️⃣ DTO 생성
        commentDTO dto = new commentDTO();
        dto.setBoardNo(boardNo);
        dto.setDesignerNo(1); // 🔥 로그인한 디자이너 번호
        dto.setContent(content);



        // 4️⃣ DAO 호출
        commentdao dao = new commentdao();
        dao.insert(dto);

        // 5️⃣ 다시 게시글 상세로 이동
        response.sendRedirect(
            request.getContextPath() + "/board/read?no=" + boardNo
        );
    }
}
