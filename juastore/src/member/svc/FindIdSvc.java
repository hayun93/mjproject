package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class FindIdSvc {

	public String getUserID(String email) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		String user_id = memberDAO.selectMemberId(email);
		
		close(con);
		
		return user_id;
	}

}
