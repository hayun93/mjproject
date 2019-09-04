<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="top.jsp"/>
<style>
#total {
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
.content {
	border: 1px solid #ddd;
	padding: 5px;
}

.content_zip{
	border: 1px solid #ddd;
	padding: 5px;
	font-size:0.7em;
}

.title {
	border: 1px solid #ddd;
	background: #eee;
	color: #A16C29;
	text-align: left;
}
</style>
<%-- <%
if(session.getAttribute("id") != "admin"){
%>
<script>
alert('관리자로 로그인 하세요');
location.href="loginForm.jsp";
</script>
<%
}
%>   --%>
</head>
<body>

<div id="total">
	<h1>정보수정</h1>
	<form action="modPro.mem" name="modForm" method="post">
		<table id="modify">
			<tr>
				<th class="title">아이디</th>
				<td class="content">${member.id }
				<input type="hidden" value="아이디값넘겨주기" id="user_id" name="user_id"></td>
			</tr>
			<tr>
				<th class="title">비밀번호 변경</th>
				<td class="content_zip">
					<input type="text" value="${member.password }" id="user_pass" name="user_pass">
				</td>
			</tr>
			<tr>
				<th class="title">이름</th>
				<td class="content"><input type="text" value="${member.name }" id="user_name" name="user_name"></td>
			</tr>
			<tr>
				<th class="title">연락처</th>
				<td class="content"><input type="text" value="${member.phone }" id="user_phone" name="user_phone"></td>
			</tr>
			<tr>
				<th class="title">이메일</th>
				<td class="content"><input type="text" value="${member.email }" id="user_email" name="user_email"></td>
			</tr>
			
			<tr>
				<th class="title">주소</th>
				<td class="content_zip">
				(우편번호)<input type="text" value="${member.zip_code }" readonly size="5" id="zip_code" name="zip_code"><br>
				(주소)<input type="text" value="${member.address }" readonly size="50" id="address" name="address"><br>
				<a href="addrChange.jsp"><input type="button" value="주소 변경"></a>
				</td>
			</tr>
			<tr>
				<th class="title">누적 구매금액</th>
				<td class="content">${member.paysum } 원</td>
			</tr>
		</table>
		<div class="tableButton">
		<p>
			<input type="submit" value="수정">
			<input type="reset" value="다시작성">
			<input type="button" value="탈퇴">
		</div>
	</form>
</div>
<jsp:include page="side.jsp"/>
<jsp:include page="bottom.jsp"/>
</body>
</html>