package order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import order.svc.CancelMyOrderSvc;
import vo.ActionForward;

public class CancelMyOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int order_num=Integer.parseInt(request.getParameter("order_num"));
		ActionForward forward=null;
		HttpSession session = request.getSession();
		
		CancelMyOrderSvc cancelMyOrderSvc = new CancelMyOrderSvc();
		boolean isMyOrder = cancelMyOrderSvc.isMyOrder(order_num,(String)session.getAttribute("id"));
		System.out.println(isMyOrder);
		if(!isMyOrder) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("location.href='/juastore/main.jsp'");
			out.println("</script>");
		}else {
			boolean isSuccess=cancelMyOrderSvc.cancelMyOrder(order_num);
			if(isSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주문이 취소되었습니다.')");
				out.println("location.href='/juastore/orderDetail.ord?order_num="+order_num+"'");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주문취소에 실패하였습니다.\n 다시 시도해주세요')");
				out.println("location.href='/juastore/orderDetail.ord?order_num="+order_num+"'");
				out.println("</script>");
			}
		}
		
		return forward;
	}

}
