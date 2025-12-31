package reservation.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reservation.dto.DesignerDTO;
import reservation.service.DesignerService;
import reservation.service.DesignerServiceImpl;

import java.io.IOException;

@WebServlet("/designer/profile")
public class DesignerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DesignerService designerService = new DesignerServiceImpl();
	
    public DesignerProfile() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // 기존 세션만 가져옴
		if (session == null || session.getAttribute("id") == null) {
		    // 세션 없거나 로그인 안 된 경우
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}

		String id = (String) session.getAttribute("id");
		DesignerDTO designerDto = designerService.getDesigner(id);
		request.setAttribute("designer", designerDto);
		request.getRequestDispatcher("/mypage_designer/profile.jsp")
		       .forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
