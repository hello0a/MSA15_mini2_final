package member.servlet.designerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.dto.DesignerDTO;
import member.dto.PersistenceLogins;
import member.dto.UserDTO;
import member.service.DesignerService;
import member.service.DesignerServiceImpl;
import member.service.PersistenceLoginsService;
import member.service.PersistenceLoginsServiceImpl;
import member.service.UserService;
import member.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/designer/login")
public class DesignerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DesignerService designerService = new DesignerServiceImpl();
    public DesignerLogin() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 아이디 저장 쿠키 확인
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("loginId")) {
				request.setAttribute("loginId", cookies[i].getValue());
				break;
			}
		}
		

		request.getRequestDispatcher("/login/designer_login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		DesignerDTO designer = DesignerDTO.builder()
							.id(id)
							.password(pw)
							.build();
		
		DesignerDTO result = designerService.login(designer);
		System.out.println("로그인 시도...");
		
		String rememberId = request.getParameter("remember-id");
		if ( rememberId != null && rememberId.equals("on")) {
			System.out.println("아이디 저장 체크");
			Cookie loginIdCookie= new Cookie("loginId", id);
			loginIdCookie.setMaxAge(60 * 60 * 24 * 7); 	// 7일
			loginIdCookie.setPath("/"); 	// 전체 사이트에서 접근 가능
			response.addCookie(loginIdCookie);
		} else { 
			System.out.println("아이디 저장 체크 해제");
			Cookie loginIdCookie= new Cookie("loginId", id);
			loginIdCookie.setMaxAge(0); 	// 0일 -> 삭제
			loginIdCookie.setPath("/"); 	// 전체 사이트에서 접근 가능
			response.addCookie(loginIdCookie);
		}
		
		if (result == null) {
			// 다시 로그인 화면으로 이동
			response.sendRedirect(request.getContextPath() + "/designer/login");
			return;
		} else {
		
			HttpSession session = request.getSession();
			session.setAttribute("designer", result);
			session.setAttribute("designerNo", result.getNo());
			session.setAttribute("id", result.getId());
			session.setAttribute("pw", result.getPassword());
		}
		
		String rememberMe = request.getParameter("remember-me");
		PersistenceLogins login = null;
		if ( rememberMe != null && rememberMe.equals("on")) {
			PersistenceLoginsService persistenceLoginsService
				= new PersistenceLoginsServiceImpl();
			login =  persistenceLoginsService.refresh(id);
		} else {
			Cookie tokenCookie = new Cookie("token", "");
			tokenCookie.setMaxAge(0);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			
			Cookie rememberMeCookie = new Cookie("rememberMe", "");
			rememberMeCookie.setMaxAge(0);
			rememberMeCookie.setPath("/");
			response.addCookie(rememberMeCookie);
		}
		if ( login != null) {
			String token = login.getToken();
			Cookie tokenCookie = new Cookie("token", token);
			tokenCookie.setMaxAge(60 * 60 * 24 * 7);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			
			Cookie rememberMeCookie = new Cookie("rememberMe", rememberMe);
			rememberMeCookie.setMaxAge(60 * 60 * 24 * 7);
			rememberMeCookie.setPath("/");
			response.addCookie(rememberMeCookie);
		}
		response.sendRedirect(request.getContextPath() + "/main?id=" + id);
		
	}

}
