package reservation.service;

import reservation.dto.UserDTO;

public interface UserService {
	// 로그인
	UserDTO login(UserDTO user);
	// 
	String findByNameAndEmail(String name, String email);
	// 
	String findByName(String name);
	// 회원가입
	int signup(UserDTO user);
	// 
	UserDTO getUser(String profile);
	// 
	int mypageupdate(UserDTO mypageUser);
	
}
