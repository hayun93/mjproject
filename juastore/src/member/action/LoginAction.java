package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.LoginService;
import vo.ActionForward;
import vo.Member;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id").trim();
		String password=request.getParameter("password").trim();
		
		LoginService loginSVC=new LoginService();
		Member member=loginSVC.memberLogin(id);
		ActionForward forward=null;
		
		if(id==null||id.equals("")||password==null||password.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α��ο� �����Ͽ����ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			if(member==null) {
				response.setContentType("text/html;charset=UTF-8");
				HttpSession session = request.getSession();
				session.getAttribute("id");
				session.getAttribute("password");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('���̵� �������� �ʽ��ϴ�.')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				if(password.equals(member.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					forward=new ActionForward();
					forward.setPath("./main.jsp");
					forward.setRedirect(false);
					
				}else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('��й�ȣ�� Ʋ���ϴ�.')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
		}
		
		
		return forward;
	}
	
}
