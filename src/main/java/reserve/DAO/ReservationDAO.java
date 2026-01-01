package reserve.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.dto.UserDTO;
import reserve.DTO.ReservationDTO;
import reserve.DTO.ReservationListDTO;
import reserve.DTO.StyleDTO;
import reserve.DTO.ReservationDTO;
import reserve.util.DBUtil;

public class ReservationDAO {

    // 예약 추가
    public int insertReservation(ReservationDTO reservation) {
        String sql = "INSERT INTO reserved (no, user_no, designer_no, style_no, date, time, etc, phonenumber, style_name, price) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservation.getNo());
            ps.setInt(2, reservation.getUserNo());
            ps.setInt(3, reservation.getDesignerNo());
            ps.setInt(4, reservation.getStyleNo());
            ps.setDate(5, reservation.getReserveDate());
            ps.setString(6, reservation.getReserveTime());
            ps.setString(7, reservation.getEtc());
            ps.setString(8, reservation.getPhoneNumber());
            ps.setString(9, reservation.getStyleName());
            ps.setInt(10, reservation.getPrice());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("예약을 실패 하였습니다.");
            System.out.println(e.getMessage());
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
    public boolean isAlreadyReserved(int designerNo, Date date, String time,int styleNo ) {
        String sql = "SELECT COUNT(*) FROM reserved WHERE designer_no=? AND date=? AND time=? AND style_no=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, designerNo);
            ps.setDate(2, date);
            ps.setString(3, time);
            ps.setInt(4, styleNo);

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
    
    public List<ReservationDTO> select(int userNo) {
        List<ReservationDTO> list = new ArrayList<>();
    	String sql = "SELECT * "
    				+ " FROM reserved"
    				+ " WHERE user_no = ? order by date desc";
    	    	
    	try (Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){

    		ps.setInt(1, userNo);
    		ResultSet rs = ps.executeQuery();
			while (rs.next()) {
    			ReservationDTO reserve = new ReservationDTO();
    			reserve.setReserveDate(rs.getDate("date"));
    			reserve.setReserveTime(rs.getString("time"));
    			reserve.setStyleName(rs.getString("style_name"));
    			reserve.setPhoneNumber(rs.getString("phonenumber"));
    			reserve.setPrice(rs.getInt("price"));
    			
    			list.add(reserve);
			}
			
		} catch (Exception e) {
			System.err.println("예약 조회 실패!");
			e.printStackTrace();
		}	
    	return list;
    }

    public List<ReservationListDTO> selectWithDesigner(int userNo) {
        List<ReservationListDTO> list = new ArrayList<>();
    	String sql = "SELECT  "
	    			+ " d.shop_name AS shop_name,"
	    			+ " r.date,"
	    			+ " r.time,"
	    			+ " r.style_name,"
	    			+ " r.phonenumber,"
	    			+ " r.price "
    				+ "FROM reserved r "
    				+ "JOIN designer d "
    				+ "ON r.designer_no = d.no "
    				+ "WHERE r.user_no = ? order by r.date desc";
    	    	
    	try (Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){

    		ps.setInt(1, userNo);
    		ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ReservationListDTO reserve = new ReservationListDTO();
    			reserve.setDate(rs.getDate("date"));
    			reserve.setTime(rs.getString("time"));
    			reserve.setServiceName(rs.getString("style_name"));
    			reserve.setPhoneNumber(rs.getString("phonenumber"));
    			reserve.setPrice(rs.getInt("price"));
    			reserve.setDesignerName(rs.getString("shop_name")); // 맺장병 필드이름으로 변경 필오. shop_name
    			
    			list.add(reserve);
			}
			
		} catch (Exception e) {
			System.err.println("예약 조회 실패!");
			e.printStackTrace();
		}	
    	return list;
    }
    public List<ReservationListDTO> selectWithUser(int designerNo) {
    	List<ReservationListDTO> list = new ArrayList<>();
    	String sql = "SELECT  "
    			+ " u.full_name AS full_name,"
    			+ " r.no,"
    			+ " r.date,"
    			+ " r.time,"
    			+ " r.style_name,"
    			+ " r.phonenumber,"
    			+ " r.price "
    			+ "FROM reserved r "
    			+ "JOIN users u "
    			+ "ON r.user_no = u.no "
    			+ "WHERE r.designer_no = ? order by r.no desc";
    	
    	try (Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		
    		ps.setInt(1, designerNo);
    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			ReservationListDTO reserve = new ReservationListDTO();
    			reserve.setNo(rs.getInt("no"));
    			reserve.setDate(rs.getDate("date"));
    			reserve.setTime(rs.getString("time"));
    			reserve.setServiceName(rs.getString("style_name"));
    			reserve.setPhoneNumber(rs.getString("phonenumber"));
    			reserve.setPrice(rs.getInt("price"));
    			reserve.setUserName(rs.getString("full_name"));
    			
    			list.add(reserve);
    		}
    		
    	} catch (Exception e) {
    		System.err.println("예약 조회 실패!");
    		e.printStackTrace();
    	}	
    	return list;
    }

    public ReservationListDTO selectByNo(int reserveNo) {
    	String sql = "SELECT  "
    			+ " u.full_name AS full_name,"
    			+ " u.birth AS birth,"
    			+ " u.gender AS gender,"
    			+ " u.phonenumber AS phonenumber,"
    			+ " r.no,"
    			+ " r.date,"
    			+ " r.time,"
    			+ " r.style_name,"
    			+ " r.etc,"
    			+ " r.price "
    			+ "FROM reserved r "
    			+ "JOIN users u "
    			+ "ON r.user_no = u.no "
    			+ "WHERE r.no = ? order by r.no desc";
    	
    	try (Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		
    		ps.setInt(1, reserveNo);
    		ResultSet rs = ps.executeQuery();
    		if (rs.next()){
    			ReservationListDTO reserve = new ReservationListDTO();
    			reserve.setNo(rs.getInt("no"));
    			reserve.setDate(rs.getDate("date"));
    			reserve.setTime(rs.getString("time"));
    			reserve.setServiceName(rs.getString("style_name"));
    			reserve.setPhoneNumber(rs.getString("phonenumber"));
    			reserve.setPrice(rs.getInt("price"));
    			reserve.setUserName(rs.getString("full_name"));
    			reserve.setBirth(rs.getDate("birth"));
    			reserve.setGender(rs.getString("gender"));
    			reserve.setEtc(rs.getString("etc"));
    			
    			return reserve;
    		}
    		
    	} catch (Exception e) {
    		System.err.println("예약 조회 실패!");
    		e.printStackTrace();
    	}	
    	return null;
    }
    
	public int reserveEtcUpdate(int no, String etc) {
		String sql = " UPDATE reserved "
					+ " SET etc = ? "
					+ " WHERE no = ?";
		int result = 0;
		
    	try (Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, etc);
			ps.setInt(2, no);
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("예약 정보 수정 시 예외 발생!");
			e.printStackTrace();
		}
		return result;
	}    
}
