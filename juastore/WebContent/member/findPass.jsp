<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/top.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 찾기</h1>
	<form action="/juastore/findPass.mem">
		<table>
			<tr>
				<td>아이디 입력</td>
				<td><input type="text" id="userId" name="userId"></td>
			</tr>
			<tr>
				<td>이메일 입력</td>
				<td><input type="text" id="email" name="email"></td>
				<td><input type="submit" value="찾기"></td>
			</tr>
		</table>
	</form>
	<c:if test="${! empty pass }">
		<c:choose>
			<c:when test="${pass eq 'no' }">
				<h1>없는 사용자 입니다.</h1>
			</c:when>
			<c:otherwise>
				<h1>메일로 비밀번호를 전송했습니다.</h1>
			</c:otherwise>
		</c:choose>
	</c:if>
	<a href="/juastore/member/findId.jsp">아이디 찾기</a><br>
	<a href="/juastore/loginForm.mem">로그인</a>
	
<jsp:include page="/side.jsp"/>
<jsp:include page="/bottom.jsp"/>
</body>
</html>