package reserve.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reserve.DTO.ReservationDTO;
import reserve.DTO.StyleDTO;
import reserve.DTO.ReservationDTO;
import reserve.util.DBUtil;

public class ReservationDAO {

    // 예약 추가
    public int insertReservation(ReservationDTO reservation) {
        String sql = "INSERT INTO reserved (user_no, designer_no, style_no, date, time, etc, phonenumber, price) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservation.getUserNo());
            ps.setInt(2, reservation.getDesignerNo());
            ps.setInt(3, reservation.getStyleNo());
            ps.setDate(4, reservation.getReserveDate());
            ps.setString(5, reservation.getReserveTime());
            ps.setString(6, reservation.getEtc());
            ps.setString(7, reservation.getPhoneNumber());
            ps.setInt(8, reservation.getPrice());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 시술 가격 조회
    public int getStylePrice(int styleNo) {
        String sql = "SELECT price FROM style WHERE no=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, styleNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("price");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // **시술 리스트 조회**
    public List<StyleDTO> getAllStyles() {
        List<StyleDTO> list = new ArrayList<>();
        String sql = "SELECT no, name, price FROM style";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StyleDTO s = new StyleDTO();
                s.setNo(rs.getInt("no"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getInt("price"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 예약 중복 확인
    public boolean isAlreadyReserved(int designerNo, Date date, String time) {
        String sql = "SELECT COUNT(*) FROM reserved WHERE designer_no=? AND date=? AND time=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, designerNo);
            ps.setDate(2, date);
            ps.setString(3, time);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 예약 가능한 시간
    public List<String> getAvailableTimes(int designerNo, Date reserveDate) {
        List<String> times = new ArrayList<>();
        String[] allTimes = { "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00" };
        String sql = "SELECT time FROM reserved WHERE designer_no=? AND date=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, designerNo);
            ps.setDate(2, reserveDate);
            ResultSet rs = ps.executeQuery();

            List<String> reserved = new ArrayList<>();
            while (rs.next()) {
                reserved.add(rs.getString("time").substring(0,5));
            }
            for (String t : allTimes) {
                if (!reserved.contains(t)) times.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return times;
    }
}
