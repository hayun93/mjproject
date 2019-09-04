package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrderView;
import vo.Pro_order;

public class OrderDetailSvc {


	public Pro_order getOrder(int order_num) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		Pro_order order=orderDAO.selectOrder(order_num);
		
		close(con);
		
		return order;
	}
	public ArrayList<OrderView> getItemList(int order_num) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrderView> itemList = orderDAO.selectItemList(order_num);
		
		close(con);
		
		return itemList;
	}

}
