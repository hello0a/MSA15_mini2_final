package member.service;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

import member.dao.DesignerDAO;
import member.dao.JDBConnection;
import member.dto.DesignerDTO;
import member.dto.UserDTO;
import member.utils.PasswordUilts;

public class DesignerServiceImpl implements DesignerService{

	private DesignerDAO designerDao = new DesignerDAO();
	// �α���
	@Override
	public DesignerDTO login (DesignerDTO designerDto) {
		// 1. ȸ������ ���̵� Ȯ��
		String designername = designerDto.getId();
		DesignerDTO selectedDesigner = designerDao.select(designername);
		
		if(selectedDesigner == null) {
			return null;
		}
		
		String loginPassword = designerDto.getPassword();
		String password= selectedDesigner.getPassword();
		
		boolean check = PasswordUilts.check(loginPassword, password);
		
		if(!check)
			return null;
		
		return selectedDesigner;
	}

	// ȸ������
	@Override
	public int signup(DesignerDTO designer) {
		// 1. ȸ������ ���� �ʼ� �׸�
		String encodedPassword = PasswordUilts.encoded(designer.getPassword());
		designer.setPassword(encodedPassword);
		
		// 회원정보 등록 요청
		int result = designerDao.signup(designer);
		if( result > 0 ) System.out.println("회원 가입 성공!");
		else			 System.out.println("회원 가입 실패!");
		return result;
	}

	// ȸ�� ���� ��ȸ
	@Override
	public int mypageupdate(DesignerDTO designerProfile) {
		return designerDao.mypageupdate(designerProfile);
	}

	// ���̵� ã��
	@Override
	public String findByNameAndEmail(String name, String email) {
		return designerDao.findByNameAndEmail(name, email);
	}

	// ���������� ȸ�� ���� ��ȸ
	@Override
	public DesignerDTO getDesigner(String profile) {
		// TODO Auto-generated method stub
		if (profile==null || profile.isEmpty())	{
			return null;
		}
		DesignerDTO designerDto = designerDao.select(profile);
		return designerDto;
	}
	

	 @Override
	    public List<DesignerDTO> findAll() {
	        return designerDao.findAll();
	    }
	
	

}
