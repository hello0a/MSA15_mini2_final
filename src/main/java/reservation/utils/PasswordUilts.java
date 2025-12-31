package reservation.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUilts {

    // 비밀번호를 해시해서 반환
    public static String encoded(String password) {
        // 기본 gensalt() 대신 라운드 수 지정 가능 (예: 12)
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    // 로그인 시 평문과 해시된 비밀번호 비교
    public static boolean check(String password, String hashedPassword) {
        // hashedPassword가 null이거나 잘못된 포맷이면 false 반환
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            return false;
        }
        return BCrypt.checkpw(password, hashedPassword);
    }
}
