package member.servlet.userservlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.dto.UserDTO;
import member.service.UserService;
import member.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/user/mypage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	UserService userService = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// forward ��� : ���������� ȸ������ �������� �̵� (review.jsp)
		String id = request.getParameter("id");
		UserDTO user = userService.getUser(id);
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("/mypage_user/main.jsp").forward(request, response);
	}
	
}
