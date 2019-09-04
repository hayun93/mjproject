package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import product.svc.ProductDetailSvc;
import vo.ActionForward;
import vo.ProductInventoryView;

public class ProductModFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		HttpSession session = request.getSession();
		
		if (session.getAttribute("id")==null||!session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			String product_code = request.getParameter("product_code");
			ProductDetailSvc productDetailSvc = new ProductDetailSvc();
			ProductInventoryView product = productDetailSvc.getProduct(product_code);
			
			String type=product_code.substring(0, 2);
			String num=product_code.substring(2,3);
			String size=product_code.substring(5,2);
			String color=product_code.substring(6);
			
			request.setAttribute("type", type);
			request.setAttribute("num", num);
			request.setAttribute("size", size);
			request.setAttribute("color", color);
			request.setAttribute("product", product);
			forward=new ActionForward("/product/productModForm.jsp",false);
		}
		
		return forward;
	}

}
