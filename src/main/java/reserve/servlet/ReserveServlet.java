package reserve.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reserve.DTO.ReservationDTO;
import reserve.service.ReservationService;

@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {

    private ReservationService service = new ReservationService();

    // GET: 예약 페이지 + 시간 + 시술 리스트
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String store = request.getParameter("store");
        String designerNo = request.getParameter("designerNo");
        String reserveDateStr = request.getParameter("reserveDate");

        request.setAttribute("selectedStore", store);
        request.setAttribute("selectedDesignerNo", designerNo);

        // 하드코딩 시간 생성 (10~22시 2시간 간격)
        if (store != null && designerNo != null && reserveDateStr != null && !reserveDateStr.isEmpty()) {

            // 서버 기준 현재 시간
            LocalTime now = LocalTime.now();

            List<String> available = new ArrayList<>();

            LocalDate selectedDate = LocalDate.parse(reserveDateStr);
            LocalDate today = LocalDate.now();

            for (int hour = 10; hour <= 22; hour += 2) {
                LocalTime slot = LocalTime.of(hour, 0);

                // 선택 날짜가 *오늘*이면 현재 시간 이후만 추가
                if (selectedDate.isEqual(today)) {
                    if (slot.isAfter(now)) {
                        available.add(slot.toString());
                    }
                } else {
                    // 현재 날짜보다 미래 날짜면 전부 추가
                    available.add(slot.toString());
                }
            }
            request.setAttribute("availableTimes", available);
        }

        // 시술 리스트
        request.setAttribute("styles", service.getAllStyles());
        request.getRequestDispatcher("/reserve.jsp").forward(request, response);
    }

    // POST: 예약 등록 + PRG
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String designerNo = request.getParameter("designerNo");
        String styleNoStr = request.getParameter("styleNo");
        String reserveDateStr = request.getParameter("reserveDate");
        String reserveTime = request.getParameter("time");
        String etc = request.getParameter("etc");
        String phoneNumber = request.getParameter("phoneNumber");

        // 필드 유효성 체크
        if (designerNo == null || styleNoStr == null ||
            reserveDateStr == null || reserveTime == null || reserveTime.isEmpty()) {

            request.setAttribute("msg", "예약 정보가 올바르지 않습니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
            return;
        }

        // POST 할 예약 시간 유효성 체크
        List<String> validTimes = new ArrayList<>();
        for (int hour = 10; hour <= 22; hour += 2) {
            validTimes.add(String.format("%02d:00", hour));
        }
        if (!validTimes.contains(reserveTime)) {
            request.setAttribute("msg", "유효하지 않은 예약 시간입니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
            return;
        }

        ReservationDTO dto = new ReservationDTO();
        dto.setUserNo(userNo);
        dto.setDesignerNo(Integer.parseInt(designerNo));
        dto.setStyleNo(Integer.parseInt(styleNoStr));
        dto.setReserveDate(Date.valueOf(reserveDateStr));
        dto.setReserveTime(reserveTime);
        dto.setEtc(etc);
        dto.setPhoneNumber(phoneNumber);

        boolean success = service.addReservation(dto);

        if (success) {
            session.setAttribute("reserveFinishInfo", dto);
            response.sendRedirect(request.getContextPath() + "/reserveFinish.jsp");
        } else {
            request.setAttribute("msg", "이미 예약된 시간입니다.");
            request.getRequestDispatcher("/reserve.jsp").forward(request, response);
        }
    }
}
