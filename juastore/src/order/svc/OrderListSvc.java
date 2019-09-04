package order.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.Pro_order;
import static db.JdbcUtil.*;

public class OrderListSvc {

	public ArrayList<Pro_order> getOrderList(String id, int page, int limit) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Pro_order> orderList = orderDAO.selectOrderList(id, page, limit);
		
		close(con);
		
		return orderList;
	}

	public int getOrderListCount() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int orderListCount=orderDAO.selectOrderListCount();
		
		close(con);
		
		return orderListCount;
	}
	
}
