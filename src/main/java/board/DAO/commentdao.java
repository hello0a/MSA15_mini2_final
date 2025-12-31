package board.DAO;

import board.DTO.commentDTO;

public class commentdao {

	public commentDTO findByBoardNo(int boardNo) {

	    commentDTO dto = null;

	    String sql =
	        "SELECT c.no, c.board_no, c.designer_no, c.content, c.created_at, " +
	        "       d.full_name AS designer_name " +
	        "FROM comment c " +
	        "JOIN designer d ON c.designer_no = d.no " +
	        "WHERE c.board_no = ?";

	    JDBConnection db = new JDBConnection();

	    try {
	        db.psmt = db.con.prepareStatement(sql);
	        db.psmt.setInt(1, boardNo);
	        db.rs = db.psmt.executeQuery();

	        if (db.rs.next()) {
	            dto = new commentDTO();
	            dto.setNo(db.rs.getInt("no"));
	            dto.setBoardNo(db.rs.getInt("board_no"));
	            dto.setDesignerNo(db.rs.getInt("designer_no"));
	            dto.setDesignerName(db.rs.getString("designer_name")); 
	            dto.setContent(db.rs.getString("content"));
	            dto.setCreatedAt(db.rs.getTimestamp("created_at"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }

	    return dto;
	}


	public void insert(commentDTO dto) {
	    String sql = "INSERT INTO comment (board_no, designer_no, content) VALUES (?, ?, ?)";

	    JDBConnection db = new JDBConnection();
	    try {
	        db.psmt = db.con.prepareStatement(sql);
	        db.psmt.setInt(1, dto.getBoardNo());
	        db.psmt.setInt(2, dto.getDesignerNo());
	        db.psmt.setString(3, dto.getContent());
	        db.psmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}
	

	public int delete(int no) {
	    int result = 0;
	    String sql = "DELETE FROM comment WHERE no = ?";

	    JDBConnection db = new JDBConnection();
	    try {
	        db.psmt = db.con.prepareStatement(sql);
	        db.psmt.setInt(1, no);
	        result = db.psmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return result;
	}


}
