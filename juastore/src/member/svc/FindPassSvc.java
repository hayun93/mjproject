package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class FindPassSvc {

	public String getPassword(String userId, String email) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		String pass=memberDAO.selectMemberPass(userId,email);
		
		close(con);
		
		return pass;
	}

}
