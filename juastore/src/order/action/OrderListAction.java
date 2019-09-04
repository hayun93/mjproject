package order.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import order.svc.OrderListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.Pro_order;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		ActionForward forward=null;
		
		
		if(session.getAttribute("id")==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("location.href='/loginForm.mem'");
			out.println("</script>");
		}else {
			
			String id=(String)session.getAttribute("id");
			int page=1;
			int limit=10;
			int limitPage=10;
			
			if(request.getParameter("page")!=null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			OrderListSvc orderListSvc = new OrderListSvc();
			int orderListCount = orderListSvc.getOrderListCount();
			ArrayList<Pro_order> orderList = orderListSvc.getOrderList(id,page,limit);
			
			int maxPage=(int)((double)orderListCount/limit+0.95);
			int startPage=(((int)((double)page/limitPage + 0.9))-1)*limitPage+1;
			int endPage=startPage+limitPage-1;
			if(endPage>maxPage) endPage=maxPage;
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(orderListCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("orderList", orderList);
			
			forward=new ActionForward("/order/myOrderList.jsp",false);
		}
		
		return forward;
	}

}
