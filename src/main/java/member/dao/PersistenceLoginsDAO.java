package member.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import member.dto.PersistenceLogins;

/**
 * - username 으로 조회
 * - 등록
 * - 수정
 * - 삭제
 */

// PersistenceLoginsDAO -> DB 담당 클래스
// DAO -> Data Access Object (DB랑 대화만 하는 역할)
// extends JDBConnection -> DB 연결 기능을 이미 만들어둔 클래스를 상속받기
// 즉, JDBConnection (전화기) / DAO (전화 걸어서 말하는 사람)
public class PersistenceLoginsDAO extends JDBConnection{
	// 아이디(username)로 자동로그인 정보 조회
	// - username 주면,
	// - DB에서 해당 유저의 자동로그인 정보를 찾아서
	// - 객체로 만들어 돌려준다.
	public PersistenceLogins selectByUsername(String username) {
		// persistence_logins -> DB 테이블 이름
		// ? -> 나중에 값 넣을 자리
		// 즉, 자리만 예약해 둔 상태
		String sql = " SELECT *"
					+ " FROM persistence_logins"
					+ " WHERE username = ?";
		// 결과를 담을 변수
		// DB 찾으면 담고, 못 찾으면 null 그대로 반환
		PersistenceLogins persistenceLogins = null;
		try {
			// DB에 실제로 물어보는 부분
			// con -> DB 연결
			// prepareStatement -> 이 SQL 문으로 물어볼 준비
			psmt = con.prepareStatement(sql);
			// 첫 번째 ? 자리에, username 값 넣음
			/* 즉, 아래처럼 바뀜
			 * SELECT *
				FROM persistence_logins
				WHERE username = '입력한아이디'
			 */
			psmt.setString(1, username);
			// 실행 + 결과 받기
			// - rs(ResultSet)에 결과 담음
			// - ResultSet -> DB 결과 표
			rs = psmt.executeQuery();
			// 결과가 있다면 객체 생성
			// - 한 줄이라도 있다면 true
			if ( rs.next() ) {
				// 빈 객체 하나 생성
				persistenceLogins = new PersistenceLogins();
				// 핵심!
				// - DB 컬럼 하나씩 꺼내서, 자바 객체에 옮겨 담는 작업
				// - DB에서 자바 객체로 변환
				persistenceLogins.setNo(rs.getInt("no"));
				persistenceLogins.setUuid(rs.getString("id"));
				persistenceLogins.setId(rs.getString("username"));
				persistenceLogins.setToken(rs.getString("token"));
				persistenceLogins.setExpiryDate(rs.getTimestamp("expiry_date"));
				persistenceLogins.setCreatedAt(rs.getTimestamp("created_at"));
				persistenceLogins.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			System.err.println("회원 아이디로 조회 시 예외 발생");
			e.printStackTrace();
		}
		// 찾았으면 객체, 없으면 null
		return persistenceLogins;
	}
	/**
	 * - token 으로 조회
	 * @param token
	 * @return
	 */
	public PersistenceLogins selectByToken(String token) {
		// 위와 동일한 구조, 차이점은 + " WHERE token = ?";
		// - 자동 로그인은 token으로 사용자 식별
		String sql = " SELECT *"
					+ " FROM persistence_logins"
					+ " WHERE token = ?";
		PersistenceLogins persistenceLogins = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, token);
			rs = psmt.executeQuery();
			if ( rs.next() ) {
				persistenceLogins = new PersistenceLogins();
				persistenceLogins.setNo(rs.getInt("no"));
				persistenceLogins.setUuid(rs.getString("id"));
				persistenceLogins.setId(rs.getString("username"));
				persistenceLogins.setToken(rs.getString("token"));
				persistenceLogins.setExpiryDate(rs.getTimestamp("expiry_date"));
				persistenceLogins.setCreatedAt(rs.getTimestamp("created_at"));
				persistenceLogins.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			System.err.println("토큰으로 조회 시 예외 발생");
			e.printStackTrace();
		}
		return persistenceLogins;
	}
	// 자동 로그인 정보 저장 (INSERT)
	// - 자동 로그인 정보를 DB에 저장
	public int insert(member.dto.PersistenceLogins persistenceLogins) {
		// SQL문
		// - 새 줄 하나 추가
		String sql = " INSERT INTO persistence_logins ( id, username, token, expiry_date )"
					+ " VALUES (?, ?, ?, ?)";
		int result = 0;
		try {
			// AUTO_INCREMENT 되는 no 컬럽의 자동생성된 값 가져온다.
			// DB가 자동으로 만들어준 번호(no)를, 자바 코드에서 다시 받아온다는 의미
			// AUTO_INCREMENT?
			// - 새 데이터 INSERT 할 때 no 값을 안 넣어도, DB가 자동으로 번호 붙여줌
			// 자바는 DB 언어를 모르므로, 해당 코드 넣어준것!!
			psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 값 채우기
			// - 객체에서 값 꺼내서 DB에 담기
			psmt.setString(1, persistenceLogins.getUuid());
			psmt.setString(2, persistenceLogins.getId());
			psmt.setString(3, persistenceLogins.getToken());
			// 날짜 처리
			// - 자바 Date 와 DB 날짜 타입은 다름!
			// - DB용 Timestamp로 변환 필요!
			Date expiryDate = persistenceLogins.getExpiryDate();
			psmt.setTimestamp(4, new java.sql.Timestamp(expiryDate.getTime()));
			// 실행
			// - 성공한다면 1, 실패한다면 0
			result = psmt.executeUpdate();
			rs = psmt.getGeneratedKeys();
			if( rs.next() ) {
				int no = rs.getInt(1);
				persistenceLogins.setNo(no);
			}
			
		} catch (Exception e) {
			System.err.println("자동 로그인 데이터 등록 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public int update(PersistenceLogins persistenceLogins ) {
		String sql = " UPDATE persistence_logins "
					+ " SET token = ? "
					+ "     ,username = ? "
					+ "     ,expiry_date = ? "
					+ "     ,updated_at = ? "
					+ " WHERE no = ?";
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,  persistenceLogins.getToken());
			psmt.setString(2,  persistenceLogins.getId());
			Date expiryDate = persistenceLogins.getExpiryDate();
			psmt.setTimestamp(3, new Timestamp(expiryDate.getTime()));
			psmt.setTimestamp(4, new Timestamp(new Date().getTime()));
			psmt.setInt(5, persistenceLogins.getNo());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("자동 로그인 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public int delete(String username) {
		String sql = " DELETE FROM persistence_logins "
					+ " WHERE username = ? ";
		
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, username);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("자동 로그인 삭제 중 예외 발생");
		}
		return result;
	}
	/**
	 * [로그인 성공]
   			↓
		자동 로그인 체크됨
   			↓
		기존 자동 로그인 있음?
   		├─ 있음 → update()
   		└─ 없음 → insert()

		[로그아웃]
   			↓
		delete(username)
	 */
}