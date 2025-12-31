package board.DAO;

import java.util.ArrayList;
import java.util.List;
import board.DTO.boardDTO;
import java.sql.*;

public class boarddao {

    public List<boardDTO> findAll(String designerNoFilter) {
        List<boardDTO> list = new ArrayList<>();
        String sql = "SELECT b.no, b.title, b.content, u.full_name AS writer, d.no AS designerNo, d.shop_name, b.created_at " +
                     "FROM board b " +
                     "JOIN users u ON b.user_no = u.no " +
                     "JOIN designer d ON b.designer_no = d.no ";
        if (designerNoFilter != null && !designerNoFilter.isEmpty()) {
            sql += " WHERE d.no = ? ";
        }
        sql += " ORDER BY b.no DESC";

        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            if (designerNoFilter != null && !designerNoFilter.isEmpty()) {
                db.psmt.setInt(1, Integer.parseInt(designerNoFilter));
            }
            db.rs = db.psmt.executeQuery();
            while (db.rs.next()) {
                boardDTO dto = new boardDTO();
                dto.setNo(db.rs.getInt("no"));
                dto.setTitle(db.rs.getString("title"));
                dto.setContent(db.rs.getString("content"));
                dto.setWriter(db.rs.getString("writer"));
                dto.setDesignerNo(db.rs.getInt("designerNo"));
                dto.setShopName(db.rs.getString("shop_name"));
                dto.setCreatedAt(db.rs.getTimestamp("created_at"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public boardDTO findByNo(int no) {
        boardDTO dto = null;
        String sql = "SELECT b.no, b.title, b.content, b.user_no, u.full_name AS writer, d.no AS designerNo, d.shop_name, b.created_at " +
                     "FROM board b " +
                     "JOIN users u ON b.user_no = u.no " +
                     "JOIN designer d ON b.designer_no = d.no " +
                     "WHERE b.no = ?";

        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            db.psmt.setInt(1, no);
            db.rs = db.psmt.executeQuery();
            if (db.rs.next()) {
                dto = new boardDTO();
                dto.setNo(db.rs.getInt("no"));
                dto.setTitle(db.rs.getString("title"));
                dto.setContent(db.rs.getString("content"));
                dto.setUserNo(db.rs.getInt("user_no")); // ← 여기 중요
                dto.setWriter(db.rs.getString("writer"));
                dto.setDesignerNo(db.rs.getInt("designerNo"));
                dto.setShopName(db.rs.getString("shop_name"));
                dto.setCreatedAt(db.rs.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return dto;
    }


    public boolean insert(boardDTO board) {
        String sql = "INSERT INTO board(title, content, user_no, designer_no, created_at) VALUES(?, ?, ?, ?, NOW())";
        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            db.psmt.setString(1, board.getTitle());
            db.psmt.setString(2, board.getContent());
            db.psmt.setInt(3, board.getUserNo()); // 로그인 유저 번호 사용
            db.psmt.setInt(4, board.getDesignerNo());
            int result = db.psmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }


    public boolean update(boardDTO board) {
        String sql = "UPDATE board SET title=?, content=?, designer_no=? WHERE no=?";
        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            db.psmt.setString(1, board.getTitle());
            db.psmt.setString(2, board.getContent());
            db.psmt.setInt(3, board.getDesignerNo());
            db.psmt.setInt(4, board.getNo());
            int result = db.psmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

    public boolean delete(int no) {
        String sql = "DELETE FROM board WHERE no=?";
        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            db.psmt.setInt(1, no);
            int result = db.psmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

    public List<boardDTO> getShopList() {
        List<boardDTO> shopList = new ArrayList<>();
        String sql = "SELECT no, shop_name FROM designer ORDER BY shop_name ASC";

        JDBConnection db = new JDBConnection();
        try {
            db.psmt = db.con.prepareStatement(sql);
            db.rs = db.psmt.executeQuery();
            while (db.rs.next()) {
                boardDTO dto = new boardDTO();
                dto.setDesignerNo(db.rs.getInt("no"));
                dto.setShopName(db.rs.getString("shop_name"));
                shopList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return shopList;
    }
}
