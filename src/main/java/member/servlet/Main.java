package member.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import member.dto.DesignerDTO;
import member.service.DesignerService;
import member.service.DesignerServiceImpl;


import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Main() {
    }

	DesignerService designerService = new DesignerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("2. 연결 성공!");
		
        List<DesignerDTO> designers = designerService.findAll();
		
        request.setAttribute("designerList", designers);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("예약 연결 성공");
	}

}
