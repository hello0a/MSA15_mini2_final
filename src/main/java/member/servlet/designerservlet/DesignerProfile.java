package member.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.dto.DesignerDTO;
import member.service.DesignerService;
import member.service.DesignerServiceImpl;

import java.io.IOException;

@WebServlet("/designer/profile")
public class DesignerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DesignerService designerService = new DesignerServiceImpl();
	
    public DesignerProfile() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // ���� ���Ǹ� ������
		if (session == null || session.getAttribute("id") == null) {
		    // ���� ���ų� �α��� �� �� ���
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
