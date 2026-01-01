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
import jakarta.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        member.dto.UserDTO loginUser = (member.dto.UserDTO) session.getAttribute("user");

        if (loginUser == null) {
            // 로그인 안 된 상태면 로그인 페이지로
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        int userNo = loginUser.getNo(); // 로그인한 유저 번호 가져오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int designerNo = Integer.parseInt(request.getParameter("designerNo"));

        boardDTO board = new boardDTO();
        board.setTitle(title);
        board.setContent(content);
        board.setDesignerNo(designerNo);
        board.setUserNo(userNo); // 로그인 유저 번호 설정

        if (dao.insert(board)) {
            response.sendRedirect(request.getContextPath() + "/board/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/board/create?error=true");
        }
    }
}
