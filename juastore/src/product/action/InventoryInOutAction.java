package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import product.svc.InventoryInOutSvc;
import vo.ActionForward;

public class InventoryInOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null||!session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			if(request.getParameter("product_code").equals("")||request.getParameter("amount").equals("")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정보를 입력해주세요.')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				String product_code = request.getParameter("product_code");
				String state = request.getParameter("state");
				int amount = Integer.parseInt(request.getParameter("amount"));
				
				InventoryInOutSvc inventoryInOutSvc = new InventoryInOutSvc();
				boolean isSuccess = inventoryInOutSvc.inventoryInOut(product_code,state,amount);
				
				if(isSuccess) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('등록되었습니다.')");
					out.println("location.href='/juastore/productDetail.pro?product_code=" + product_code + "'");
					out.println("</script>");
				}else {
					request.setAttribute("product_code", product_code);
					forward = new ActionForward("/productDetail.pro",false);
				}
			}
		}
		
		return forward;
	}

}
