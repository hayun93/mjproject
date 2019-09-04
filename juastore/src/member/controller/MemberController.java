package member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.action.AddrChangeAction;
import member.action.LoginAction;
import member.action.LogoutAction;
import member.action.MemberIdCheckAction;
import member.action.MemberInfoAction;
import member.action.MemberJoinProAction;
import member.action.MemberListAction;
import member.action.MemberModFormAction;
import member.action.MemberModProAction;
import member.action.PassChangeAction;
import vo.ActionForward;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
	   	String contextPath=request.getContextPath();
	   	String command=RequestURI.substring(contextPath.length());
	    	
	   	ActionForward forward=null;
	   	Action action = null;

    	if(command.equals("/joinForm.mem")) {
    		forward=new ActionForward();
    		forward.setPath("joinForm.jsp");
    	}else if(command.equals("/MemberJoinPro.mem")) {
    		action=new MemberJoinProAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/memberIdCheck.mem")) {
    		action = new MemberIdCheckAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/loginForm.mem")) {
    		forward=new ActionForward();
    		forward.setPath("loginForm.jsp");
    	}else if(command.equals("/login.mem")) {
    		action = new LoginAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/logout.mem")) {
    		action = new LogoutAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}else if(command.equals("/memberInfo.mem")) {
    		action = new MemberInfoAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/modify.mem")) {
    		action = new MemberModFormAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/modPro.mem")) {
    		action = new MemberModProAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/passChange.mem")) {
    		action = new PassChangeAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/memberList.mem")) {
    		action = new MemberListAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/addrChange.mem")) {
    		action = new AddrChangeAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	
    	if(forward !=null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
  
    	}
	}
}

