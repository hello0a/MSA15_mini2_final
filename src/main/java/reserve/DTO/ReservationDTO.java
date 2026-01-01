package reserve.DTO;

import java.sql.Date;

public class ReservationDTO {

	private int no;
    private int reservationId;
    private int userNo;
    private int designerNo;
    private String styleName;
	private int styleNo;
    private Date reserveDate;
    private String reserveTime;
    private String etc;
    private String phoneNumber;
    private int price;

    // 서비스 (시술명) 저장용 필드
    // 선택한 시술 문자열을 담습니다 (예: "컷 + 펌")
    private String services;

    // 기본 생성자
    public ReservationDTO() { }

    // getter & setter
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserNo() {
        return userNo;
    }
    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getDesignerNo() {
        return designerNo;
    }
    public void setDesignerNo(int designerNo) {
        this.designerNo = designerNo;
    }
    public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

    public int getStyleNo() {
        return styleNo;
    }
    public void setStyleNo(int styleNo) {
        this.styleNo = styleNo;
    }

    public Date getReserveDate() {
        return reserveDate;
    }
    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveTime() {
        return reserveTime;
    }
    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getServices() {
        return services;
    }
    public void setServices(String services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId=" + reservationId +
                ", no=" + no +
                ", userNo=" + userNo +
                ", designerNo=" + designerNo +
                ", styleNo=" + styleNo +
                ", reserveDate=" + reserveDate +
                ", reserveTime='" + reserveTime + '\'' +
                ", etc='" + etc + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", styleName='" + styleName + '\'' +
                ", price=" + price +
                ", services='" + services + '\'' +
                '}';
    }

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
