package reservation.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reservation.dto.DesignerDTO;
import reservation.service.DesignerService;
import reservation.service.DesignerServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	DesignerService designerService = new DesignerServiceImpl();
    public Main() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("연결 성공!");
		
        List<DesignerDTO> designers = designerService.findAll();
        request.setAttribute("designerList", designers);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
