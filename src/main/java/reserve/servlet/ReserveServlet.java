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
        HttpSession session = request.getSession(false);

        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        String store = request.getParameter("store");
        String designerNo = request.getParameter("designerNo");
        // String[] parts = designerNoTemp.split("\\^");
        // String result = parts[1];
        // System.out.println(result); // 111111
        // String designerNo = result;

        String reserveDateStr = request.getParameter("reserveDate");

        request.setAttribute("selectedDesignerNo", designerNo);
        request.setAttribute("selectedStore", store);

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
        try {
			request.setAttribute("styles", service.getAllStyles());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("/reserve/reserve.jsp").forward(request, response);
    }

    // POST: 예약 등록 + PRG
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }
        
        String userPhone = (String) session.getAttribute("userPhone");

        String designerNo = request.getParameter("designerNo");
        String[] styleNoStr = request.getParameterValues("styleNo");
        String reserveDateStr = request.getParameter("reserveDate");
        String reserveTime = request.getParameter("time");
        String etc = request.getParameter("etc");
        String phoneNumber = userPhone;

        // 필드 유효성 체크
        if (designerNo == null || styleNoStr == null ||
            reserveDateStr == null || reserveTime == null || reserveTime.isEmpty()) {
        	System.out.println("필수 값 주세요");
            request.setAttribute("msg", "예약 정보가 올바르지 않습니다.");
            request.getRequestDispatcher("/reserve/reserve.jsp").forward(request, response);
            return;
        }

        // POST 할 예약 시간 유효성 체크
        List<String> validTimes = new ArrayList<>();
        for (int hour = 10; hour <= 22; hour += 2) {
            validTimes.add(String.format("%02d:00", hour));
        }
        if (!validTimes.contains(reserveTime)) {
        	System.out.println("시간을 주세요");
            request.setAttribute("msg", "유효하지 않은 예약 시간입니다.");
            request.getRequestDispatcher("/reserve/reserve.jsp").forward(request, response);
            return;
        }

        int totalPrice = 0;
        ArrayList<ReservationDTO> dtoList = new ArrayList();
        if (styleNoStr != null) {
            for (String v : styleNoStr) {
                // v 예: "1:컷:20000"
                String[] parts = v.split(":");
                if (parts.length != 3) continue; // 방어코드

                int no = Integer.parseInt(parts[0]);
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);

                totalPrice += price;

                System.out.println("no=" + no + ", name=" + name + ", price=" + price);
                // TODO: DB 저장용 리스트에 담기 등 처리
                
                ReservationDTO dto = new ReservationDTO();
                dto.setUserNo(userNo);
                dto.setDesignerNo(Integer.parseInt(designerNo));
                dto.setStyleNo(no);
                dto.setPrice(price);
                dto.setReserveDate(Date.valueOf(reserveDateStr));
                dto.setReserveTime(reserveTime);
                dto.setEtc(etc);
                dto.setPhoneNumber(phoneNumber);
                dto.setStyleName(name);

                System.out.println("userNo : " + userNo);
                System.out.println(dto.toString());
                boolean success = service.addReservation(dto);
                if (success) {
                	dtoList.add(dto);
                } else {
                	System.out.println("중복 시간입니다.");
                    request.setAttribute("msg", "이미 예약된 시간입니다.");
                    request.getRequestDispatcher("/reserve/reserve.jsp").forward(request, response);
                    return;
                }
            }
        }
        System.out.println(dtoList.toString());
        request.setAttribute("reserveFinishInfo", dtoList);
//        response.sendRedirect(request.getContextPath() + "/reserveFinish");
        request.getRequestDispatcher("/reserveFinish").forward(request, response);

    }
}
