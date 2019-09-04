package product.svc;

import java.sql.Connection;

import dao.ProductDAO;

import static db.JdbcUtil.*;

public class ProductAddSvc {

	public boolean addProduct(String product_code, String product_name, int product_price, String image, String etc) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		boolean isSuccess=false;
		
		int add = productDAO.insertProduct(product_code,product_name,product_price,image,etc);
		
		if(add>0) {
			isSuccess=true;
			commit(con);
		}else
			rollback(con);
		
		close(con);
		
		return isSuccess;
	}

	public boolean addInventory(String product_code, int inventory_amount) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		boolean isSuccess=false;
		
		int add = productDAO.insertInventory(product_code,inventory_amount,"in");
		int sub = productDAO.insertInventory(product_code,0,"out");
		
		if(add>0 && sub>0) {
			isSuccess=true;
			commit(con);
		}else
			rollback(con);
		
		close(con);
		
		return isSuccess;
	}

}
