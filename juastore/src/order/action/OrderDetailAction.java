package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.OrderDetailSvc;
import vo.ActionForward;
import vo.OrderView;
import vo.Pro_order;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		
		int order_num=Integer.parseInt(request.getParameter("order_num"));
		
		OrderDetailSvc orderDetailSvc=new OrderDetailSvc();
		Pro_order order = orderDetailSvc.getOrder(order_num);
		ArrayList<OrderView> orderItemList = orderDetailSvc.getItemList(order_num);
		
		request.setAttribute("order", order);
		request.setAttribute("itemList", orderItemList);
		
		forward = new ActionForward("/order/myOrderDetail.jsp",false);
		
		return forward;
	}

}
