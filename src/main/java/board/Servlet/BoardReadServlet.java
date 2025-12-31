package board.Servlet;

import java.io.IOException;

import board.DAO.boarddao;
import board.DAO.commentdao;
import board.DTO.boardDTO;
import board.DTO.commentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/read")
public class BoardReadServlet extends HttpServlet {

    private boarddao boardDao = new boarddao();
    private commentdao commentDao = new commentdao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String noStr = request.getParameter("no");
        if (noStr == null || noStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/board/list");
            return;
        }

        int no = Integer.parseInt(noStr);

        // 📌 게시글 조회
        boardDTO board = boardDao.findByNo(no);
        if (board == null) {
            response.sendRedirect(request.getContextPath() + "/board/list");
            return;
        }

        // 📌 댓글 조회 (🔥 이게 핵심)
        commentDTO comment = commentDao.findByBoardNo(no);


        // 📌 JSP로 전달
        request.setAttribute("board", board);
        request.setAttribute("comment", comment);

        request.getRequestDispatcher("/board/boardread.jsp")
               .forward(request, response);
    }
}
