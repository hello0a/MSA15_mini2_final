package reservation.servlet.userservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reservation.dto.UserDTO;
import reservation.service.UserService;
import reservation.service.UserServiceImpl;

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
		request.getRequestDispatcher("/login/id_find_test.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String id = userService.findByNameAndEmail(name, email);
		response.sendRedirect(request.getContextPath() + "/id_find?id=" + id);
	}

}
