package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Member;
import static db.JdbcUtil.*;

public class MemberDAO {

	private static MemberDAO memberDAO;
	private Connection con;
	
	public MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		// TODO Auto-generated method stub
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	//회원가입
	public int InsertMember(Member article) {
		PreparedStatement pstmt=null;
		String sql="insert into member Values (?,?,?,?,?,?,?,now())";
		int insertCount=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,article.getId());
			pstmt.setString(2,article.getPassword());
			pstmt.setString(3,article.getName());
			pstmt.setString(4,article.getEmail());
			pstmt.setInt(5,article.getZip_code());
			pstmt.setString(6, article.getAddress());
			pstmt.setString(7,article.getPhone());
			
			insertCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("JoinMember 오류 : " + ex);
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	//로그인
	public Member SelectMember(String id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql="select * from member where id=?";
		String sql2 = "select * from memlist where id=?";
		Member loginMember=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setPassword(rs.getString("password").trim());
				loginMember.setName(rs.getString("name"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setZip_code(rs.getInt("zip_code"));
				loginMember.setAddress(rs.getString("address"));
				loginMember.setPhone(rs.getString("phone"));
				
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, rs.getString("id"));
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					loginMember.setPaysum(rs2.getInt("accumpay"));
				}
			}
		}catch(SQLException e) {
			System.out.println("SelectMember 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
			close(rs2);
			close(pstmt2);
		}
		
		return loginMember;
	}

	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result=0;
		String update_sql = "update member set password=?,name=?,email=?,zip_code=?,address=?,phone=?"
				+ "where id=?";
		
		try {
			pstmt = con.prepareStatement(update_sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setInt(4, member.getZip_code());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("updateMember오류 : "+e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int PassChangeMember(Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result=0;
		String chpass_sql = "update member set password=? where id=?";

		try {
			pstmt = con.prepareStatement(chpass_sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("PassChange오류 : "+e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectListCount() {
		// TODO Auto-generated method stub
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 오류:"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Member> SelectMemList(int page, int limit, String search) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		String memlist_sql ="select * from member order by id limit ?,10";
		System.out.println("search값 테스트"+search);
		ArrayList<Member> memlist = new ArrayList<Member>();
		Member member = null;
		int startrow=(page-1)*10;
		
		try {
	
			pstmt = con.prepareStatement(memlist_sql);
			pstmt.setInt(1, startrow);
			rs=pstmt.executeQuery();
			

			while(rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setJoin_date(rs.getDate("join_date"));
				
				pstmt2 = con.prepareStatement("select member.id,member.join_date,accumpay from member "
						+ "left join memlist on member.id = memlist.id where member.id=?;");
				
				pstmt2.setString(1, rs.getString("id"));
				rs2 = pstmt2.executeQuery();
				while(rs2.next()) {
				member.setPaysum(rs2.getInt("accumpay"));
				}
				
				memlist.add(member);
			}
		}catch(Exception e) {
			System.out.println("getMemList오류 : "+e);
		}finally {
			close(rs);
			close(pstmt);
			close(rs2);
			close(pstmt2);
		}
		return memlist;
	}

	public int AddrChangeMember(Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result=0;
		String chaddr_sql = "update member set zip_code=?,address=? where id=?";
		System.out.println("DAO집코드"+member.getZip_code());
		try {
			pstmt = con.prepareStatement(chaddr_sql);
			pstmt.setInt(1, member.getZip_code());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("AddrChangeMember오류 : "+e);
		}finally {
			close(pstmt);
		}
		return result;
	}

}
