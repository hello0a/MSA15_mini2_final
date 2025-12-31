package board.Servlet;

import java.io.IOException;
import board.DAO.boarddao;
import board.DTO.boardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

    private boarddao dao = new boarddao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        boardDTO board = dao.findByNo(no);
        request.setAttribute("board", board);
        request.setAttribute("shopList", dao.getShopList());
        request.getRequestDispatcher("/board/boardupdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int no = Integer.parseInt(request.getParameter("no"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int designerNo = Integer.parseInt(request.getParameter("designerNo"));

        boardDTO board = new boardDTO();
        board.setNo(no);
        board.setTitle(title);
        board.setContent(content);
        board.setDesignerNo(designerNo);

        dao.update(board);
        response.sendRedirect(request.getContextPath() + "/board/read?no=" + no);
    }
}
