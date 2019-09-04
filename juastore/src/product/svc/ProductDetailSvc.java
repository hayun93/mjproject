package product.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.Inventory;
import vo.ProductInventoryView;

public class ProductDetailSvc {

	public ProductInventoryView getProduct(String product_code) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		ProductInventoryView product_view = productDAO.selectProduct(product_code);
		
		close(con);
		
		return product_view;
	}

	public ArrayList<Inventory> getInventory(String product_code,int page, int limit,int inventoryYear, int inventoryMonth) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		ArrayList<Inventory> inventoryList = productDAO.selectInventoryList(product_code,page,limit, inventoryYear, inventoryMonth);
		
		close(con);
		
		return inventoryList;
	}

	public int getInventoryListCount(String product_code,int inventoryYear, int inventoryMonth) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int listCount = productDAO.selectInventoryListCount(product_code,inventoryYear,inventoryMonth);
		
		close(con);
		
		return listCount;
	}

}
