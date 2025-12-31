package reservation.dao;


import reservation.dto.UserDTO;


	public class UserDAO extends JDBConnection {

		public UserDTO select(String id) {
			String sql = "SELECT * "
							+ " FROM users"
							+ " WHERE id = ? ";
			UserDTO result = null;
			
			try (var con = reservation.utils.DBUtil.getConnection();
				var ps = con.prepareStatement(sql)){
				ps.setString(1, id);
				
				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						UserDTO userId = new UserDTO();
						
						userId.setNo(rs.getInt("no"));
						userId.setId(rs.getString("id"));
						userId.setPassword(rs.getString("password"));
						userId.setEmail(rs.getString("email"));
						userId.setFull_name(rs.getString("full_name"));
						userId.setBirth(rs.getDate("birth"));
						userId.setGender(rs.getString("gender"));
						userId.setNationality(rs.getString("nationality"));
						userId.setPhonenumber(rs.getString("phonenumber"));
						userId.setCreated_at(rs.getTimestamp("created_at"));
						userId.setUpdated_at(rs.getTimestamp("updated_at"));
						
						return userId;
					}
				
				}
			} catch (Exception e) {
				System.err.println("회원 정보 번호로 조회 시 예외 발생");
				e.printStackTrace();
			}
			return result;
		}
		
		public int signup(UserDTO user) {
			int result = 0;
			String sql = " INSERT INTO users(no, id, password, email, full_name, birth, gender, nationality, phonenumber)"
						+ " VALUES (?, ? ,? ,? ,? ,? ,? ,? ,?)" ;
			
			try (var con = reservation.utils.DBUtil.getConnection();
				var ps = con.prepareStatement(sql)) {
				
				ps.setInt(1, user.getNo()); 
				ps.setString(2, user.getId());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getFull_name());
				ps.setDate(6, user.getBirth());
				ps.setString(7, user.getGender());
				ps.setString(8, user.getNationality());
				ps.setString(9, user.getPhonenumber());
				
				return ps.executeUpdate();
				
			} catch (Exception e) {
				System.err.println("회원 등록 시, 예외 발생!");
				e.printStackTrace();
			}
			return result;
		}
		
		public int mypageupdate(UserDTO mypageuser) {
			String sql = " UPDATE users "
						+ " SET password = ? "
						+ "		,full_name = ? "
						+ "		,birth = ?"
						+ " 	,email = ?"
						+ "		,phonenumber = ?"
						+ " 	,gender = ?"
						+ " 	,nationality = ?"
						+ " WHERE id = ?";
			int result = 0;
			
			try (var con = reservation.utils.DBUtil.getConnection();
				var ps = con.prepareStatement(sql)) {
				
				ps.setString(1, mypageuser.getPassword());
				ps.setString(2, mypageuser.getFull_name());
				ps.setDate(3, mypageuser.getBirth());
				ps.setString(4, mypageuser.getEmail());
				ps.setString(5, mypageuser.getPhonenumber());
				ps.setString(6, mypageuser.getGender());
				ps.setString(7, mypageuser.getNationality());
				ps.setString(8,  mypageuser.getId());
				
				return ps.executeUpdate();
				
			} catch (Exception e) {
				System.err.println("회원 정보 수정 시 예외 발생!");
				e.printStackTrace();
			}
			return result;
		}

		public String findByNameAndEmail(String name, String email) {

		    String sql = "SELECT id "
		               + "FROM users "
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
		        System.err.println("ID 찾기 시 예외 발생!");
		        e.printStackTrace();
		    }

		    return "";
		}

		public String findByName(String name) {
			String sql = "SELECT id "
		               + "FROM users "
		               + "WHERE full_name = ?";

		    try (var con = reservation.utils.DBUtil.getConnection();
		         var ps = con.prepareStatement(sql)) {

		        ps.setString(1, name);

		        try (var rs = ps.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("id");
		            }
		        }

		    } catch (Exception e) {
		        System.err.println("�̸� ��ȸ ��, ���� �߻�!");
		        e.printStackTrace();
		    }
		    return null;
		}

		
		
	}



