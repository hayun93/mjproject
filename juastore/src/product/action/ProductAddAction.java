package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import product.svc.ProductAddSvc;
import vo.ActionForward;

public class ProductAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ActionForward forward=null;
		String etc=null;
		if(session.getAttribute("id")==null||!session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		int fileSize=5*1024*1024;
		String realFolder="";
		String saveFolder="/productImage";
		realFolder=request.getServletContext().getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"utf-8",new DefaultFileRenamePolicy());
		System.out.println(multi.getParameter("product_price"));
		System.out.println(multi.getParameter("size"));
		System.out.println(multi.getParameter("color"));
		System.out.println(multi.getParameter("product_image"));
		System.out.println(multi.getParameter("inventory"));
		if(multi.getParameter("product_name")==null||multi.getParameter("product_num")==null||
				multi.getParameter("product_price")==null||multi.getParameter("size")==null||
				multi.getParameter("color")==null||multi.getOriginalFileName((String)multi.getFileNames().nextElement())==null||multi.getParameter("inventory")==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('하나도 빠트림 없이 입력해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			
			
			
			String product_code=multi.getParameter("type")+multi.getParameter("product_num")+
					multi.getParameter("size")+multi.getParameter("color");
			String product_name=multi.getParameter("product_name");
			int product_price=Integer.parseInt(multi.getParameter("product_price"));
			String image=multi.getOriginalFileName((String)multi.getFileNames().nextElement());
			if(multi.getParameter("etc")!=null)
				etc=multi.getParameter("etc");

			int inventory_amount = Integer.parseInt(multi.getParameter("inventory"));
			
			ProductAddSvc productAddSvc=new ProductAddSvc();
			boolean addProductSuccess=productAddSvc.addProduct(product_code,product_name,product_price,image,etc);
			
			if(!addProductSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('상품등록실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			boolean addInventorySuccess=productAddSvc.addInventory(product_code,inventory_amount);
			
			if(!addInventorySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('재고등록실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			request.setAttribute("product_code", product_code);
			forward=new ActionForward("/productDetail.pro",false);
			
		}
		
		return forward;
	}

}
