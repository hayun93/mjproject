package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSVC;
import vo.ActionForward;
import vo.Member;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		HttpSession session = request.getSession();

		if(session.getAttribute("id")==null || !((String)session.getAttribute("id")).equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인 하세요')");
			out.println("location.href='loginForm.mem'");
			out.println("</script>");
		}else {
			String user_id = request.getParameter("user_id");

			MemberInfoSVC memberInfoSVC = new MemberInfoSVC();
			Member member = memberInfoSVC.getMember(user_id);
			
			request.setAttribute("member", member);
			
			forward = new ActionForward();
			forward.setPath("memberListInfo.jsp?id="+user_id);
		}
		
		return forward;
	}

}
