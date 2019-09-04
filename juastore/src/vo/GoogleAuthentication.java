package vo;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

public class GoogleAuthentication extends Authenticator{
	PasswordAuthentication passAuth;
	
	public GoogleAuthentication() {
		passAuth=new PasswordAuthentication("shati1910@gmail.com","olepyzjbscujbzln");
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}
