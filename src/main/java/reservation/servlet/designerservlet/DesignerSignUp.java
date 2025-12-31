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

@WebServlet("/designer/signup")
public class DesignerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DesignerService designerService = new DesignerServiceImpl();
    public DesignerSignUp() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.getRequestDispatcher("/signup/signup_owner_test.jsp").forward(request, response);
		
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
		String nationality = request.getParameter("nation");
		String shop_name = request.getParameter("shopName");
		String biz_num = request.getParameter("bizNum");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String addrDetail = request.getParameter("addrDetail");
		
		// 2025-12-28
		String birthStr = birthYear + "-" + birthMonth + "-" + birthDay;
		java.sql.Date birth = java.sql.Date.valueOf(birthStr);

		DesignerDTO designerDto = DesignerDTO.builder()
				.id(id)
				.password(pw)
				.email(email)
				.full_name(name)
				.phonenumber(phone)
				.birth(birth)
				.gender(gender)
				.nationality(nationality)
				.shop_name(shop_name)
				.biz_num(biz_num)
				.city(city)
				.district(district)
				.addr_detail(addrDetail)
				.build();
		
		int result = designerService.signup(designerDto);
		if (result == 0) {
			response.sendRedirect(request.getContextPath() + "/designer/signup");
		} else {
			response.sendRedirect(request.getContextPath() + "/main/");
		}
	}

}
