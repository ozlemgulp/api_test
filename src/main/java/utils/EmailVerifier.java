package utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailVerifier {

	public static boolean isEmailAdressValid(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		
	}

}
