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
import member.dto.UserDTO;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

    private boarddao dao = new boarddao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("user");

        int no = Integer.parseInt(request.getParameter("no"));
        boardDTO board = dao.findByNo(no);

        if (loginUser == null || board.getUserNo() != loginUser.getNo()) {
            // 로그인 안했거나 작성자와 다르면 접근 금지
            response.sendRedirect(request.getContextPath() + "/board/list");
            return;
        }

        request.setAttribute("board", board);
        request.setAttribute("shopList", dao.getShopList());
        request.getRequestDispatcher("/board/boardupdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("user");

        int no = Integer.parseInt(request.getParameter("no"));
        boardDTO board = dao.findByNo(no);

        if (loginUser == null || board.getUserNo() != loginUser.getNo()) {
            // 로그인 안했거나 작성자와 다르면 수정 금지
            response.sendRedirect(request.getContextPath() + "/board/list");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int designerNo = Integer.parseInt(request.getParameter("designerNo"));

        board.setTitle(title);
        board.setContent(content);
        board.setDesignerNo(designerNo);

        dao.update(board);
        response.sendRedirect(request.getContextPath() + "/board/read?no=" + no);
    }
}
