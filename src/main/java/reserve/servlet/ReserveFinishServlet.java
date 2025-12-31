package reserve.servlet;

import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/reserveFinish")
public class ReserveFinishServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET 요청 처리: 바로 URL 접근 시 대비
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/"); // 홈으로 이동
    }

    // POST: 예약 완료 화면
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        // 세션에서 사용자 ID 확인
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp"); // 로그인 필요
            return;
        }

        // 1️ 폼 데이터 가져오기
        String reserveDate = request.getParameter("reserveDate");
        String reserveTime = request.getParameter("time");
        String[] services = request.getParameterValues("service");
        String designerName = request.getParameter("designerName");
        String designerCareer = request.getParameter("designerCareer");
        String designerImg = request.getParameter("designerImg");
        String address = request.getParameter("address");

        // 2️ 서비스 문자열 합치기
        String servicesStr = (services != null) ? String.join(" + ", services) : "선택 없음";
        String reserveDateTime = reserveDate + " " + reserveTime;

        // 3️ JSP로 전달
        request.setAttribute("designerName", designerName != null ? designerName : "정보 없음");
        request.setAttribute("designerCareer", designerCareer != null ? designerCareer : "");
        request.setAttribute("designerImg", designerImg != null ? designerImg : "img/default.jpg");
        request.setAttribute("address", address != null ? address : "정보 없음");
        request.setAttribute("services", servicesStr);
        request.setAttribute("reserveDateTime", reserveDateTime);

        // 4️ 예약 완료 JSP로 포워드
        request.getRequestDispatcher("/reserveFinish.jsp").forward(request, response);
    }
}
