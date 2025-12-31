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
import reserve.DTO.ReservationListDTO;

@WebServlet("/mypage_user/reserveList")
public class ReservationListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hairshop?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "hairshop";
    private static final String DB_PASSWORD = "123456";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userNo") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int userNo = (int) session.getAttribute("userNo");

        List<ReservationListDTO> reservations = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {

                String sql = "SELECT r.no, r.date, r.time, r.phonenumber, r.price, "
                           + "d.full_name AS designer_name, s.name AS service_name "
                           + "FROM reserved r "
                           + "JOIN designer d ON r.designer_no = d.no "
                           + "JOIN style s ON r.style_no = s.no "
                           + "WHERE r.user_no = ? "
                           + "ORDER BY r.date DESC, r.time DESC";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, userNo);

                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            ReservationListDTO res = new ReservationListDTO();
                            res.setNo(rs.getInt("no"));
                            res.setDate(rs.getTimestamp("date"));
                            res.setTime(rs.getTimestamp("time"));
                            res.setPhoneNumber(rs.getString("phonenumber"));
                            res.setPrice(rs.getInt("price"));
                            res.setDesignerName(rs.getString("designer_name"));
                            res.setServiceName(rs.getString("service_name"));
                            reservations.add(res);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "예약 내역을 불러오는 중 오류가 발생했습니다.");
        }

        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/mypage_user/reserve.jsp").forward(request, response);
    }
}
