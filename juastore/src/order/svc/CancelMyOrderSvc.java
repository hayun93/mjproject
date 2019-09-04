package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;

public class CancelMyOrderSvc {

	public boolean isMyOrder(int order_num,String user_id) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		boolean isMyOrder = orderDAO.selectIsMyOrder(order_num, user_id);
		
		return isMyOrder;
	}

	public boolean cancelMyOrder(int order_num) {
		// TODO Auto-generated method stub
		Connection con=getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		boolean isSuccess=false;
		
		int isCancel=orderDAO.cancelMyOrder(order_num);
		
		if(isCancel>0) {
			commit(con);
			isSuccess=true;
		}else {
			rollback(con);
		}
		
		return isSuccess;
	}

}
