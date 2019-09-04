<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:requestEncoding value="utf-8" />
<c:set var="login_Id" value="${param.id }" />
<c:set var="pass" value="${param.pass }" />

<c:catch var="err">
	<sql:setDataSource var="conn" dataSource="jdbc/mySQLDB" />
	<sql:query var="rs" dataSource="${conn }">
		select * from member where id=?
		<sql:param>${login_Id }</sql:param>
	</sql:query>
	<c:forEach var="row" items="${rs.rows }">
		<c:if test="${!empty rs }">
			<c:if test="${pass==row.password }">
				<c:set var="id" value="${login_Id }" scope="session" />
				<c:redirect url="shopMain.jsp" />
			</c:if>
		</c:if>
	</c:forEach>

	<script>
		alert("아이디나 비밀번호가 일치하지 않습니다.");
		location.href = "loginForm.jsp";
	</script>
</c:catch>
<c:if test="${!empty err }">
	<c:out value="${err }"></c:out>
</c:if>