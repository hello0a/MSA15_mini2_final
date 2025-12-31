package board.Servlet;

import java.io.IOException;
import java.util.List;
import board.DAO.boarddao;
import board.DTO.boardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/create")
public class BoardCreateServlet extends HttpServlet {
    private boarddao dao = new boarddao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<boardDTO> shopList = dao.getShopList();
        request.setAttribute("shopList", shopList);
        request.getRequestDispatcher("/board/boardcreate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int designerNo = Integer.parseInt(request.getParameter("designerNo"));

        boardDTO board = new boardDTO();
        board.setTitle(title);
        board.setContent(content);
        board.setDesignerNo(designerNo);

        if (dao.insert(board)) {
            response.sendRedirect(request.getContextPath() + "/board/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/board/create?error=true");
        }
    }
}
