package member.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reserve.DTO.ReservationListDTO;
import reserve.service.ReservationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/designer/reserve_detail")
public class DesignerReserveDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private ReservationService reservationService = new ReservationService();

    public DesignerReserveDetail() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        Integer designerNo = (Integer) session.getAttribute("designerNo");
        if (designerNo == null) {
            response.sendRedirect(request.getContextPath() + "/designer/login");
            return;
        }

        String noStr = request.getParameter("no");
        int reserveNo = Integer.parseInt(noStr);

        ReservationListDTO reservations = reservationService.selectByNo(reserveNo);

        request.setAttribute("reserveNo", reserveNo);
        request.setAttribute("reservations", reservations);
		request.getRequestDispatcher("/mypage_designer/reserve_detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noStr = request.getParameter("no");
		int reserveNo = Integer.parseInt(noStr);
		String etc = request.getParameter("etc");
		
		int result = reservationService.reserveEtcUpdate(reserveNo, etc);
		if (result>0) {
			
		} else {
			
		}
	
		response.sendRedirect(request.getContextPath() + "/designer/reserve_detail?no=" + reserveNo);
	}

}
