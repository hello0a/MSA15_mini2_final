package reserve.DTO;

import java.sql.Timestamp;

public class ReservationListDTO {
    private int no;
    private Timestamp date;
    private Timestamp time;
    private String phoneNumber;
    private int price;
    private String designerName;
    private String serviceName;

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }
    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }
    public Timestamp getTime() { return time; }
    public void setTime(Timestamp time) { this.time = time; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDesignerName() { return designerName; }
    public void setDesignerName(String designerName) { this.designerName = designerName; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
}
