package board.Servlet;

import java.io.IOException;
import board.DAO.commentdao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int commentNo = Integer.parseInt(request.getParameter("commentNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        commentdao dao = new commentdao();
        dao.delete(commentNo);

        response.sendRedirect(
            request.getContextPath() + "/board/read?no=" + boardNo
        );
    }
}
