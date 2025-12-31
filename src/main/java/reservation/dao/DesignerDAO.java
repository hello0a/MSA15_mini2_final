package reservation.dao;

import reservation.dto.DesignerDTO;

public class DesignerDAO extends JDBConnection{
	
	
	public DesignerDTO select(String id) {
		
		String sql = "SELECT * "
						+ " FROM designer"
						+ " WHERE id = ? ";
		
		DesignerDTO result = null;
		
		try (var con = reservation.utils.DBUtil.getConnection();
			var ps = con.prepareStatement(sql)){
			
			ps.setString(1, id);
			
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					DesignerDTO designerId = new DesignerDTO();
					
					designerId.setNo(rs.getInt("no"));
					designerId.setId(rs.getString("id"));
					designerId.setPassword(rs.getString("password"));
					designerId.setEmail(rs.getString("email"));
					designerId.setFull_name(rs.getString("full_name"));
					designerId.setBirth(rs.getDate("birth"));
					designerId.setGender(rs.getString("gender"));
					designerId.setNationality(rs.getString("nationality"));
					designerId.setPhonenumber(rs.getString("phonenumber"));
					designerId.setShop_name(rs.getString("shop_name"));
					designerId.setBiz_num(rs.getString("biz_num"));
					designerId.setCity(rs.getString("city"));
					designerId.setDistrict(rs.getString("district"));
					designerId.setAddr_detail(rs.getString("addr_detail"));
					designerId.setCreated_at(rs.getTimestamp("created_at"));
					designerId.setUpdated_at(rs.getTimestamp("updated_at"));
					
					return designerId;
				}
			
			}
		} catch (Exception e) {
			System.err.println("회원 정보 번호로 조회 시 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int signup(DesignerDTO designer) {
		int result = 0;
		
		String sql = " INSERT INTO designer("
				+ "no, id, password, email, full_name, birth, gender, nationality, phonenumber, shop_name, biz_num, city, district, addr_detail)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		System.out.println(sql);
		try (var con = reservation.utils.DBUtil.getConnection();
			var ps = con.prepareStatement(sql)) {
			
			ps.setInt(1, designer.getNo());
			ps.setString(2, designer.getId());
			ps.setString(3, designer.getPassword());
			ps.setString(4, designer.getEmail());
			ps.setString(5, designer.getFull_name());
			ps.setDate(6, designer.getBirth());
			ps.setString(7, designer.getGender());
			ps.setString(8, designer.getNationality());
			ps.setString(9, designer.getPhonenumber());
			ps.setString(10, designer.getShop_name());
			ps.setString(11, designer.getBiz_num());
			ps.setString(12, designer.getCity());
			ps.setString(13, designer.getDistrict());
			ps.setString(14, designer.getAddr_detail());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("회원 등록 시, 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int mypageupdate(DesignerDTO designerProfile) {
		String sql = " UPDATE designer "
					+ " SET password = ? "
					+ " 	,email = ?"
					+ "		,full_name = ? "
					+ "		,birth = ?"
					+ " 	,gender = ?"
					+ "		,nationality = ?"
					+ "		,phonenumber = ?"
					+ "		,shop_name = ?"
					+ " 	,biz_num = ?"
					+ " 	,city = ?"
					+ " 	,district = ?"
					+ " 	,addr_detail = ?"
					+ " WHERE id = ?";
		int result = 0;
		
		try (var con = reservation.utils.DBUtil.getConnection();
			var ps = con.prepareStatement(sql)) {
			
			ps.setString(1, designerProfile.getPassword());
			ps.setString(2, designerProfile.getEmail());
			ps.setString(3, designerProfile.getFull_name());
			ps.setDate(4, designerProfile.getBirth());
			ps.setString(5, designerProfile.getGender());
			ps.setString(6, designerProfile.getNationality());
			ps.setString(7, designerProfile.getPhonenumber());
			ps.setString(8, designerProfile.getShop_name());
			ps.setString(9, designerProfile.getBiz_num());
			ps.setString(10, designerProfile.getCity());
			ps.setString(11, designerProfile.getDistrict());
			ps.setString(12, designerProfile.getAddr_detail());
			ps.setString(13,  designerProfile.getId());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("회원 정보 업데이트 시 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	
	public String findByNameAndEmail(String name, String email) {

	    String sql = "SELECT id "
	               + "FROM designer "
	               + "WHERE full_name = ? AND email = ?";

	    try (var con = reservation.utils.DBUtil.getConnection();
	         var ps = con.prepareStatement(sql)) {

	        ps.setString(1, name);
	        ps.setString(2, email);

	        try (var rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString("id");
	            }
	        }

	    } catch (Exception e) {
	        System.err.println("ID 찾기 불가능 시 예외 발생!");
	        e.printStackTrace();
	    }
	    return "";
	}
	
	

}

