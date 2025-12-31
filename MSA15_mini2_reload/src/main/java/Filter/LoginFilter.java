package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import reservation.dto.PersistenceLogins;
import reservation.dto.UserDTO;
import reservation.service.PersistenceLoginsService;
import reservation.service.PersistenceLoginsServiceImpl;
import reservation.service.UserService;
import reservation.service.UserServiceImpl;

import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(description = "자동 로그인 등 인증 처리 필터", urlPatterns = { "/*" })
public class LoginFilter extends HttpFilter implements Filter {
	// 1. 필터 도구 : DB에서 정보 가져오는 서비스 클래스
	// PersistenceLoginsService -> 자동 로그인 토큰 관리
	PersistenceLoginsService persistenceLoginsService;
	// UserService -> 사용자 정보 조회
	UserService userService;
    public LoginFilter() {
        super();
    }
    // 2. 필터 처음 만들어질 때 : 서버 시작할 때 딱 1번 실행
    // - 서비스 객체 준비
    // - 나중에 자동 로그인 검사할 때 써야지~!
    public void init(FilterConfig fConfig) throws ServletException {
    	persistenceLoginsService = new PersistenceLoginsServiceImpl();
    	userService = new UserServiceImpl();
    }
    // 3. 핵심! 사용자가 페이지 요청할 때마다 실행
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 쿠키 확인
		// 1. 자동 로그인 여부
		// 2. 인증 토큰 검증
		
		// 3-1. HttpServletRequest로 변환
		// - ServletRequest는 기능이 적어서, 웹 관련 기능이 많은 HttpServletRequest 로 변환
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 3-2. 쿠키 가져오기
		// - 브라우저가 들고 온 쿠키들
		Cookie[] cookies = httpRequest.getCookies();
		// 3-3. 쿠키에서 값 꺼내기
		String rememberMe = null; 	// 자동 로그인 여부
		String token = null;	// 인증 토큰
		if ( cookies != null ) {
			// 3-3.1 쿠키 하나씩 확인하면서
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = URLDecoder.decode( cookie.getValue(), "UTF-8");
				switch( name ) {
					// 3-3.2 결과 
					// rememberMe : 자동 로그인 체크 여부
					// token : 사용자 인증용 비밀 번호
					case "rememberMe" : rememberMe = value; break;
					case "token" : token = value; break;
				}
			}
		}
		System.out.println("LoginFilter...");
		System.out.println("rememberMe : " + rememberMe);
		System.out.println("token : " + token);
		// 4. 세션 확인 (이미 로그인했는지)
		// - 세션에 저장된 로그인 정보
		// 로그인 여부 확인
		HttpSession session = httpRequest.getSession();
		String loginId = (String) session.getAttribute("id");
		
		// 4-1. 세션이 있다면?
		// - 이미 로그인 -> 그냥 통과!
		// - 필터 끝!
		
		// 이미 로그인 됨
		if ( loginId != null )	{
			chain.doFilter(request, response);
			System.out.println("로그인된 사용자 : " + loginId);
			return;
		}
		
		// 5. 자동 로그인 시도
		// - 자동 로그인 체크했었고 & 토큰 쿠키도 있다면
		// 자동 로그인 및 토큰 여부 체크
		if ( rememberMe != null & token != null ) {
			System.out.println("rememberMe : " + rememberMe);
			System.out.println("token : " + token);
			// 5-1. 토큰으로 DB 조회
			// - 이 토큰이 DB에 있는 진짜 토큰인지 확인
			PersistenceLogins persistenceLogins = persistenceLoginsService.selectByToken(token);
			System.out.println("persistenceLogins : " + persistenceLogins);
			// 5-2. 토큰 유효한지 확인
			// - 만료 안 됐는지, 위조 아닌지 검사
			boolean isValid = persistenceLoginsService.isValid(token);
			// 5-3. 자동 로그인 성공 시
			// 토큰 존재 및 유효 여부 체크
			if( persistenceLogins != null && isValid) {
				// - 토큰 주인의 아이디 가져오기
				loginId = persistenceLogins.getId();
				UserDTO user = userService.getUser(loginId);
				String name = user.getFull_name();
				System.out.println("loginId : " + loginId);
				// 5-4. 세션에 로그인 정보 저장
				// - 이 순간부터 로그인된 사용자!
				//로그인 처리
				session.setAttribute("username", loginId);
				session.setAttribute("name", name);
				System.out.println("자동 로그인 성공!");
			}
		}
		// 6. 다음 단계로 넘겨라!
		// - Controller로 이동
		chain.doFilter(request, response);
	}

}
