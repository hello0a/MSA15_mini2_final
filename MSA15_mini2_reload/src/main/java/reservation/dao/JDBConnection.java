package reservation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/* 참고용 : DB 연결은 DBUtil로 */
public class JDBConnection {

	public Connection con;	// 연결된 드라이버에 SQL 요청할 객체 생성
	public PreparedStatement psmt;	// Statement 에서 ? 파라미터 확장 기능 추가로 제공
	public ResultSet rs;	// SQL 실행 결과 받아오기
	
	public JDBConnection() {
		try {
			// DB 연결
			String url = "jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
			String id = "hairshop";
			String password = "123456";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, id, password);
			
			System.out.println("DB 연결 성공!");
					
		} catch (Exception e) {
			System.err.println("DB 연결 실패!");
			e.printStackTrace();
		}	
	}
	
	// 톰캣 멈추기 전까지 DB 연결 계속 유지
	public void close() {
		try { if (rs != null) rs.close(); } catch (Exception e) {}
		try { if (psmt != null) psmt.close(); } catch (Exception e) {}
		try { if (con != null) con.close(); } catch (Exception e) {}
	}
}