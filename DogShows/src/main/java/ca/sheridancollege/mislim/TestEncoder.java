package ca.sheridancollege.mislim;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {
	public static void main(String[] args) {
	String password = "123";
	String encryptedpassword = encryptPassword(password);
	System.out.println(encryptedpassword);
	encryptedpassword = encryptPassword(password);
	System.out.println(encryptedpassword);
	//System.out.println("================================");
	//System.out.println(isMatch("123","$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl."));
	}
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
		}
	
//	public static boolean isMatch(String password,String hash) {
//		BCryptPasswordEncoder bcryt = new BCryptPasswordEncoder();
//		if(bcryt.matches("password",hash))
//			return true;
//		return false;
//	}
	
}
