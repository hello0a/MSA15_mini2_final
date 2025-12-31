package board.DTO;

import java.sql.Timestamp;

public class boardDTO {
    private int no;
    private String title;
    
    
    
    private int userNo;
    private String content;
    private String writer;
    private int designerNo;   
    private String shopName;
    private Timestamp createdAt;

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public int getDesignerNo() { return designerNo; }
    public void setDesignerNo(int designerNo) { this.designerNo = designerNo; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
