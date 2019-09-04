<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<jsp:include page="/top.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.total {
	margin: auto;
	width: 80%;
}
.tableButton{
	margin: auto;
	width: 75%;
}
#modify{
	width: 75%;
	border-collapse: collapse;
	font-size: 1.3em;
	margin: auto;
}
#content {
	border: 1px solid #ddd;
	padding: 5px;
}

#content_zip{
	border: 1px solid #ddd;
	padding: 5px;
	font-size:0.7em;
}

#title {
	border: 1px solid #ddd;
	background: #eee;
	color: #A16C29;
	text-align: left;
}
</style>
<%
if(session.getAttribute("id")==null){
%>
<script>
alert('로그인을 하세요');
location.href="loginForm.jsp";
</script>
<%
}
%>  
</head>
<body>

<div class="total">
	<h1>정보수정</h1>
	<form action="modPro.mem" name="modForm" method="post">
		<table id="modify">
			<tr>
				<th id="title">아이디</th>
				<td id="content"><%=session.getAttribute("id") %>
				<input type="hidden" value="<%=session.getAttribute("id") %>" id="id" name="id"></td>
			</tr>
			<tr>
				<th id="title">비밀번호 변경</th>
				<td id="content_zip">
					안전을 위해 3개월에 한번 비밀번호 변경을 추천드립니다. &nbsp;&nbsp;
					<a href="pass_change.jsp"><input type="button" value="비밀번호 변경하기"></a>
					<input type="hidden" value="${member.password }" id="password" name="password">
				</td>
			</tr>
			<tr>
				<th id="title">이름</th>
				<td id="content"><input type="text" value="${member.name }" id="name" name="name"></td>
			</tr>
			<tr>
				<th id="title">연락처</th>
				<td id="content"><input type="text" value="${member.phone }" id="phone" name="phone"></td>
			</tr>
			<tr>
				<th id="title">이메일</th>
				<td id="content"><input type="text" value="${member.email }" id="email" name="email"></td>
			</tr>
			
			<tr>
				<th id="title">주소</th>
				<td id="content_zip">
				(우편번호)<input type="text" value="${member.zip_code }" readonly size="5" id="zip_code" name="zip_code"><br>
				(주소)<input type="text" value="${member.address }" readonly size="50" id="address" name="address"><br>
				<a href="addrChange.jsp"><input type="button" value="주소 변경"></a>
				</td>
			</tr>
		</table>
		<div class="tableButton">
			<input type="submit" value="수정">
			<input type="reset" value="다시작성">
		</div>
	</form>
</div>
<jsp:include page="side.jsp"/>
<jsp:include page="bottom.jsp"/>
</body>
</html>