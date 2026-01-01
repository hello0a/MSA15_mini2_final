package member.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.dto.DesignerDTO;
import member.service.DesignerService;
import member.service.DesignerServiceImpl;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/designer/profile_edit")
public class DesignerProfileEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DesignerService designerService = new DesignerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		DesignerDTO designer = designerService.getDesigner(id);
		System.out.println("디자이너 - 회원정보수정");
		System.out.println(designer);
		request.setAttribute("designer", designer);
		request.getRequestDispatcher("/mypage_designer/profile_edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birthStr = request.getParameter("birth");
		java.sql.Date birth = java.sql.Date.valueOf(birthStr);
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String shopName = request.getParameter("shopName");
		String bizNum = request.getParameter("bizNum");
		String gender = request.getParameter("gender");
		String nation = request.getParameter("nation");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String addrDetail = request.getParameter("addrDetail");		
		
		DesignerDTO designer = DesignerDTO.builder()
				.id(id)
				.password(password)
				.full_name(name)
				.birth(birth)
				.email(email)
				.phonenumber(phone)
				.shop_name(shopName)
				.biz_num(bizNum)
				.gender(gender)
				.nationality(nation)
				.city(city)
				.district(district)
				.addr_detail(addrDetail)			
				.build();
		
		int result = designerService.mypageupdate(designer);
		
		if (result>0) {
			
		} else {
			
		}
		
		response.sendRedirect(request.getContextPath() + "/designer/profile?id=" +id);
	}

}
