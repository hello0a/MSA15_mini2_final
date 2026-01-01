package reserve.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import reserve.DAO.ReservationDAO;
import reserve.DTO.ReservationDTO;
import reserve.DTO.ReservationListDTO;
import reserve.DTO.StyleDTO;

public class ReservationService {

    private ReservationDAO dao = new ReservationDAO();

    // 예약 등록
    public boolean addReservation(ReservationDTO reservation) {
		/*
		 * int price = dao.getStylePrice(reservation.getStyleNo());
		 * reservation.setPrice(price);
		 */
        if (dao.isAlreadyReserved(
                reservation.getDesignerNo(),
                reservation.getReserveDate(),
                reservation.getReserveTime(),
                reservation.getStyleNo())) {
            return false;
        }
        return dao.insertReservation(reservation) > 0;
    }

    // 예약 가능한 시간 조회
    public List<String> getAvailableTimes(int designerNo, Date reserveDate) {
        List<String> list = dao.getAvailableTimes(designerNo, reserveDate);
        return list != null ? list : new ArrayList<>();
    }

    // 시술 리스트 조회
    public List<StyleDTO> getAllStyles() {
        List<StyleDTO> list = dao.getAllStyles();
        return list != null ? list : new ArrayList<>();
    }
    
    public List<ReservationDTO> select(int userNo) {
    	return dao.select(userNo);
    }

    public List<ReservationListDTO> selectWithDesigner(int userNo) {
    	return dao.selectWithDesigner(userNo);
    }

    public List<ReservationListDTO> selectWithUser(int designerNo) {
    	return dao.selectWithUser(designerNo);
    }
    
    public ReservationListDTO selectByNo(int reserveNo) {
    	return dao.selectByNo(reserveNo);
    }
    
	public int reserveEtcUpdate(int no, String etc) {
		return dao.reserveEtcUpdate(no, etc);
	}
}
