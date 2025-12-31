package reservation.servlet.userservlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reservation.dto.UserDTO;
import reservation.service.UserService;
import reservation.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/user/mypage/profile_edit")
public class MyPageProfileEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private UserService userService = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// forward ��� : ���������� ȸ������ �������� �̵� (review.jsp)
		String id = request.getParameter("id");
		UserDTO user = userService.getUser(id);
//		request.getRequestDispatcher("/info.jsp").forward(request, response);		
		request.setAttribute("user", user);
		request.getRequestDispatcher("/mypage_user/profile_edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String birthStr = request.getParameter("birth");
		java.sql.Date birth = java.sql.Date.valueOf(birthStr);
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phonenumber");
		String gender = request.getParameter("gender");
		
		UserDTO user = UserDTO.builder()
				.id(id)
				.password(password)
				.email(email)
				.full_name(name)
				.birth(birth)
				.gender(gender)
				.phonenumber(phonenumber)
				.nationality("")
				.build();

		// ȸ�� ������Ʈ.
		int result = userService.mypageupdate(user);
		if (result>0) {
			
		} else {
			
		}
	
		response.sendRedirect(request.getContextPath() + "/user/mypage/profile?id=" + id);
		
		// ������Ʈ �� ����� ����.
//		UserDTO user = userService.getUser(id);
//		request.setAttribute("user", user);
//		request.getRequestDispatcher("/mypage_user/info.jsp").forward(request, response);
	}
}
