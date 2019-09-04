package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.MemberJoinSVC;
import vo.ActionForward;
import vo.Member;

public class MemberJoinProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward=null;
		Member member=null;
		String phone=request.getParameter("phone0")+"-"+request.getParameter("phone1")+"-"+request.getParameter("phone2");
		String address=request.getParameter("addr1")+request.getParameter("addr2")+request.getParameter("exaddr");
		member=new Member();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setZip_code(Integer.parseInt(request.getParameter("zip_code")));
		member.setAddress(address);
		member.setPhone(phone);
		MemberJoinSVC memberJoinSVC = new MemberJoinSVC();
		boolean isJoinSuccess = memberJoinSVC.joinMember(member);
		
		if(!isJoinSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력되지 않은 정보가 있습니다. 다시 시도해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
			
		}else{
			forward = new ActionForward();
			forward.setPath("joinProcess.jsp");
		}
		
		return forward;
	}

}
