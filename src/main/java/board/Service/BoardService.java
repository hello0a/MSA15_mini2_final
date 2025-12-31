package board.Service;

import java.util.List;
import board.DAO.boarddao;
import board.DTO.boardDTO;

public class BoardService {

    private boarddao dao = new boarddao();

    public List<boardDTO> getBoardList() {
        return dao.findAll(null);
    }
}
