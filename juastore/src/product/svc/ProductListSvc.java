package product.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.ProductInventoryView;

public class ProductListSvc {

	public int getProductListCount(String product_code) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int listCount = productDAO.getProductListCount(product_code);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ProductInventoryView> getProductList(String product_code, int page, int limit) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		ArrayList<ProductInventoryView> productList = productDAO.getProductList(product_code, page, limit);
		
		close(con);
		
		return productList;
	}
	
}
