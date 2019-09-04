package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Inventory;
import vo.Product;
import vo.ProductInventoryView;

import static db.JdbcUtil.*;

public class ProductDAO {
	public static ProductDAO productDAO;
	private Connection con;
	
	public static ProductDAO getInstance() {
		if(productDAO==null)
			productDAO = new ProductDAO();
		return productDAO;
	}
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con=con;
	}
	public int getProductListCount(String product_code) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int listCount=0;
		String sql="select count(*) from product_inventory_view";
		if(product_code !=null)
			sql+=" where product_code like '"+ product_code +"%'";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getProductListCount에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<ProductInventoryView> getProductList(String product_code, int page, int limit) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ProductInventoryView> productList=null;
		
		String sql="select * from product_inventory_view";
		int startrow=(page-1)*limit;

		if(product_code!=null)
			sql+=" where product_code like '"+product_code+"%'";
		
		sql+=" order by product_code limit "+startrow +","+limit;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ArrayList<ProductInventoryView>();
				do {
					ProductInventoryView product = new ProductInventoryView();
					
					product.setInventory_amount(rs.getInt("inventory_amount"));
					product.setProduct_code(rs.getString("product_code"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_price(rs.getInt("product_price"));
					productList.add(product);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getProductList 에러 :"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}
	public ProductInventoryView selectProduct(String product_code) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_inventory_view where product_code=?";
		ProductInventoryView product_view=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product_view = new ProductInventoryView();
				product_view.setEtc(rs.getString("etc"));
				product_view.setImage(rs.getString("image"));
				product_view.setProduct_code(rs.getString("product_code"));
				product_view.setProduct_name(rs.getString("product_name"));
				product_view.setProduct_price(rs.getInt("product_price"));
				product_view.setInventory_amount(rs.getInt("inventory_amount"));
			}
		}catch(Exception e) {
			System.out.println("selectProduct에러 : "+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return product_view;
	}
	public ArrayList<Inventory> selectInventoryList(String product_code,int page,int limit,int inventoryYear,int inventoryMonth) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Inventory> inventoryList=null;
		String sql="select * from inventory where product_code=?";
		int startrow=(page-1)*limit;
		
		if(!(inventoryYear==0||inventoryMonth==0)) {
			sql+=" and inventory_date between str_to_date('"+inventoryYear+"-"+inventoryMonth+"','%Y-%m') and str_to_date('"+inventoryYear+"-"+(inventoryMonth+1)+"','%Y-%m')";
		}else if(inventoryYear!=0) {
			sql+=" and inventory_date between str_to_date('"+inventoryYear+"','%Y') and str_to_date('"+(inventoryYear+1)+"','%Y')";
		}
		
		sql+=" order by inventory_num limit ?,? ";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, limit);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				inventoryList = new ArrayList<Inventory>();
				
				do {
					Inventory inventory=new Inventory();
					inventory.setInventory_amount(rs.getInt("inventory_amount"));
					inventory.setInventory_date(rs.getDate("inventory_date"));
					inventory.setInventory_num(rs.getInt("inventory_num"));
					inventory.setInventory_state(rs.getString("inventory_state"));
					inventory.setProduct_code(rs.getString("product_code"));
					inventoryList.add(inventory);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("selectInventoryList에러 : " +e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return inventoryList;
	}
	public int selectInventoryListCount(String product_code,int inventoryYear, int inventoryMonth) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int listCount =0;
		String sql="select count(*) from inventory where product_code=?";
		
		if(!(inventoryYear==0||inventoryMonth==0)) {
			sql+=" and inventory_date between str_to_date('"+inventoryYear+"-"+inventoryMonth+"','%Y-%m') and str_to_date('"+inventoryYear+"-"+(inventoryMonth+1)+"','%Y-%m')";
		}else if(inventoryYear!=0) {
			sql+=" and inventory_date between str_to_date('"+inventoryYear+"','%Y') and str_to_date('"+(inventoryYear+1)+"','%Y')";
		}
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("selectInventoryListCount에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public int insertProduct(String product_code, String product_name, int product_price, String image, String etc) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int add=0;
		String sql="insert into product values(?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product_code);
			pstmt.setString(2, product_name);
			pstmt.setInt(3, product_price);
			pstmt.setString(4, image);
			pstmt.setString(5, etc);
			
			add=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertProduct에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return add;
	}
	public int insertInventory(String product_code, int inventory_amount, String state) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="insert into inventory value(?,?,now(),?,?)";
		int add=0;
		int inventory_num=0;
		
		try {
			pstmt=con.prepareStatement("select max(inventory_num) from inventory");
			rs=pstmt.executeQuery();
			
			if(rs.next())
				inventory_num=rs.getInt(1)+1;
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,inventory_num);
			pstmt.setString(2, product_code);
			pstmt.setInt(3, inventory_amount);
			pstmt.setString(4, state);
			
			add=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertInventory에러: "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return add;
	}	
}
