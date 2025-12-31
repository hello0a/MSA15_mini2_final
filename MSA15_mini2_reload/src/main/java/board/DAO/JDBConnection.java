package board.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hairshop?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "hairshop";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC �뱶�씪�씠踰� 濡쒕뱶 �꽦怨�!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection con;
    public PreparedStatement psmt;
    public ResultSet rs;

    public JDBConnection() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            System.err.println("DB 연결 실패!");
            e.printStackTrace();
        }
    }

    public void close() {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (psmt != null) psmt.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
