package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/* ������ : DB ������ DBUtil�� */
public class JDBConnection {

	public Connection con;	// ����� ����̹��� SQL ��û�� ��ü ����
	public PreparedStatement psmt;	// Statement ���� ? �Ķ���� Ȯ�� ��� �߰��� ����
	public ResultSet rs;	// SQL ���� ��� �޾ƿ���
	
	public JDBConnection() {
		try {
			// DB ����
			String url = "jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
			String id = "hairshop";
			String password = "123456";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, id, password);
			
			System.out.println("DB 연동 성공!");
					
		} catch (Exception e) {
			System.err.println("DB 연동 실패!");
			e.printStackTrace();
		}	
	}
	
	// ��Ĺ ���߱� ������ DB ���� ��� ����
	public void close() {
		try { if (rs != null) rs.close(); } catch (Exception e) {}
		try { if (psmt != null) psmt.close(); } catch (Exception e) {}
		try { if (con != null) con.close(); } catch (Exception e) {}
	}
}