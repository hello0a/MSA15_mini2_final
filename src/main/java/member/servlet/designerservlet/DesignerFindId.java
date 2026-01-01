package member.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.dto.DesignerDTO;
import member.dto.UserDTO;
import member.service.DesignerService;
import member.service.DesignerServiceImpl;
import member.service.UserService;
import member.service.UserServiceImpl;

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
		request.getRequestDispatcher("/login/designer_id_find.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String id = designerService.findByNameAndEmail(name, email);
		response.sendRedirect(request.getContextPath() + "/designer/id_find?id=" + id);
	}

}
