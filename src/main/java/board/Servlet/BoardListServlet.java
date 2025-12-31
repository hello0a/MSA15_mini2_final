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

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    private boarddao dao = new boarddao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<boardDTO> shopList = dao.getShopList();
        request.setAttribute("shopList", shopList);

        String shop = request.getParameter("storeSelect");
        List<boardDTO> boardList = dao.findAll(shop);
        request.setAttribute("boardList", boardList);

        request.getRequestDispatcher("/board/boardlist.jsp").forward(request, response);
    }
}
