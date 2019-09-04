package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.OrderView;
import vo.Pro_order;
import static db.JdbcUtil.*;

public class OrderDAO {
	private static OrderDAO orderDAO;
	private Connection con;
	
	public static OrderDAO getInstance() {
		// TODO Auto-generated method stub
		if(orderDAO==null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con=con;
	}
	
	//주문수
	public int selectOrderListCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from pro_order";
		int listCount=0;
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("selectOrderListCount 에러 : " +e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	//주문목록
	public ArrayList<Pro_order> selectOrderList(String id, int page, int limit) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from pro_order where user_id=? order by order_num limit ?,?";
		int startrow = (page-1) *limit;
		
		ArrayList<Pro_order> orderList=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, limit);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				orderList=new ArrayList<Pro_order>();
				
				do {
					Pro_order order= new Pro_order();
					order.setOrder_num(rs.getInt("order_num"));
					order.setAddress(rs.getString("address"));
					order.setOrder_date(rs.getDate("order_date"));
					order.setOrder_state(rs.getString("order_state"));
					order.setPay(rs.getInt("pay"));
					orderList.add(order);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("selectOrderList 에러 :"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return orderList;
	}
	//주문한 상품목록
	public ArrayList<OrderView> selectItemList(int order_num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from order_view where order_num=?";
		ArrayList<OrderView> itemList=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				itemList=new ArrayList<OrderView>();
				do {
					OrderView item=new OrderView();
					item.setProduct_name(rs.getString("product_name"));
					item.setAmount(rs.getInt("amount"));
					item.setPay(rs.getInt("pay"));
					item.setProduct_price(rs.getInt("product_price"));
					itemList.add(item);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("selectItemList 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return itemList;
	}
	//주문 상세
	public Pro_order selectOrder(int order_num) {
		// TODO Auto-generated method stub
		Pro_order order = null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from pro_order where order_num=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				order = new Pro_order();
				order.setAddress(rs.getString("address"));
				order.setOrder_date(rs.getDate("order_date"));
				order.setOrder_num(rs.getInt("order_num"));
				order.setOrder_state(rs.getString("order_state"));
				order.setPay(rs.getInt("pay"));
				order.setPhone(rs.getString("phone"));
				order.setReceive_name(rs.getString("receive_name"));
				order.setZip_code(rs.getInt("zip_code"));
			}
		}catch(Exception e) {
			System.out.println("selectOrder에러 : " +e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return order;
	}
	//주문자 확인
	public boolean selectIsMyOrder(int order_num, String user_id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from pro_order where order_num=? and user_id=?";
		boolean isMyOrder=false;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			pstmt.setString(2,user_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				isMyOrder=true;
			}
		}catch(Exception e) {
			System.out.println("selectIsMyOrder에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return isMyOrder;
	}
	public int cancelMyOrder(int order_num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		String sql="update pro_order set order_state='주문취소' where order_num=?";
		int isCancel=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			isCancel=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("cancelMyOrder에러 : " +e);
		}finally {
			close(pstmt);
		}
		return isCancel;
	}
	
	
}
