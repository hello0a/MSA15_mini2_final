package reserve.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reserve.DTO.ReservationDTO;
import reserve.DTO.ReservationListDTO;
import reserve.service.ReservationService;

@WebServlet("/user/mypage/reserve")
public class ReservationListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ReservationService reservationService = new ReservationService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        List<ReservationListDTO> reservations = new ArrayList<>();        
        reservations = reservationService.selectWithDesigner(userNo);

        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/mypage_user/reserve.jsp").forward(request, response);
    }
}
