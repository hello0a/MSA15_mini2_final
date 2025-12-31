package reservation.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUilts {
	
	public static String encoded(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean check(String password, String encodedPassword) {
		return BCrypt.checkpw(password, encodedPassword);
	}
}
