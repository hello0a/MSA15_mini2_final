package reservation.service;

import reservation.dao.UserDAO;
import reservation.dto.UserDTO;
import reservation.utils.PasswordUilts;

public class UserServiceImpl implements UserService {

	private UserDAO userDao = new UserDAO();
	// �α���
	@Override
	public UserDTO login (UserDTO userDto) {
		// 1. ȸ������ �� ���̵� Ȯ��
		String username = userDto.getId();
		UserDTO selectedUser = userDao.select(username);
		
		if(selectedUser == null) {
			return null;
		}
		
		String loginPassword = userDto.getPassword();
		String password= selectedUser.getPassword();
		
		boolean check = PasswordUilts.check(loginPassword, password);
		
		if(!check)
			return null;
		
		return selectedUser;
	}
	// ���̵�ã��
	@Override
	public String findByNameAndEmail(String name, String email) {		
		return userDao.findByNameAndEmail(name, email);
	}
	// ȸ������
	@Override
	public int signup(UserDTO user) {
		
		String encodedPassword = PasswordUilts.encoded(user.getPassword());
		user.setPassword(encodedPassword);
		
		// 회원정보 등록 요청
		int result = userDao.signup(user);
		if( result > 0 ) System.out.println("회원 가입 성공!");
		else			 System.out.println("회원 가입 실패!");
		return result;

	}
	
	// ���������� ȸ�� ���� ��ȸ
	@Override
	public UserDTO getUser(String profile) {
		// TODO Auto-generated method stub
		if (profile==null || profile.isEmpty()) {
			return null;
		}
		UserDTO userDto = userDao.select(profile);
		return userDto;
	}
	
	// ���������� ȸ�� ���� ����
	@Override
	public int mypageupdate(UserDTO user) {
		// TODO Auto-generated method stub
		return userDao.mypageupdate(user);
	}
	
	@Override
	public String findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByName(name);
	}
	

	
}
