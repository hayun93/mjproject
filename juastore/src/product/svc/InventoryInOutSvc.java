package product.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ProductDAO;

public class InventoryInOutSvc {

	public boolean inventoryInOut(String product_code, String state, int amount) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		boolean isSuccess=false;
		
		int result=productDAO.insertInventory(product_code, amount, state);
		
		if(result>0) {
			isSuccess=true;
			commit(con);
		}else
			rollback(con);
		
		close(con);
		
		return isSuccess;
	}

}
