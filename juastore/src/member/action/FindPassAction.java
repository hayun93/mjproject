package member.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.FindPassSvc;
import vo.ActionForward;
import vo.GoogleAuthentication;

public class FindPassAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		ActionForward forward = null;
		if (userId == null || userId.equals("") || email == null || email.equals("")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디와 이메일을 모두 입력해주세요!')");
			out.println("location.href='/juastore/member/findPass.jsp'");
			out.println("</script>");
		} else {
			FindPassSvc findPassSvc = new FindPassSvc();
			String pass = findPassSvc.getPassword(userId, email);

			request.setAttribute("pass", pass);

			if (!(pass == null || pass.equals("no"))) {
				try {
					Properties properties = System.getProperties();
					properties.put("mail.smtp.starttls.enable", "true");
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.port", "587"); // gmail포트

					Authenticator auth = new GoogleAuthentication();
					Session s = Session.getDefaultInstance(properties, auth);
					Message message = new MimeMessage(s);
					Address sender_address = new InternetAddress("jua@juastore.com");
					Address receiver_address = new InternetAddress(email);
					message.setHeader("content-type", "text/html;charset=UTF-8");
					message.setFrom(sender_address);
					message.addRecipient(Message.RecipientType.TO, receiver_address);
					message.setSubject("주아스토어에 요청하신 비밀번호입니다.");
					message.setContent(userId + "님의 비밀번호는 " + pass + "입니다.", "text/html;charset=utf-8");
					message.setSentDate(new java.util.Date());
					Transport.send(message);
				} catch (Exception e) {
					System.out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
					e.printStackTrace();
				}
			}
			forward = new ActionForward("/member/findPass.jsp", false);
		}
		return forward;
	}

}
