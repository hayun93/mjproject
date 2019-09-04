package product.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import product.svc.ProductDetailSvc;
import vo.ActionForward;
import vo.Inventory;
import vo.PageInfo;
import vo.ProductInventoryView;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String product_code = request.getParameter("product_code");
		if(request.getAttribute("product_code")!=null) {
			product_code=(String)request.getAttribute("product_code");
		}
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
			ProductDetailSvc productDetailSvc = new ProductDetailSvc();
			ProductInventoryView product_view = productDetailSvc.getProduct(product_code);
			
			int inventoryYear=0;
			if(request.getParameter("inventoryYear")!=null)
				inventoryYear = Integer.parseInt(request.getParameter("inventoryYear"));
			int inventoryMonth =0;
			if(request.getParameter("inventoryMonth")!=null)
				inventoryMonth=Integer.parseInt(request.getParameter("inventoryMonth"));
			
			int page = 1;
			int limit = 10;
			int limitPage = 10;
			
			if (request.getParameter("page") != null) 
				page = Integer.parseInt(request.getParameter("page"));
			
			int inventoryListCount = productDetailSvc.getInventoryListCount(product_code,inventoryYear,inventoryMonth);
			
			int maxPage=(int)((double)inventoryListCount/limit+0.95);
			int startPage=(((int)((double)page/limitPage+0.9))-1)*limitPage+1;
			int endPage = startPage+limitPage-1;
			if(endPage>maxPage)
				endPage=maxPage;
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(inventoryListCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			
			
			ArrayList<Inventory> inventoryList = productDetailSvc.getInventory(product_code,page,limit,inventoryYear,inventoryMonth);
	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("product", product_view);
			request.setAttribute("inventoryList", inventoryList);
			forward= new ActionForward("/product/productDetail.jsp",false);
		}
		return forward;
	}

}
