package member.servlet.userservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.dto.UserDTO;
import member.service.UserService;
import member.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/id_find")
public class FindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService userService = new UserServiceImpl();
    public FindId() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		request.getRequestDispatcher("/login/user_id_find.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String id = userService.findByNameAndEmail(name, email);
		response.sendRedirect(request.getContextPath() + "/id_find?id=" + id);
	}

}
