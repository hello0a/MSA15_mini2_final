package reserve.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // DB 접속 정보
    private static final String URL =
    		"jdbc:mysql://127.0.0.1:3306/aloha?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";;
    private static final String USER = "aloha";
    private static final String PASSWORD = "123456";

    // DB 연결 메서드
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
