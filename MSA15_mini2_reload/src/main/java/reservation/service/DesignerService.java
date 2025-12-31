package reservation.service;

import java.util.List;

import reservation.dto.DesignerDTO;
import reservation.dto.UserDTO;

public interface DesignerService {
	
		DesignerDTO login(DesignerDTO designer);
			
		// ȸ������ : �Է�(UserDTO) -> ��� (���� �� or boolean)
		int signup(DesignerDTO designer);
		
		// ���������� ȸ�� ���� ���� : �Է�(UserDTO) -> ���(���� ��)
		int mypageupdate(DesignerDTO designerprofile);
		
		// ���̵� ã�� : �̸�, �̸���
		String findByNameAndEmail(String name, String email);
		
		// ȸ�� ���� ��ȸ
		DesignerDTO getDesigner(String profile);
		
		 List<DesignerDTO> findAll();

}
