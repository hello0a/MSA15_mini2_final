package reservation.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static final String url = "jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String id = "hairshop";
	private static final String password = "123456";
	
	static {
		try {
			// DB 연결
			Class.forName("com.mysql.cj.jdbc.Driver");					
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("DB 연결 실패!", e);
		}	
	}

	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, id, password);
	}
}
