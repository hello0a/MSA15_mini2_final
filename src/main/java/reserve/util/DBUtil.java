package reserve.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // DB 접속 정보
    private static final String URL =
    		"jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";;
    private static final String USER = "hairshop";
    private static final String PASSWORD = "123456";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("DB �뱶�씪�씠踰� 濡쒕뱶 �꽦怨�!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // DB 연결 메서드
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
