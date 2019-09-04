package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.AddrChangeService;
import vo.ActionForward;
import vo.Member;

public class AddrChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		
		if(request.getParameter("zip_code")!=null && request.getParameter("addr1") != null) {
			response.setContentType("text/html;charset=UTF-8");
			Member member = new Member();
			member.setId(id);
			member.setZip_code(Integer.parseInt(request.getParameter("zip_code")));
			member.setAddress(request.getParameter("addr1")+request.getParameter("addr2")+request.getParameter("exaddr"));
			AddrChangeService addrChangeSvc = new AddrChangeService();
			boolean addrResult= addrChangeSvc.ChAddr(member);
			
			if(!addrResult) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주소 변경에 오류가 발생했습니다. 다시 작성하세요.')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주소변경이 완료되었습니다.')");
				out.println("location.href='modify.mem'");
				if(session.getAttribute("id")=="admin") {
					out.println("location.href='memberList.jsp'");
				}
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주소를 입력해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
