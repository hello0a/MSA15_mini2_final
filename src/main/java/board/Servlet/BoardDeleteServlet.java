package board.Servlet;

import java.io.IOException;
import board.DAO.boarddao;
import board.DTO.boardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reservation.dto.UserDTO;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

    private boarddao dao = new boarddao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	member.dto.UserDTO loginUser = (member.dto.UserDTO) request.getSession().getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        int no = Integer.parseInt(request.getParameter("no"));
        boardDTO board = dao.findByNo(no);

        // 작성자일 경우만 삭제 가능
        if (loginUser.getNo() == board.getUserNo()) {
            dao.delete(no);
        }

        response.sendRedirect(request.getContextPath() + "/board/list");
    }
}
