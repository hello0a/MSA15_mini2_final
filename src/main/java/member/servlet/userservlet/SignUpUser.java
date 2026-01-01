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
import java.sql.Date;

@WebServlet("/user/signup")
public class SignUpUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService userService = new UserServiceImpl();
    public SignUpUser() {
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/signup/user_signup.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String birthYear = request.getParameter("birthYear");
		String birthMonth = request.getParameter("birthMonth");
		String birthDay = request.getParameter("birthDay");
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");

		// 2025-12-28
		String birthStr = birthYear + "-" + birthMonth + "-" + birthDay;
		java.sql.Date birth = java.sql.Date.valueOf(birthStr);
		
		UserDTO users = UserDTO.builder()
								.id(id)
								.password(pw)
								.email(email)
								.full_name(name)
								.phonenumber(phone)
								.birth(birth)
								.gender(gender)
								.nationality(nationality)
								.build() ;
		
		int result = userService.signup(users);
		if (result == 0) {
			response.sendRedirect(request.getContextPath() + "/user/signup");
		} else {
			response.sendRedirect(request.getContextPath() + "/main");
		}
	}

}
