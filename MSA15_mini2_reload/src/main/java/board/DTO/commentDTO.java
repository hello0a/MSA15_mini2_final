package board.DTO;

import java.sql.Timestamp;

public class commentDTO {

    private int no;
    private int boardNo;
    private int designerNo;
    private String designerName;   // ✅ 추가
    private String content;
    private Timestamp createdAt;

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getBoardNo() { return boardNo; }
    public void setBoardNo(int boardNo) { this.boardNo = boardNo; }

    public int getDesignerNo() { return designerNo; }
    public void setDesignerNo(int designerNo) { this.designerNo = designerNo; }

    public String getDesignerName() { return designerName; }
    public void setDesignerName(String designerName) { this.designerName = designerName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
