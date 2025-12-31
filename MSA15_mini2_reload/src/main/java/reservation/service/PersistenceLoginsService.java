package reservation.service;

import reservation.dto.PersistenceLogins;

public interface PersistenceLoginsService {

	// 토큰 등록 (자동 로그인 처음 체크할 때)
	// - 사용자가 로그인하면서 "자동 로그인" 체크
	// - 랜덤 토큰 생성 / DB 저장 / username + token + 만료시간
	// - 놀이공원 자동입장권 처음 발급
	public PersistenceLogins insert(String username);
	
	// 토큰 조회 (아이디)
	// - 이 유저가 이미 자동로그인 토큰이 있는지 확인
	public PersistenceLogins select(String username);
	
	// 토큰 조회 (토큰)
	// - 중요! Filter에서 쿠키 검사할 때
	// - 이 토큰이 DB에 실제 존재하는가?
	public PersistenceLogins selectByToken(String token);
	
	// 토큰 수정
	// - 토큰이 이미 있는데
	// - 새 토큰으로 변경 / 만료시간 연장 / 보안상
	// - 토큰 재발급
	public PersistenceLogins update(String username);
	
	// 토큰 갱신 (없으면 등록, 있으면 수정)
	// 자동 판단! 제일 실무적인 메서드
	// - 내부에서 하는일 (보통)
	// - 토큰 있음? update / 토큰 없음? insert
	// - 자동입장권 : 있으면 연장 / 없으면 새로 발급
	public PersistenceLogins refresh(String username);
	
	// 토큰 유효성 체크 (만료여부 확인)
	// - 토큰 존재? 만료 여부? 조작여부?
	// Filter -> booleanisValid
	// 				= persistenceLoginsService.isValid(token);
	// - true 자동 로그인 기능 / false 다시 로그인 필요
	public boolean isValid(String token);
	
	// 토큰 삭제
	// - 로그아웃할 때
	// - 자동 로그인 토큰 제거
	// - 다음 접속 시 자동 로그인X
	public boolean delete(String username);
}