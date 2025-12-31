package reserve.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reserve.DTO.ReservationDTO;
import reserve.service.ReservationService;

@WebServlet("/reservation")
public class ReservationController extends HttpServlet {

    private ReservationService service = new ReservationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        // 로그인한 사용자 userNo를 세션에서 가져옵니다
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            request.setAttribute("msg", "❌ 로그인 후 예약해주세요.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 폼 데이터 가져오기
        String designerNoStr = request.getParameter("designerNo");
        String styleNoStr = request.getParameter("styleNo");
        String reserveDateStr = request.getParameter("reserveDate");
        String reserveTime = request.getParameter("time");
        String[] servicesArr = request.getParameterValues("service");

        // null 또는 빈 값 체크
        if (designerNoStr == null || styleNoStr == null ||
            reserveDateStr == null || reserveDateStr.isEmpty() ||
            reserveTime == null || reserveTime.isEmpty() ||
            servicesArr == null || servicesArr.length == 0) {

            request.setAttribute("msg", "❌ 예약 정보가 올바르지 않습니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
            return;
        }

        // 날짜/시간 유효성 체크 (서버 측)
        LocalDate selectedDate = LocalDate.parse(reserveDateStr);
        LocalDate today = LocalDate.now();

        // 과거 날짜 예약 방지
        if (selectedDate.isBefore(today)) {
            request.setAttribute("msg", "❌ 과거 날짜는 예약할 수 없습니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
            return;
        }

        // 오늘 날짜인 경우, 현재 시간 이전 시간 예약 금지
        LocalTime now = LocalTime.now();
        LocalTime selectedTime = LocalTime.parse(reserveTime);
        if (selectedDate.isEqual(today) && selectedTime.isBefore(now)) {
            request.setAttribute("msg", "❌ 지나간 시간은 예약할 수 없습니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
            return;
        }

        // checkbox 배열을 문자열로 변환
        String services = String.join(" + ", servicesArr);

        // java.sql.Date로 변환
        Date reserveDate = Date.valueOf(reserveDateStr);

        // DTO 생성 + 세팅
        ReservationDTO reservation = new ReservationDTO();
        reservation.setUserNo(userNo);                                   // ✅ DTO에 있는 userNo
        reservation.setDesignerNo(Integer.parseInt(designerNoStr));      // ✅ DTO에 있는 designerNo
        reservation.setStyleNo(Integer.parseInt(styleNoStr));            // ✅ DTO에 있는 styleNo
        reservation.setReserveDate(reserveDate);
        reservation.setReserveTime(reserveTime);
        reservation.setServices(services);

        // Service 호출 (중복 체크 포함)
        boolean result = service.addReservation(reservation);

        // 메시지 설정 + 페이지 이동
        if (result) {
            request.setAttribute("msg", "✅ 예약이 완료되었습니다!");
        } else {
            request.setAttribute("msg", "❌ 이미 예약된 시간입니다.");
        }
        request.getRequestDispatcher("/reserve.jsp").forward(request, response);
    }
}
