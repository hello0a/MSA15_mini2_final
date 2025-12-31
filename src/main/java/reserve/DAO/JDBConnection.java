package reserve.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hairshop"; // DB 이름
    private static final String USER = "aloha";  // 본인 계정
    private static final String PASSWORD = "123456"; // 본인 비밀번호

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
