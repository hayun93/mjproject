<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a{
	text-decoration:none;
}
#log{
	width:400px;
	height:200px;
	border:1px solid #D9B577;
	padding-left:100px;
	padding-right:100px;
	padding-top:30px;
	padding-bottom:30px;
	margin-left:30%;
	text-align:center;
}
.logimage{
	margin-left:42%;
	margin-top:50px;
}
table{
	padding-top:30px;
	padding-left:80px;
	text-align:center;
}
.tableButton{
	margin: auto;
	width: 75%;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"/>
<section id="log">
<form name="loginform" action="login.mem" method="post">
<table>
	<tr>
		<td>
			<label for="id">아이디</label>
		</td>
		<td>
			<input type="text" name="id" id="id">
		</td>
	</tr>
	<tr>
		<td>
			<label for="password">비밀번호</label>
		</td>
		<td>
			<input type="password" name="password" id="password">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="tableButton">
				<input type="submit" value="로그인">
				<input type="reset" value="다시작성">
			</div>
		</td>
	</tr>
</table>
<br>
<a href="#">id/password찾기</a>&nbsp;&nbsp;

</form>
</section>
<jsp:include page="side.jsp"/>
<jsp:include page="bottom.jsp"/>
</body>
</html>