package reservation.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reservation.dto.DesignerDTO;
import reservation.dto.UserDTO;
import reservation.service.DesignerService;
import reservation.service.DesignerServiceImpl;
import reservation.service.UserService;
import reservation.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/designer/id_find")
public class DesignerFindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DesignerService designerService = new DesignerServiceImpl();
    public DesignerFindId() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		request.getRequestDispatcher("/login/designer_id_find_test.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String id = designerService.findByNameAndEmail(name, email);
		response.sendRedirect(request.getContextPath() + "/designer/id_find?id=" + id);
	}

}
