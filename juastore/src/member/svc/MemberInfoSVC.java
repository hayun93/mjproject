package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import vo.Member;

public class MemberInfoSVC {

	public Member getMember(String user_id) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		Member member = memberDAO.SelectMember(user_id);
		
		close(con);
		
		return member;
	}

}
